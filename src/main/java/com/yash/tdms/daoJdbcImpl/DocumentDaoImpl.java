package com.yash.tdms.daoJdbcImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sun.mail.handlers.image_gif;
import com.yash.tdms.dao.DocumentDao;
import com.yash.tdms.model.Document;
import com.yash.tdms.model.Member;

/**
 * DocumentDaoImpl is the JDBC implementation of all the CRUD operations related
 * to Document
 * 
 * @author goyal.ayush
 *
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addDocument(Document document) {
		jdbcTemplate
				.update("INSERT INTO documents(user_id,category_id,NAME,description,createdby,modifiedby,createddate,modifieddate,filepath,batch_id) VALUES (?,?,?,?,?,?,?,?,?,?);",
						new Object[] { document.getUser_id(),
								document.getCategory_id(), document.getName(),
								document.getDescription(),
								document.getUser_id(), document.getUser_id(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()),
								document.getFilePath(), document.getBatchId() });
	}

	/**
	 * Trainer can see his own documents only, thats why we are getting
	 * getAllDocumentsByUserId
	 */

	public List<Document> getAllDocumentsByUserId(int id) {
		return jdbcTemplate.query("select * from documents where user_id=?",
				new Object[] { id }, new DocumentRowMapper());
	}

	private static final class DocumentRowMapper implements RowMapper<Document> {

		public Document mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Document document = new Document();

			// Map columns to document object

			document.setId(resultSet.getInt("id"));
			document.setName(resultSet.getString("name"));
			document.setCategory_id(resultSet.getInt("category_id"));
			document.setUser_id(resultSet.getInt("user_id"));
			document.setDescription(resultSet.getString("description"));
			document.setFilePath(resultSet.getString("filepath"));
			document.setIsShow(resultSet.getInt("isShow"));
			document.setIsActive(resultSet.getInt("isActive"));
			document.setCreatedBy(resultSet.getInt("createdby"));
			document.setModifiedBy(resultSet.getInt("modifiedby"));
			document.setCreatedDate(resultSet.getDate("createddate"));
			document.setModifiedDate(resultSet.getDate("modifieddate"));
			document.setBatchId(resultSet.getInt("batch_id"));
			if (document.getName().contains(" ")) {
				document.setName(document.getName().replaceAll(" ", "_"));
			}
			System.out.println(document);
			return document;
		}
	}

	public List<Document> getAllActiveDocuments(int batchId, int memberId) {
		List<Document> documents = jdbcTemplate.query(
				"select * from documents where isShow = 1 AND batch_id=?",
				new Object[] { batchId }, new DocumentRowMapper());
		try {
			List<Document> documentsForSpecificMember = jdbcTemplate
					.query("SELECT * FROM documents WHERE id IN (SELECT documentId FROM `members_documents_junction_show_status` WHERE `user_id`=?)",
							new Object[] { memberId }, new DocumentRowMapper());
			documents.addAll(documentsForSpecificMember);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return documents;
	}

	public int getTotalDocuments(int memberId) {
		return jdbcTemplate.queryForObject(
				"select count(*) from documents where user_id=?",
				new Object[] { memberId }, Integer.class);
	}

	public void changeStatusOfDocumentByDocumentId(int documentId, int statusId) {
		jdbcTemplate.update("update documents set isShow=? where id=?",
				new Object[] { statusId, documentId });
	}

	public void deleteDocumentById(int documentId) {
		System.out.println("------------------------------  " + documentId);
		jdbcTemplate.update("DELETE FROM documents WHERE id=?",
				new Object[] { documentId });
	}

	public Document getDocumentById(int documentId) {
		return jdbcTemplate.queryForObject(
				"select * from documents where id=?",
				new Object[] { documentId }, new DocumentRowMapper());
	}

	public void updateDocument(int documentId, String name, String description) {
		jdbcTemplate.update(
				"update documents set name=?,description=? where id=?",
				new Object[] { name, description, documentId });
	}

	public boolean checkIfStatusAlreadyRead(int documentId, int user_id) {
		try {
			jdbcTemplate
					.queryForObject(
							"select id from members_documents_junction where document_id=? and user_id=?",
							new Object[] { documentId, user_id }, Integer.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void doEntryAsReadForThisDocument(int documentId, int user_id) {
		jdbcTemplate
				.update("INSERT INTO members_documents_junction(document_id,user_id,STATUS,createddate,modifieddate) VALUES(?,?,?,?,?)",
						new Object[] { documentId, user_id, 1,
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });
	}

	public void updateReadEntryOfDocument(int documentId, int user_id) {
		jdbcTemplate
				.update("update members_documents_junction set modifieddate=?,count=count+1 where document_id=? and user_id=?",
						new Object[] { new Timestamp(new Date().getTime()),
								documentId, user_id });
	}

	public Map<String, Object> getDocumentReadStatus(int documentId, int user_id) {

		try {
			Map<String, Object> map = (Map<String, Object>) jdbcTemplate
					.queryForMap(
							"SELECT STATUS,createddate,modifieddate,count FROM members_documents_junction WHERE document_id =? AND user_id=? ",
							new Object[] { documentId, user_id });
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Document> getAllDocumentsByBatchId(int batchId) {
		return jdbcTemplate.query("select * from documents where batch_id=?",
				new Object[] { batchId }, new DocumentRowMapper());
	}

	public List getDocumentReadStautsList(int batchId, int documentId) {
		List<Member> members = jdbcTemplate.query(
				"select * from members where batch_id=?",
				new Object[] { batchId }, new MemberRowMapper());
		List list = new ArrayList();
		members.forEach((i) -> {
			try {
				Map<String, Object> map = (Map<String, Object>) jdbcTemplate
						.queryForMap(
								"SELECT status,createddate,modifieddate,count FROM members_documents_junction WHERE document_id =? AND user_id=? ",
								new Object[] { documentId, i.getId() });
				map.put("name", i.getFirstname() + " " + i.getLastname());
				map.put("email", i.getEmail());
				System.out.println(map);
				list.add(map);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", i.getFirstname() + " " + i.getLastname());
				map.put("email", i.getEmail());
				map.put("status", 0);
				System.out.println(map);
				list.add(map);
			}
		});
		return list;
	}

	private static final class MemberRowMapper implements RowMapper<Member> {

		public Member mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Member member = new Member();
			member.setId(resultSet.getInt("id"));
			member.setFirstname(resultSet.getString("firstname"));
			member.setLastname(resultSet.getString("lastname"));
			member.setContact(resultSet.getLong("contact"));
			member.setEmail(resultSet.getString("email"));
			member.setIsActive(resultSet.getInt("isActive"));
			member.setRole(resultSet.getInt("role"));
			member.setBatchId(resultSet.getInt("batch_id"));
			member.setCreatedDate(resultSet.getDate("createddate"));
			System.out.println(member);
			return member;
		}

	}

	@Override
	public int getBatchIdByDocumentId(int documentId) {
		return jdbcTemplate.queryForObject(
				"select batch_id from documents where id=?",
				new Object[] { documentId }, Integer.class);
	}

	@Override
	public void changeStatusOfDocumentByDocumentIdForSpecificMember(
			int documentId, int memberId) {
		jdbcTemplate
				.update("INSERT INTO members_documents_junction_show_status(documentId,user_id,createddate,modifieddate) VALUES(?,?,?,?)",
						new Object[] { documentId, memberId,
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });
	}

	@Override
	public void hideDocumentForSpecificMember() {
		jdbcTemplate
				.update("DELETE FROM members_documents_junction_show_status WHERE createddate< NOW() - INTERVAL 2 DAY;");
	}

	@Override
	public void shiftDocumentsByBatch(int fromBatchId, int toBatchId,
			int memberId) {
		List<Document> documents = jdbcTemplate
				.query("select * from documents where batch_id=? and createdby = ?",
						new Object[] { fromBatchId, memberId },
						new DocumentRowMapper());
		String insertSqlString = "INSERT INTO documents(user_id,category_id,NAME,description,createdby,modifiedby,createddate,modifieddate,filepath,batch_id) VALUES (?,?,?,?,?,?,?,?,?,?);";

		jdbcTemplate.batchUpdate(insertSqlString,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Document document = documents.get(i);
						ps.setInt(1, document.getUser_id());
						ps.setInt(2, document.getCategory_id());
						ps.setString(3, document.getName());
						ps.setString(4, document.getDescription());
						ps.setInt(5, document.getCreatedBy());
						ps.setInt(6, document.getModifiedBy());
						ps.setDate(7, new java.sql.Date(document
								.getCreatedDate().getTime()));
						ps.setDate(8, new java.sql.Date(document
								.getModifiedDate().getTime()));
						ps.setString(9, document.getFilePath());
						ps.setInt(10, toBatchId);
					}

					@Override
					public int getBatchSize() {
						return documents.size();
					}

				});
	}

	@Override
	public void updateDocumentObject(Document document) {
		jdbcTemplate
				.update("update documents set user_id=?,category_id=?,NAME=?,description=?,createdby=?,modifiedby=?,createddate=?,modifieddate=?,filepath=?,batch_id=? where id=?",
						new Object[] { document.getUser_id(),
								document.getCategory_id(), document.getName(),
								document.getDescription(),
								document.getUser_id(), document.getUser_id(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()),
								document.getFilePath(), document.getBatchId(),
								document.getId() });
	}

	@Override
	public boolean documentNameExistsUnderThisBatch(int batchId,
			String documentName) {
		try {
			jdbcTemplate.queryForObject(
					"select id from documents where batch_id=? and name=?",
					new Object[] { batchId, documentName }, Integer.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public void saveRequestForDocument(int fromUserId, int toUserId,
			List<Integer> documentsId, String reason) {
		jdbcTemplate
				.update("INSERT INTO documents_request (from_user_id,to_user_id,createddate,modifieddate,reason) VALUES (?,?,?,?,?);",
						new Object[] { fromUserId, toUserId,
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()), reason });

		int documents_request_id = jdbcTemplate
				.queryForObject(
						"select id from documents_request where from_user_id=? and to_user_id=? and reason = ?",
						new Object[] { fromUserId, toUserId, reason },
						Integer.class);
		String insertSqlString = "INSERT INTO requested_documents (documents_request_id,document_id,createdby,modifiedby,createddate,modifieddate) VALUES (?,?,?,?,?,?)";

		jdbcTemplate.batchUpdate(insertSqlString,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						documentsId.get(i);
						ps.setInt(1, documents_request_id);
						ps.setInt(2, documentsId.get(i));
						ps.setInt(3, fromUserId);
						ps.setInt(4, fromUserId);
						ps.setDate(5, new java.sql.Date(new Date().getTime()));
						ps.setDate(6, new java.sql.Date(new Date().getTime()));
					}

					@Override
					public int getBatchSize() {
						return documentsId.size();
					}
				});
	}

	private static final class DocumentRequestRowMapper implements
			RowMapper<List> {

		public List mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			List list = new ArrayList();
			// Map columns to document object
			list.add(resultSet.getInt("id"));
			list.add(resultSet.getInt("from_user_id"));
			list.add(resultSet.getString("reason"));
			list.add(resultSet.getString("rejected_reason"));
			list.add(resultSet.getInt("isActive"));
			list.add(resultSet.getDate("createddate"));
			System.out.println(list);
			return list;
		}
	}

	@Override
	public List getRequestedDocumentsData(int memberId) {
		List listOfRequestedDocuments = new ArrayList();
		try {
			listOfRequestedDocuments = jdbcTemplate
					.query("select id,from_user_id,reason,rejected_reason,isActive,createddate from documents_request where to_user_id=? and isActive=1",
							new Object[] { memberId },
							new DocumentRequestRowMapper());
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}
		System.out.println(listOfRequestedDocuments);

		List list = new ArrayList();
		for (Object i : listOfRequestedDocuments) {
			List tempList = (List) i;
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("reason", tempList.get(2));
			tempMap.put("requestId", tempList.get(0));

			List<Document> documentsList = jdbcTemplate
					.query("select * from documents where id in (SELECT document_id from requested_documents where documents_request_id = ?)",
							new Object[] { tempList.get(0) },
							new DocumentRowMapper());
			System.out.println(documentsList);
			tempMap.put("documents", documentsList);

			Map<String, Object> map2 = (Map<String, Object>) jdbcTemplate
					.queryForMap(
							"select mem.id as memberId,concat(mem.firstname,' ',mem.lastname) as name,mem.email as email,(select bat.name from batches bat where id=mem.batch_id) as batch from members mem where id =? ",
							new Object[] { tempList.get(1) });
			tempMap.put("name", map2.get("name"));
			tempMap.put("email", map2.get("email"));
			tempMap.put("batch", map2.get("batch"));
			tempMap.put("memberId", map2.get("memberId"));
			System.out.println(map2);
			list.add(tempMap);

		}
		System.out.println("--------Finalk list -------------------- ");
		System.out.println(list);

		return list;
	}

	@Override
	public void approveRequestForDocument(int requestId,
			List<Integer> documentsId, int memberId) {
		jdbcTemplate
				.update("update documents_request set isActive=2,modifieddate=? where id=?",
						new Object[] { new Timestamp(new Date().getTime()),
								requestId });

		String insertSqlString = "INSERT INTO members_documents_junction_show_status(documentId,user_id,createddate,modifieddate) VALUES(?,?,?,?)";

		jdbcTemplate.batchUpdate(insertSqlString,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, documentsId.get(i));
						ps.setInt(2, memberId);
						ps.setTimestamp(3, new Timestamp(new Date().getTime()));
						ps.setTimestamp(4, new Timestamp(new Date().getTime()));

					}

					@Override
					public int getBatchSize() {
						return documentsId.size();
					}
				});

	}

	@Override
	public void saveReasonForRejectionOfRequest(int requestId, String reason) {
		jdbcTemplate
				.update("update documents_request set isActive=2,modifieddate=?,rejected_reason=? where id=?",
						new Object[] { new Timestamp(new Date().getTime()),
								reason, requestId });
		System.out.println("UPDATED  ----------------------------------- "
				+ requestId);
	}

	@Override
	public List getRequestedDocumentReportsBasicData(int memberId) {
		List listOfRequestedDocuments = new ArrayList();
		try {
			listOfRequestedDocuments = jdbcTemplate
					.query("select id,from_user_id,reason,rejected_reason,isActive,createddate from documents_request where to_user_id=?",
							new Object[] { memberId },
							new DocumentRequestRowMapper());
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}
		List list = new ArrayList();
		for (Object i : listOfRequestedDocuments) {
			List tempList = (List) i;
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("reason", tempList.get(2));
			tempMap.put("requestId", tempList.get(0));
			tempMap.put("toMemberId", memberId);
			Map<String, Object> map2 = (Map<String, Object>) jdbcTemplate
					.queryForMap(
							"select mem.id as memberId,concat(mem.firstname,' ',mem.lastname) as name,mem.email as email,(select bat.name from batches bat where id=mem.batch_id) as batch from members mem where id =? ",
							new Object[] { tempList.get(1) });
			tempMap.put("name", map2.get("name"));
			tempMap.put("email", map2.get("email"));
			tempMap.put("batch", map2.get("batch"));
			tempMap.put("fromMemberId", map2.get("memberId"));
			System.out.println(map2);
			list.add(tempMap);
		}

		Map<Integer, Map<String, Object>> temporaryMap = new LinkedHashMap<Integer, Map<String, Object>>();
		for (Object o : list) {
			Map<String, Object> map = (Map<String, Object>) o;
			temporaryMap.put((Integer) map.get("fromMemberId"), map);
		}
		list.clear();
		list.addAll(temporaryMap.values());
		return list;
	}

	@Override
	public List getRequestedDocumentReportsAdvanceData(int fromMemberId,
			int toMemberId) {

		List listOfRequestedDocuments = new ArrayList();
		try {
			listOfRequestedDocuments = jdbcTemplate
					.query("select id,from_user_id,reason,rejected_reason,isActive,createddate from documents_request where to_user_id=? and from_user_id=?",
							new Object[] { toMemberId, fromMemberId },
							new DocumentRequestRowMapper());
		} catch (DataAccessException e1) {
			e1.printStackTrace();
		}
		System.out.println(listOfRequestedDocuments);

		List list = new ArrayList();
		// Map<String, Object> tempMap = new HashMap<String, Object>();
		List intermediateList = new ArrayList();
		List<Document> totalDocuments = new ArrayList<Document>();
		for (Object i : listOfRequestedDocuments) {
			List tempList = (List) i;
			List<Document> documentsList = jdbcTemplate
					.query("select * from documents where id in (SELECT document_id from requested_documents where documents_request_id = ?)",
							new Object[] { tempList.get(0) },
							new DocumentRowMapper());
			Map<String, Object> tempMapOfDocumentNameAndStatus = new HashMap<String, Object>();

			documentsList.forEach((doc) -> {
				String status = " NotApproved ";
				if (((Integer) tempList.get(4)) == 1) {
					status = " No Action Performed ";
				} else {
					if ((((Integer) tempList.get(4)) == 2)
							&& tempList.get(3) == null) {
						status = " Approved ";
					} else {
						status += " , " + tempList.get(3);
					}
				}
				tempMapOfDocumentNameAndStatus.put(doc.getName(), status + "/"
						+ tempList.get(5));
			});
			intermediateList.add(tempMapOfDocumentNameAndStatus);
			// tempMap.putAll(tempMapOfDocumentNameAndStatus);
		}

		intermediateList.forEach((i) -> {
			Map<String, Object> interMap = (Map<String, Object>) i;
			interMap.forEach((k, v) -> {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("documentName", k);
				String value = (String) v;
				m.put("status", value.split("/")[0]);
				m.put("Date", value.split("/")[1]);
				list.add(m);
			});
		});
		/*
		 * tempMap.forEach((k, v) -> { Map<String, Object> m = new
		 * HashMap<String, Object>(); m.put("documentName", k); String value =
		 * (String) v; m.put("status", value.split("/")[0]); m.put("Date",
		 * value.split("/")[1]);
		 * 
		 * list.add(m); });
		 */
		return list;
	}

	@Override
	public void shiftDocumentsByTrainer(int fromTrainerId, int toTrainerId,
			List<Integer> documentsId) {

		List<Document> tempDocuments = jdbcTemplate.query(
				"select * from documents where createdby = ?",
				new Object[] { fromTrainerId }, new DocumentRowMapper());
		List<Document> documents = tempDocuments.stream()
				.filter((i) -> documentsId.contains(i.getId()))
				.collect(Collectors.toList());
		System.out.println(documents);

		String insertSqlString = "INSERT INTO documents(user_id,category_id,NAME,description,createdby,modifiedby,createddate,modifieddate,filepath,batch_id) VALUES (?,?,?,?,?,?,?,?,?,?);";

		jdbcTemplate.batchUpdate(insertSqlString,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Document document = documents.get(i);
						ps.setInt(1, toTrainerId);
						ps.setInt(2, document.getCategory_id());
						ps.setString(3, document.getName());
						ps.setString(4, document.getDescription());
						ps.setInt(5, toTrainerId);
						ps.setInt(6, toTrainerId);
						ps.setDate(7, new java.sql.Date(document
								.getCreatedDate().getTime()));
						ps.setDate(8, new java.sql.Date(document
								.getModifiedDate().getTime()));
						ps.setString(9, document.getFilePath());
						ps.setInt(10, document.getBatchId());
					}

					@Override
					public int getBatchSize() {
						return documents.size();
					}

				});

	}

	@Override
	public List<Document> getAllDocumentsByBatchIdAndMemberId(int batchId,
			int memberId) {

		return jdbcTemplate.query(
				"select * from documents where batch_id=? and user_id=?",
				new Object[] { batchId, memberId }, new DocumentRowMapper());
	}

	@Override
	public List<Document> getAllDocumentsByUserIdBasedOnOperation(int memberId,
			int flag) {
		return jdbcTemplate.query(
				"select * from documents where user_id=? and isShow = ?",
				new Object[] { memberId, flag }, new DocumentRowMapper());
	}
}
