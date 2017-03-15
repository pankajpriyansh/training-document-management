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
import com.yash.tdms.service.BatchService;
import com.yash.tdms.service.CategoryService;
import com.yash.tdms.service.DocumentService;
import com.yash.tdms.service.MemberService;
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

	@Autowired
	private BatchService batchService;

	@Autowired
	private MemberService memberService;

	@RequestMapping("/trainer")
	public String forwardToTrainerPage(HttpSession session, ModelMap modelMap)
			throws IOException {
		/** set logged in user to get its id on further pages */
		if (session.getAttribute("loggedInUser") == null
				|| ((Member) session.getAttribute("loggedInUser")).getRole() != 2) {
			return "failedAuthentication";
		}
		List<Document> documents = documentService
				.getAllDocumentsByUserId(((Member) session
						.getAttribute("loggedInUser")).getId());
		modelMap.addAttribute("totalSections",
				sectionService.getTotalSections());
		modelMap.addAttribute("totalCategories",
				categoryService.getTotalCategories());
		modelMap.addAttribute("totalDocuments", documents.size());
		modelMap.addAttribute("sections", sectionService.getAllSections());
		modelMap.addAttribute("totalBatches", batchService.getTotalBatches());
		// modelMap.addAttribute("documents", documents);
		modelMap.addAttribute("batches", batchService.getAllBatches());
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
			HttpServletResponse response, ModelMap model) throws IOException {
		System.out.println("sectionId Value - > " + sectionId);
		List<Category> categories = categoryService
				.getCategoriesBySectionId(sectionId);
		model.addAttribute("categoriesAccordingToSection", categories);
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
		if (!file.getOriginalFilename().endsWith(".pdf")) {
			response.getWriter().append("FileNotPDF");
			return;
		}
		if (documentService.documentNameExistsUnderThisBatch(
				document.getBatchId(), document.getName())) {
			response.getWriter().append("alreadyExists");
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
			list.add(map.get("count"));
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

	@RequestMapping(value = "/showDocumentsPage")
	public String showDocumentsPage(HttpSession session, ModelMap modelMap)
			throws IOException {
		List<Document> documents = documentService
				.getAllDocumentsByUserId(((Member) session
						.getAttribute("loggedInUser")).getId());
		modelMap.addAttribute("documents", documents);
		modelMap.addAttribute("batches", batchService.getAllBatches());
		modelMap.addAttribute("sections", sectionService.getAllSections());
		return "showDocumentsPage";
	}

	@RequestMapping(value = "/readStatusOfDocument")
	public String readStatusOfDocument(HttpSession session, ModelMap modelMap)
			throws IOException {
		modelMap.addAttribute("batches", batchService.getAllBatches());
		return "readStatusOfDocumentPage";
	}

	@RequestMapping(value = "/getDocumentsByBatchId")
	public void getDocumentsByBatchId(@RequestParam("batchId") int batchId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {

		List<Document> documents = documentService
				.getAllDocumentsByBatchId(batchId);
		String jsonOfDocuments = new Gson().toJson(documents);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfDocuments);
	}

	@RequestMapping(value = "/checkDocumentReadStatus")
	public String checkDocumentReadStatus(@RequestParam("batchId") int batchId,
			@RequestParam("documentId") int documentId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		List list = documentService.getDocumentReadStautsList(batchId,
				documentId);
		modelMap.addAttribute("documentReadStatusList", list);
		/*
		 * String jsonOfDocumentStatus = new Gson().toJson(list);
		 * response.setContentType("application/json");
		 * response.getWriter().append(jsonOfDocumentStatus);
		 */

		return "readStatusOfDocumentPage";
	}

	@RequestMapping(value = "/getListOfMembersByDocumentsBatchId")
	public void getListOfMembersByDocumentsBatchId(
			@RequestParam("documentId") int documentId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		int batchId = documentService.getBatchIdByDocumentId(documentId);
		List<Member> members = memberService.getAllMembersByBatchId(batchId);
		String jsonOfMembers = new Gson().toJson(members);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfMembers);
	}

	@RequestMapping(value = "/onStatusChangeOfShowHideForSpecificMember")
	public void onStatusChangeOfShowHideForSpecificMember(
			@RequestParam("documentId") String documentId,
			@RequestParam("status") String status,
			@RequestParam("memberId") int memberId, HttpServletResponse response)
			throws IOException {
		/**
		 * for specific member, its entry inserted in database, and after 2 days
		 * , this entry will be automatically deleted
		 */
		documentService.changeStatusOfDocumentByDocumentIdForSpecificMember(
				Integer.parseInt(documentId), memberId);

		response.getWriter().append("completed");
	}

	@RequestMapping(value = "/getSectionCategoryDocumentGraphData")
	public void getSectionCategoryDocumentGraphData(
			@RequestParam("batchId") int batchId, HttpServletResponse response)
			throws IOException {
		List list = sectionService.getSectionCategoryDocumentGraphData(batchId);
		String jsonData = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);
	}

	@RequestMapping(value = "/shiftDocumentsPage")
	public String shiftDocumentsPage(HttpServletResponse response,
			ModelMap modelMap, HttpSession session) throws IOException {
		modelMap.addAttribute("batches", batchService.getAllBatches());
		/*
		 * List<Document> documents = documentService
		 * .getAllDocumentsByUserId(((Member) session
		 * .getAttribute("loggedInUser")).getId());
		 * modelMap.addAttribute("documents", documents);
		 */
		return "shiftDocumentsPage";
	}

	@RequestMapping(value = "/shiftDocumentsByBatch")
	public void shiftDocumentsByBatch(
			@RequestParam("fromBatchId") int fromBatchId,
			@RequestParam("toBatchId") int toBatchId, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (fromBatchId == toBatchId) {
			response.getWriter().append("bothBatchSame");
			return;
		}
		documentService.shiftDocumentsByBatch(fromBatchId, toBatchId,
				((Member) session.getAttribute("loggedInUser")).getId());
		response.getWriter().append("");
	}

	@RequestMapping(value = "/getCategoryFromDocumentId")
	public void getCategoryFromDocumentId(
			@RequestParam("documentId") int documentId,
			HttpServletResponse response) throws IOException {
		Category category = categoryService
				.getCategoryFromDocumentId(documentId);
		System.out.println(category);
		String jsonData = new Gson().toJson(category);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);
	}

	@RequestMapping(value = "/getCategoriesUnderASectionByDocumentId")
	public void getCategoriesUnderASectionByDocumentId(
			@RequestParam("documentId") int documentId,
			HttpServletResponse response) throws IOException {
		List<Category> categories = categoryService
				.getCategoriesUnderASectionByDocumentId(documentId);
		String jsonData = new Gson().toJson(categories);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);
	}

	@RequestMapping(value = "/shiftDocumentsByCategory")
	public void shiftDocumentsByCategory(
			@RequestParam("documentId") int documentId,
			@RequestParam("fromCategory") int fromCategoryId,
			@RequestParam("toCategory") int toCategory,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		String workingDir = request.getServletContext().getRealPath("");
		documentService.shiftDocumentsByCategory(documentId, fromCategoryId,
				toCategory, workingDir);
		response.getWriter().append("");
	}

	@RequestMapping(value = "/getBatchMemberGraphData")
	public void getBatchMemberGraphData(HttpServletResponse response)
			throws IOException {
		List list = memberService.getBatchMemberGraphData();
		String jsonData = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);

	}

	@RequestMapping(value = "/documentsRequest")
	public String documentsRequest(HttpSession session, ModelMap modelMap)
			throws IOException {
		modelMap.addAttribute("requestedDocuments", documentService
				.getRequestedDocumentsData(((Member) session
						.getAttribute("loggedInUser")).getId()));
		return "documentsRequestPage";
	}

	@RequestMapping(value = "/approveRequestForDocument")
	public void approveRequestForDocument(
			@RequestParam("requestId") int requestId,
			@RequestParam("documents") String documents,
			@RequestParam("memberId") int memberId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		List<Integer> documentsId = new ArrayList<Integer>();
		for (String s : documents.substring(1).split(",")) {
			documentsId.add(Integer.parseInt(s));
		}
		documentService.approveRequestForDocument(requestId, documentsId,
				memberId);
		response.getWriter().append("");
	}

	@RequestMapping(value = "/saveReasonForRejectionOfRequest")
	public void saveReasonForRejectionOfRequest(
			@RequestParam("requestId") int requestId,
			@RequestParam("reason") String reason,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		System.out.println(requestId + "       " + reason);
		documentService.saveReasonForRejectionOfRequest(requestId, reason);
		response.getWriter().append("");
	}

	@RequestMapping(value = "/documentRequestReports")
	public String documentRequestReports(HttpSession session, ModelMap modelMap)
			throws IOException {
		/*
		 * System.out.println(documentService
		 * .getRequestedDocumentReportsData(((Member) session
		 * .getAttribute("loggedInUser")).getId()));
		 */

		modelMap.addAttribute("requestedDocumentReports", documentService
				.getRequestedDocumentReportsBasicData(((Member) session
						.getAttribute("loggedInUser")).getId()));

		return "documentRequestReportsPage";
	}

	@RequestMapping(value = "/getRequestedDocumentReportsAdvanceData")
	public void getRequestedDocumentReportsAdvanceData(
			@RequestParam("fromMemberId") int fromMemberId,
			@RequestParam("toMemberId") int toMemberId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		System.out.println(fromMemberId + "       " + toMemberId);
		List list = documentService.getRequestedDocumentReportsAdvanceData(
				fromMemberId, toMemberId);
		String jsonData = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);
	}

}
