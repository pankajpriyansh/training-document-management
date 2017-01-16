package com.yash.tdms.serviceImpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.tdms.dao.SectionDao;
import com.yash.tdms.model.Section;
import com.yash.tdms.service.SectionService;

/**
 * SectionServiceImpl is the implementation of all the services and
 * functionalities related to Section. It contains all the services related to
 * business logics. Whenever there is a need of database , it can have a call to
 * DAO , to fulfill the need.
 * 
 * @author goyal.ayush
 *
 */
@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionDao sectionDao;

	@Override
	public List<Section> getAllSections() {
		return sectionDao.getAllSections();
	}

	@Override
	public int addSection(Section section) {
		return sectionDao.addSection(section);
	}

	@Override
	public void makeSectionNameFolder(String workingDir, String sectionName) {
		System.out.println("Current working directory : " + workingDir);
		File pathToMakeSectionDir = new File(workingDir + File.separator
				+ "Documents" + File.separator + sectionName);
		if (!pathToMakeSectionDir.exists()) {
			pathToMakeSectionDir.mkdirs();
			System.out
					.println("--------------- Section Directory Made -------------");
		}
	}

	@Override
	public String getSectionNameBySectionId(Integer sectionId) {
		return sectionDao.getSectionNameBySectionId(sectionId);
	}

	@Override
	public int getTotalSections() {

		return sectionDao.getTotalSections();
	}

}
