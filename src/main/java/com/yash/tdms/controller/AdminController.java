package com.yash.tdms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.yash.tdms.model.Document;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.BatchService;
import com.yash.tdms.service.DocumentService;
import com.yash.tdms.service.MemberService;
import com.yash.tdms.service.SectionService;

/**
 * 
 * @author goyal.ayush
 *
 */
@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private BatchService batchService;

	@RequestMapping("/admin")
	public String forwardToTraineePage(HttpSession session, ModelMap modelMap) {
		System.out
				.println("ADMIN --------------------------------------------------------------------------------");
		if (session.getAttribute("loggedInUser") == null
				|| ((Member) session.getAttribute("loggedInUser")).getRole() != 1) {
			return "failedAuthentication";
		}
		return "admin";
	}

	@RequestMapping("/registrationRequest")
	public String registrationRequest(HttpSession session, ModelMap modelMap) {
		modelMap.addAttribute("nonActiveMembers",
				memberService.getNonRegisteredMembers());
		return "registrationRequestPage";
	}

	@RequestMapping("/registerMember")
	public void registerMember(@RequestParam("memberId") int memberId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		memberService.registerMember(memberId);
		response.getWriter().append("");
	}

	@RequestMapping("/changeRole")
	public void changeRole(@RequestParam("memberId") int memberId,
			@RequestParam("role") int role, HttpServletResponse response,
			ModelMap modelMap) {
		memberService.changeRole(memberId, role);
	}

	@RequestMapping("/shiftDocumentsAccordingToAdminPage")
	public String shiftDocumentsAccordingToTrainer(HttpSession session,
			ModelMap modelMap) {
		modelMap.addAttribute("trainers", memberService.getAllTrainers());

		return "shiftDocumentsAccordingToAdminPage";
	}

	@RequestMapping("/getListOfDocumentsByTrainer")
	public void getListOfDocumentsByTrainer(
			@RequestParam("memberId") int memberId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		List<Document> documents = documentService
				.getAllDocumentsByUserId(memberId);
		String jsonData = new Gson().toJson(documents);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);
	}

	@RequestMapping(value = "/copyDocumentsByTrainer")
	public void copyDocumentsByTrainer(
			@RequestParam("fromTrainerId") int fromTrainerId,
			@RequestParam("toTrainerId") int toTrainerId,
			@RequestParam("documentsId") List<Integer> documentsId,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		if (fromTrainerId == toTrainerId) {
			response.getWriter().append("bothSame");
			return;
		}
		documentService.shiftDocumentsByTrainer(fromTrainerId, toTrainerId,
				documentsId);
		response.getWriter().append("");

	}

	@RequestMapping("/showDocumentsAdminPage")
	public String showDocumentsAdminPage(HttpSession session, ModelMap modelMap) {
		/* modelMap.addAttribute("trainers", memberService.getAllTrainers()); */
		modelMap.addAttribute("batches", batchService.getAllBatches());
		modelMap.addAttribute("trainers", memberService.getAllTrainers());
		return "showDocumentsAdminPage";
	}

	@RequestMapping("/getDocumentsBasedOnTrainerAndBatch")
	public String getDocumentsBasedOnTrainerAndBatch(
			@RequestParam("batchId") int batchId,
			@RequestParam("memberId") int memberId,
			@RequestParam("statusOfDocuments") String statusOfDocuments,
			HttpSession session, ModelMap modelMap) {
		/* modelMap.addAttribute("trainers", memberService.getAllTrainers()); */
		modelMap.addAttribute("sections", sectionService.getAllSections());
		modelMap.addAttribute("batches", batchService.getAllBatches());
		modelMap.addAttribute("trainers", memberService.getAllTrainers());

		if (statusOfDocuments.equals("all"))
			modelMap.addAttribute("documents", documentService
					.getAllDocumentsByBatchIdAndMemberId(batchId, memberId));
		else
			modelMap.addAttribute("documents", documentService
					.getFavouriteDocumentsByBatchIdAndMemberId(batchId,
							memberId));
		return "showDocumentsAdminPage";
	}

	@RequestMapping("/shiftBatchOfUserPage")
	public String shiftBatchOfUserPage(HttpSession session, ModelMap modelMap) {
		/* modelMap.addAttribute("trainers", memberService.getAllTrainers()); */
		modelMap.addAttribute("batches", batchService.getAllBatches());
		// modelMap.addAttribute("trainers", memberService.getAllTrainers());
		return "shiftBatchOfUserPage";
	}

	@RequestMapping("/getMembersByBatchId")
	public void getMembersByBatchId(@RequestParam("batchId") int batchId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		List<Member> members = memberService.getAllMembersByBatchId(batchId);
		String jsonData = new Gson().toJson(members);
		response.setContentType("application/json");
		response.getWriter().append(jsonData);
	}

	@RequestMapping(value = "/shiftMemberByBatch")
	public void shiftMemberByBatch(
			@RequestParam("fromBatchId") int fromBatchId,
			@RequestParam("memberId") int memberId,
			@RequestParam("toBatchId") int toBatchId, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (fromBatchId == toBatchId) {
			response.getWriter().append("bothBatchSame");
			return;
		}
		memberService.shiftMemberByBatch(fromBatchId, toBatchId, memberId);
		response.getWriter().append("");
	}

	@RequestMapping("/memberActivateDeactivate")
	public String memberActivateDeactivate(HttpSession session,
			ModelMap modelMap) {
		/* modelMap.addAttribute("trainers", memberService.getAllTrainers()); */
		modelMap.addAttribute("batches", batchService.getAllBatches());
		// modelMap.addAttribute("trainers", memberService.getAllTrainers());
		return "memberActivateDeactivate";
	}

	@RequestMapping("/setMemberActivateDeactivateStatus")
	public String setMemberActivateDeactivateStatus(
			@RequestParam("roleId") int roleId,
			@RequestParam(value = "batchId", required = false, defaultValue = "-1") int batchId,
			HttpSession session, ModelMap modelMap) {
		/* modelMap.addAttribute("trainers", memberService.getAllTrainers()); */

		System.out.println(roleId + "      " + batchId);
		modelMap.addAttribute("batches", batchService.getAllBatches());

		if (roleId == 2)
			modelMap.addAttribute("members", memberService.getAllTrainers());
		else if (roleId == 3)
			modelMap.addAttribute("members",
					memberService.getAllTraineesByBatchId(batchId));
		return "memberActivateDeactivate";
	}

	@RequestMapping("/memberIsActive")
	public void memberIsActive(@RequestParam("memberId") int memberId,
			@RequestParam(value = "status") boolean status,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		memberService.setMemberIsActive(memberId, status);
		response.getWriter().append("");
	}

}
