package com.yash.tdms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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

}
