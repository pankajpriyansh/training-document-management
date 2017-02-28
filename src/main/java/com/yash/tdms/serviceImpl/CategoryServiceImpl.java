package com.yash.tdms.serviceImpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.tdms.dao.CategoryDao;
import com.yash.tdms.model.Category;
import com.yash.tdms.service.CategoryService;
import com.yash.tdms.service.SectionService;

/**
 * CategoryServiceImpl is the implementation of all the services and
 * functionalities related to Category. It contains all the services related to
 * business logics. Whenever there is a need of database , it can have a call to
 * DAO , to fulfill the need.
 * 
 * @author goyal.ayush
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private SectionService sectionService;

	public List<Category> getCategoriesBySectionId(int sectionId) {
		return categoryDao.getCategoriesBySectionId(sectionId);
	}

	public int addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	public void makeCategoryNameFolder(String workingDir, Integer sectionId,
			String categoryName) {
		String sectionName = sectionService
				.getSectionNameBySectionId(sectionId);
		System.out.println("Current working directory : " + workingDir);
		File pathToMakeCategoryDir = new File(workingDir + File.separator
				+ "Documents" + File.separator + sectionName + File.separator
				+ categoryName);
		if (!pathToMakeCategoryDir.exists()) {
			pathToMakeCategoryDir.mkdirs();
			System.out
					.println("--------------- Category Directory Made -------------");
		}
	}

	public int getTotalCategories() {
		return categoryDao.getTotalCategories();
	}

	public boolean checkIfCategoryExists(String categoryName, Integer sectionId) {
		return categoryDao.checkIfCategoryExists(categoryName, sectionId);
	}

	@Override
	public Category getCategoryFromDocumentId(int documentId) {
		return categoryDao.getCategoryFromDocumentId(documentId);
	}

	@Override
	public List<Category> getCategoriesUnderASectionByDocumentId(int documentId) {
		return categoryDao.getCategoriesUnderASectionByDocumentId(documentId);
	}

	@Override
	public Category getCategoryByCategoryId(int fromCategoryId) {
		return categoryDao.getCategoryByCategoryId(fromCategoryId);
	}

}
