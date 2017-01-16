package com.yash.tdms.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.tdms.dao.DocumentDao;
import com.yash.tdms.model.Document;
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

	@Override
	public void addDocument(Document document) {
		documentDao.addDocument(document);
	}

	@Override
	public List<Document> getAllDocumentsByUserId(int id) {
		return documentDao.getAllDocumentsByUserId(id);
	}

	@Override
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

	@Override
	public List<Document> getAllActiveDocuments() {
		return documentDao.getAllActiveDocuments();
	}

}
