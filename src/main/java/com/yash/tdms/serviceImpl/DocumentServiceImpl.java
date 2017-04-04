package com.yash.tdms.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.tdms.dao.DocumentDao;
import com.yash.tdms.model.Category;
import com.yash.tdms.model.Document;
import com.yash.tdms.service.CategoryService;
import com.yash.tdms.service.DocumentService;

/**
 * DocumentServiceImpl is the implementation of all the services and
 * functionalities related to Document. It contains all the services related to
 * business logics. Whenever there is a need of database , it can have a call to
 * DAO , to fulfill the need.
 * 
 * @author goyal.ayush
 *
 */
@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	@Autowired
	private CategoryService categoryService;

	public void addDocument(Document document) {
		documentDao.addDocument(document);
	}

	public List<Document> getAllDocumentsByUserId(int id) {
		return documentDao.getAllDocumentsByUserId(id);
	}

	public void uploadFile(MultipartFile file, String workingDir,
			String filePath) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				System.out.println("Current working directory : " + workingDir);
				File uploadedDocument = new File(workingDir + File.separator
						+ "Documents" + File.separator + filePath);
				System.out.println("Total File Path -> " + uploadedDocument);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream(uploadedDocument));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				System.out.println("File Uploaded successfully");
			} catch (Exception e) {
				System.out.println("file not upload " + e.getMessage());
			}
		}
	}

	public List<Document> getAllActiveDocuments(int batchId, int memberId) {
		return documentDao.getAllActiveDocuments(batchId, memberId);
	}

	public int getTotalDocuments(int memberId) {
		return documentDao.getTotalDocuments(memberId);
	}

	public void changeStatusOfDocumentByDocumentId(int documentId, int statusId) {
		documentDao.changeStatusOfDocumentByDocumentId(documentId, statusId);
	}

	public void deleteDocumentById(int documentId) {
		documentDao.deleteDocumentById(documentId);
	}

	public Document getDocumentById(int documentId) {
		return documentDao.getDocumentById(documentId);
	}

	public void updateDocument(int documentId, String name, String description) {
		documentDao.updateDocument(documentId, name, description);
	}

	public boolean checkIfStatusAlreadyRead(int documentId, int user_id) {
		return documentDao.checkIfStatusAlreadyRead(documentId, user_id);
	}

	public void doEntryAsReadForThisDocument(int documentId, int user_id) {
		documentDao.doEntryAsReadForThisDocument(documentId, user_id);
	}

	public void updateReadEntryOfDocument(int documentId, int user_id) {
		documentDao.updateReadEntryOfDocument(documentId, user_id);
	}

	public Map<String, Object> getDocumentReadStatus(int documentId,
			int memberId) {
		return documentDao.getDocumentReadStatus(documentId, memberId);
	}

	public List<Document> getAllDocumentsByBatchId(int batchId) {
		return documentDao.getAllDocumentsByBatchId(batchId);
	}

	public List getDocumentReadStautsList(int batchId, int documentId) {
		return documentDao.getDocumentReadStautsList(batchId, documentId);
	}

	@Override
	public int getBatchIdByDocumentId(int documentId) {
		return documentDao.getBatchIdByDocumentId(documentId);
	}

	@Override
	public void changeStatusOfDocumentByDocumentIdForSpecificMember(
			int documentId, int memberId) {
		documentDao.changeStatusOfDocumentByDocumentIdForSpecificMember(
				documentId, memberId);
	}

	@Override
	public void hideDocumentForSpecificMember() {
		documentDao.hideDocumentForSpecificMember();
	}

	@Override
	public void shiftDocumentsByBatch(int fromBatchId, int toBatchId,
			int memberId) {
		documentDao.shiftDocumentsByBatch(fromBatchId, toBatchId, memberId);
	}

	@Override
	public void shiftDocumentsByCategory(int documentId, int fromCategoryId,
			int toCategoryId, String workingDir) {
		Category fromCategory = categoryService
				.getCategoryByCategoryId(fromCategoryId);
		Category toCategory = categoryService
				.getCategoryByCategoryId(toCategoryId);
		Document document = documentDao.getDocumentById(documentId);
		document.setCategory_id(toCategoryId);
		String oldFilePathString = document.getFilePath();
		document.setFilePath(oldFilePathString.replace(oldFilePathString
				.substring(oldFilePathString.indexOf("/"),
						oldFilePathString.lastIndexOf("/")),
				"/" + toCategory.getName()));
		System.out.println(document);
		documentDao.updateDocumentObject(document);

		moveFile(oldFilePathString, document.getFilePath(), workingDir);

	}

	private void moveFile(String oldFilePath, String newFilePath,
			String workingDir) {
		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			File afile = new File(workingDir + File.separator + "Documents"
					+ File.separator + oldFilePath);
			File bfile = new File(workingDir + File.separator + "Documents"
					+ File.separator + newFilePath);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete the original file
			afile.delete();

			System.out.println("File is copied successful!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean documentNameExistsUnderThisBatch(int batchId,
			String documentName) {

		return documentDao.documentNameExistsUnderThisBatch(batchId,
				documentName);
	}

	@Override
	public void saveRequestForDocument(int fromUserId, int toUserId,
			List<Integer> documentsId, String reason) {
		documentDao.saveRequestForDocument(fromUserId, toUserId, documentsId,
				reason);
	}

	@Override
	public List getRequestedDocumentsData(int memberId) {
		return documentDao.getRequestedDocumentsData(memberId);
	}

	@Override
	public void approveRequestForDocument(int requestId,
			List<Integer> documentsId, int memberId) {
		documentDao.approveRequestForDocument(requestId, documentsId, memberId);
	}

	@Override
	public void saveReasonForRejectionOfRequest(int requestId, String reason) {
		documentDao.saveReasonForRejectionOfRequest(requestId, reason);
	}

	@Override
	public List getRequestedDocumentReportsBasicData(int memberId) {
		return documentDao.getRequestedDocumentReportsBasicData(memberId);
	}

	@Override
	public List getRequestedDocumentReportsAdvanceData(int fromMemberId,
			int toMemberId) {
		return documentDao.getRequestedDocumentReportsAdvanceData(fromMemberId,
				toMemberId);
	}

	@Override
	public void shiftDocumentsByTrainer(int fromTrainerId, int toTrainerId,
			List<Integer> documentsId) {
		documentDao.shiftDocumentsByTrainer(fromTrainerId, toTrainerId,
				documentsId);
	}

	@Override
	public List<Document> getAllDocumentsByBatchIdAndMemberId(int batchId,
			int memberId) {
		return documentDao.getAllDocumentsByBatchIdAndMemberId(batchId,
				memberId);
	}

	@Override
	public List<Document> getAllDocumentsByUserIdBasedOnOperation(int memberId,
			int flag) {
		return documentDao.getAllDocumentsByUserIdBasedOnOperation(memberId,
				flag);
	}

	@Override
	public List<Document> getFavouriteDocumentsByBatchIdAndMemberId(
			int batchId, int memberId) {
		return documentDao.getFavouriteDocumentsByBatchIdAndMemberId(batchId,
				memberId);
	}

	@Override
	public void shiftDocumentsBySection(int fromBatchId, int toBatchId,
			int sectionId, int memberId) {
		documentDao.shiftDocumentsBySection(fromBatchId, toBatchId, sectionId,
				memberId);
	}
}
