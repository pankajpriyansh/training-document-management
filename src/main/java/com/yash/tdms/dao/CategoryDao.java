package com.yash.tdms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yash.tdms.model.Category;

/**
 * CategoryDao will hold all the common CRUD tasks related to Category. This is
 * the design, implementation will be provided by the specific type of
 * implementation
 * 
 * @author goyal.ayush
 *
 */
@Repository
public interface CategoryDao {

	List<Category> getCategoriesBySectionId(int sectionId);

	int addCategory(Category category);

	int getTotalCategories();

	boolean checkIfCategoryExists(String categoryName, Integer sectionId);

	Category getCategoryFromDocumentId(int documentId);

	List<Category> getCategoriesUnderASectionByDocumentId(int documentId);

	Category getCategoryByCategoryId(int fromCategoryId);

}
