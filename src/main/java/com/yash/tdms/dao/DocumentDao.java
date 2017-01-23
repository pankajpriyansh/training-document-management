package com.yash.tdms.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.gson.JsonElement;
import com.yash.tdms.model.Document;

/**
 * DocumentDao will hold all the common CRUD tasks related to Document. This is
 * the design, implementation will be provided by the specific type of
 * implementation
 * 
 * @author goyal.ayush
 *
 */
@Repository
public interface DocumentDao {

	void addDocument(Document document);

	List<Document> getAllDocumentsByUserId(int id);

	List<Document> getAllActiveDocuments();

	int getTotalDocuments(int memberId);

	void changeStatusOfDocumentByDocumentId(int documentId, int statusId);

	void deleteDocumentById(int documentId);

	Document getDocumentById(int documentId);

	void updateDocument(int documentId, String name, String description);

	boolean checkIfStatusAlreadyRead(int documentId, int user_id);

	void doEntryAsReadForThisDocument(int documentId, int user_id);

	void updateReadEntryOfDocument(int documentId, int user_id);

	Map<String, Object> getDocumentReadStatus(int documentId, int user_id);

}
