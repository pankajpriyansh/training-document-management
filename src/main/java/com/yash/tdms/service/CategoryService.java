package com.yash.tdms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.tdms.model.Category;

/**
 * CategoryService provides all the services and functionalities related to
 * category. This is the design, implemented by any specific implementation
 * provider . It contains all the services related to business logics .
 * 
 * @author goyal.ayush
 *
 */
@Service
public interface CategoryService {

	List<Category> getCategoriesBySectionId(int sectionId);

	int addCategory(Category category);

	void makeCategoryNameFolder(String workingDir, Integer sectionId,
			String categoryName);

	int getTotalCategories();

	boolean checkIfCategoryExists(String categoryName, Integer sectionId);

	Category getCategoryFromDocumentId(int documentId);

	List<Category> getCategoriesUnderASectionByDocumentId(int documentId);

	Category getCategoryByCategoryId(int fromCategoryId);

}
