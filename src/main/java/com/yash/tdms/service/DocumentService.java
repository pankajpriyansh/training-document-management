package com.yash.tdms.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.tdms.model.Document;

/**
 * DocumentService provides all the services and functionalities related to
 * Document. This is the design, implemented by any specific implementation
 * provider . It contains all the services related to business logics .
 * 
 * @author goyal.ayush
 *
 */
@Service
public interface DocumentService {

	void addDocument(Document document);

	List<Document> getAllDocumentsByUserId(int id);

	void uploadFile(MultipartFile file, String workingDir, String filePath);

	List<Document> getAllActiveDocuments(int batchId, int memberId);

	int getTotalDocuments(int memberId);

	void changeStatusOfDocumentByDocumentId(int documentId, int statusId);

	void deleteDocumentById(int documentId);

	Document getDocumentById(int documentId);

	void updateDocument(int documentId, String name, String description);

	boolean checkIfStatusAlreadyRead(int documentId, int user_id);

	void doEntryAsReadForThisDocument(int documentId, int user_id);

	void updateReadEntryOfDocument(int documentId, int user_id);

	Map<String, Object> getDocumentReadStatus(int documentId, int memberId);

	List<Document> getAllDocumentsByBatchId(int batchId);

	List getDocumentReadStautsList(int batchId, int documentId);

	int getBatchIdByDocumentId(int documentId);

	void changeStatusOfDocumentByDocumentIdForSpecificMember(int documentId,
			int memberId);

	void hideDocumentForSpecificMember();

	void shiftDocumentsByBatch(int fromBatchId, int toBatchId, int memberId);

	void shiftDocumentsByCategory(int documentId, int fromCategoryId,
			int toCategory, String workingDir);

	boolean documentNameExistsUnderThisBatch(int batchId, String documentName);

	void saveRequestForDocument(int fromUserId, int toUserId,
			List<Integer> documentsId, String reason);

	List getRequestedDocumentsData(int memberId);

	void approveRequestForDocument(int requestId, List<Integer> documentsId,
			int memberId);

	void saveReasonForRejectionOfRequest(int requestId, String reason);

	List getRequestedDocumentReportsBasicData(int memberId);

	List getRequestedDocumentReportsAdvanceData(int fromMemberId, int toMemberId);

	void shiftDocumentsByTrainer(int fromTrainerId, int toTrainerId,
			List<Integer> documentsId);

	List<Document> getAllDocumentsByBatchIdAndMemberId(int batchId, int memberId);

	List<Document> getAllDocumentsByUserIdBasedOnOperation(int memberId, int flag);

}
