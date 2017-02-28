package com.yash.tdms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yash.tdms.model.Section;

/**
 * SectionDao will hold all the common CRUD tasks related to Section. This is
 * the design, implementation will be provided by the specific type of
 * implementation
 * 
 * @author goyal.ayush
 *
 */
@Repository
public interface SectionDao {
	/**
	 * returns all sections, created by the specific user , based on user id
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	List<Section> getAllSections();

	int addSection(Section section);

	String getSectionNameBySectionId(Integer sectionId);

	int getTotalSections();

	boolean checkIfSectionExists(String sectionName);

	List getSectionCategoryDocumentGraphData(int batchId);
}
