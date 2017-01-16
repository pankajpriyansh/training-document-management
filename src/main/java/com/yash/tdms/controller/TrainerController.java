package com.yash.tdms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yash.tdms.model.Category;
import com.yash.tdms.model.Document;
import com.yash.tdms.model.Member;
import com.yash.tdms.model.Section;
import com.yash.tdms.service.CategoryService;
import com.yash.tdms.service.DocumentService;
import com.yash.tdms.service.SectionService;

/**
 * TrainerController - All the requests regarding trainer pages are mapped using
 * this controller
 * 
 * @author goyal.ayush
 *
 */
@Controller
public class TrainerController {

	@Autowired
	private SectionService sectionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DocumentService documentService;

	@RequestMapping("/trainer")
	public String forwardToTrainerPage(HttpSession session) {

		/** set logged in user to get its id on further pages */
		Member member = new Member();
		member.setId(1);
		session.setAttribute("loggedInUser", member);

		List<Document> documents = documentService
				.getAllDocumentsByUserId(((Member) session
						.getAttribute("loggedInUser")).getId());
		// attach documents to request so that they display on browser
		return "trainer";
	}

	@RequestMapping("/createDocumentForm")
	public String createDocumentPage(Model model, HttpSession session) {
		model.addAttribute("sections", sectionService.getAllSections());
		return "createDocumentForm";
	}

	@RequestMapping("/getCategories")
	public void getCategories(@RequestParam("sectionId") int sectionId,
			HttpServletResponse response, Model model) throws IOException {
		System.out.println("sectionId Value - > " + sectionId);
		List<Category> categories = categoryService
				.getCategoriesBySectionId(sectionId);
		String jsonOfCategories = new Gson().toJson(categories);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfCategories);
	}

	@RequestMapping("/saveDocument")
	public String saveDocument(@ModelAttribute("document") Document document,
			@RequestParam("fileToUpload") MultipartFile file,
			@RequestParam("section") String sectionName,
			@RequestParam("category") String categoryName, HttpSession session,
			HttpServletRequest request) {
		System.out.println("Inside Save Documenyt --------------------------");
		document.setFilePath(sectionName + "/" + categoryName + "/"
				+ file.getOriginalFilename());
		document.setUser_id(((Member) session.getAttribute("loggedInUser"))
				.getId());
		String workingDir = request.getServletContext().getRealPath("");
		System.out.println("working directory "+workingDir);
		documentService.uploadFile(file, workingDir, document.getFilePath());
		documentService.addDocument(document);

		// InComplete, need to upload file
		return "trainer";
		/* return null; */
	}

	@RequestMapping("/saveSection")
	public void saveSection(@RequestParam("sectionName") String sectionName,
			HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Section section = makeSectionObjectWithGievnDetails(sectionName,
				session);
		int sectionId = sectionService.addSection(section);
		section.setId(sectionId);
		String workingDir = request.getServletContext().getRealPath("");
		sectionService.makeSectionNameFolder(workingDir, sectionName);
		String jsonOfSection = new Gson().toJson(section);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfSection);
	}

	private Section makeSectionObjectWithGievnDetails(String sectionName,
			HttpSession session) {
		Section section = new Section();
		section.setName(sectionName);
		section.setCreatedBy(((Member) session.getAttribute("loggedInUser"))
				.getId());
		section.setModifiedBy(((Member) session.getAttribute("loggedInUser"))
				.getId());
		return section;
	}

	@RequestMapping("/saveCategory")
	public void saveCategory(@RequestParam("categoryName") String categoryName,
			@RequestParam("sectionId") Integer sectionId, HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		Category category = makeCategoryObjectWithGievnDetails(categoryName,
				sectionId, session);
		int categoryId = categoryService.addCategory(category);
		String workingDir = request.getServletContext().getRealPath("");
		categoryService.makeCategoryNameFolder(workingDir, sectionId,
				categoryName);
		category.setId(categoryId);
		String jsonOfCategory = new Gson().toJson(category);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfCategory);
	}

	private Category makeCategoryObjectWithGievnDetails(String categoryName,
			Integer sectionId, HttpSession session) {
		Category category = new Category();
		category.setName(categoryName);
		category.setSection_id(sectionId);
		category.setCreatedBy(((Member) session.getAttribute("loggedInUser"))
				.getId());
		category.setModifiedBy(((Member) session.getAttribute("loggedInUser"))
				.getId());
		return category;
	}

}
