package com.yash.tdms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String forwardToTrainerPage(HttpSession session, ModelMap modelMap) {
		/** set logged in user to get its id on further pages */
		if (session.getAttribute("loggedInUser") == null
				|| ((Member) session.getAttribute("loggedInUser")).getRole() != 2) {
			return "failedAuthentication";
		}

		List<Document> documents = documentService
				.getAllDocumentsByUserId(((Member) session
						.getAttribute("loggedInUser")).getId());
		System.out.println(((Member) session.getAttribute("loggedInUser"))
				.getId());
		System.out.println(documents);
		modelMap.addAttribute("totalSections",
				sectionService.getTotalSections());
		modelMap.addAttribute("totalCategories",
				categoryService.getTotalCategories());
		modelMap.addAttribute("totalDocuments", documents.size());
		modelMap.addAttribute("sections", sectionService.getAllSections());
		modelMap.addAttribute("documents", documents);
		return "trainer";
	}

	/*
	 * ------------------------------------------------------------------
	 * --------- Section Related Mappings Start--------------
	 * ------------------------------------------------------------------
	 */
	@RequestMapping("/getSections")
	public void getSections(HttpServletResponse response, Model model)
			throws IOException {
		List<Section> sections = sectionService.getAllSections();
		String jsonOfSections = new Gson().toJson(sections);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfSections);
	}

	@RequestMapping("/saveSection")
	public void saveSection(@RequestParam("sectionName") String sectionName,
			HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (sectionName.contains(" ")) {
			response.getWriter().append("containSpace");
			return;
		}
		if (sectionService.checkIfSectionExists(sectionName)) {
			response.getWriter().append("sectionExists");
			return;
		}
		Section section = makeSectionObjectWithGievnDetails(sectionName,
				session);
		int sectionId = sectionService.addSection(section);
		section.setId(sectionId);
		String workingDir = request.getServletContext().getRealPath("");
		sectionService.makeSectionNameFolder(workingDir, sectionName);
		List listOfSectionObjectAndTotalSections = getListOfSectionObjectAndTotalSections(section);
		String jsonOfSection = new Gson()
				.toJson(listOfSectionObjectAndTotalSections);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfSection);
	}

	private List getListOfSectionObjectAndTotalSections(Section section) {
		List listOfSectionObjectAndTotalSections = new ArrayList();
		listOfSectionObjectAndTotalSections.add(section);
		listOfSectionObjectAndTotalSections.add(sectionService
				.getTotalSections());
		return listOfSectionObjectAndTotalSections;
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

	/*
	 * ------------------------------------------------------------------
	 * --------- Categories Related Mapping Start--------------
	 * ------------------------------------------------------------------
	 */
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

	@RequestMapping("/saveCategory")
	public void saveCategory(@RequestParam("categoryName") String categoryName,
			@RequestParam("sectionId") Integer sectionId, HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		if (categoryName.contains(" ")) {
			response.getWriter().append("containSpace");
			return;
		}
		if (categoryService.checkIfCategoryExists(categoryName, sectionId)) {
			response.getWriter().append("categoryExists");
			return;
		}
		Category category = makeCategoryObjectWithGievnDetails(categoryName,
				sectionId, session);
		int categoryId = categoryService.addCategory(category);
		String workingDir = request.getServletContext().getRealPath("");
		categoryService.makeCategoryNameFolder(workingDir, sectionId,
				categoryName);
		category.setId(categoryId);
		List listOfCategoryObjectAndTotalCategories = getListOfCategoryObjectAndTotalCategories(category);
		String jsonOfCategory = new Gson()
				.toJson(listOfCategoryObjectAndTotalCategories);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfCategory);
	}

	private List getListOfCategoryObjectAndTotalCategories(Category category) {
		List listOfCategoryObjectAndTotalCategories = new ArrayList();
		listOfCategoryObjectAndTotalCategories.add(category);
		listOfCategoryObjectAndTotalCategories.add(categoryService
				.getTotalCategories());
		return listOfCategoryObjectAndTotalCategories;
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

	/*
	 * ------------------------------------------------------------------
	 * --------- Documents Related Mapping Start--------------
	 * ------------------------------------------------------------------
	 */
	@RequestMapping(value = "/saveDocument", method = RequestMethod.POST)
	public void saveDocument(@ModelAttribute("document") Document document,
			@RequestParam("fileToUpload") MultipartFile file,
			@RequestParam("section") String sectionName,
			@RequestParam("category") String categoryName, HttpSession session,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out
				.println("---------------------------- Inside save Dopcument method --------------------------");
		if (!file.getOriginalFilename().endsWith(".pdf")) {
			response.getWriter().append("FileNotPDF");
			return;
		}
		document.setFilePath(sectionName + "/" + categoryName + "/"
				+ file.getOriginalFilename());
		document.setUser_id(((Member) session.getAttribute("loggedInUser"))
				.getId());
		String workingDir = request.getServletContext().getRealPath("");
		documentService.uploadFile(file, workingDir, document.getFilePath());
		documentService.addDocument(document);
		String jsonOfDocuments = new Gson().toJson(documentService
				.getTotalDocuments(((Member) session
						.getAttribute("loggedInUser")).getId()));
		response.setContentType("application/json");
		response.getWriter().append(jsonOfDocuments);
	}

	@RequestMapping(value = "/deleteDocument")
	public void deleteDocument(@RequestParam("documentId") String documentId,
			HttpServletResponse response) throws IOException {
		documentService.deleteDocumentById(Integer.parseInt(documentId));
		response.getWriter().append("documentDelleted");
	}

	@RequestMapping(value = "/getDocument")
	public void getDocument(@RequestParam("documentId") String documentId,
			HttpServletResponse response, Model model) throws IOException {
		System.out.println("Got PAth Variable - > " + documentId);

		Document document = documentService.getDocumentById(Integer
				.parseInt(documentId));
		String jsonOfDocument = new Gson().toJson(document);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfDocument);
	}

	@RequestMapping(value = "/editDocument")
	public void editDocument(
			@RequestParam("hiddenDocumentId") String documentId,
			@RequestParam("description") String description,
			@RequestParam("name") String name, HttpServletResponse response,
			Model model) throws IOException {
		System.out.println(documentId + "  " + name + "  " + description);
		documentService.updateDocument(Integer.parseInt(documentId), name,
				description);
	}

	@RequestMapping(value = "/markDocumentRead")
	public void markDocumentRead(@RequestParam("documentId") String documentId,
			HttpSession session, HttpServletResponse response, Model model)
			throws IOException {
		int user_id = ((Member) session.getAttribute("loggedInUser")).getId();
		if (documentService.checkIfStatusAlreadyRead(
				Integer.parseInt(documentId), user_id)) {
			documentService.updateReadEntryOfDocument(
					Integer.parseInt(documentId), user_id);
			response.getWriter().append("UpdateReadEntryDone");
			return;
		} else {
			documentService.doEntryAsReadForThisDocument(
					Integer.parseInt(documentId), user_id);
			response.getWriter().append("ReadEntryDone");
		}

	}

	@RequestMapping(value = "/checkDocumentStatus")
	public void checkDocumentStatus(
			@RequestParam("documentIdOfReadStatus") String documentId,
			@RequestParam("memberId") String memberId, HttpSession session,
			HttpServletResponse response, Model model) throws IOException {
		System.out.println(documentId + "   " + memberId);
		Map<String, Object> map = documentService.getDocumentReadStatus(
				Integer.parseInt(documentId), Integer.parseInt(memberId));
		if (map == null) {
			response.getWriter().append("UnRead");
		} else {
			List list = new ArrayList();
			list.add(map.get("STATUS"));
			list.add(map.get("createddate"));
			list.add(map.get("modifieddate"));
			String json = new Gson().toJson(list);
			response.setContentType("application/json");
			response.getWriter().append(json);
		}
	}

	/*
	 * ------------------------------------------------------------------
	 * --------- On Change Of Status , Show / Hide --------------
	 * ------------------------------------------------------------------
	 */
	@RequestMapping(value = "/onStatusChangeOfShowHide")
	public void onStatusChangeEventHandler(
			@RequestParam("documentId") String documentId,
			@RequestParam("status") String status, HttpServletResponse response)
			throws IOException {
		if (status.equalsIgnoreCase("show"))
			documentService.changeStatusOfDocumentByDocumentId(
					Integer.parseInt(documentId), 1);
		else
			documentService.changeStatusOfDocumentByDocumentId(
					Integer.parseInt(documentId), 2);
		response.getWriter().append("completed");
	}

}
