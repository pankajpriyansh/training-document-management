package com.yash.tdms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.tdms.model.Section;

/**
 * SectionService provides all the services and functionalities related to
 * Section. This is the design, implemented by any specific implementation
 * provider . It contains all the services related to business logics .
 * 
 * @author goyal.ayush
 *
 */
@Service
public interface SectionService {
	/**
	 * returns all sections, created by the specific user , based on user id
	 * 
	 * @return
	 */
	List<Section> getAllSections();

	int addSection(Section section);

	void makeSectionNameFolder(String workingDir, String sectionName);

	String getSectionNameBySectionId(Integer sectionId);
}
