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
import com.yash.tdms.service.DocumentService;
import com.yash.tdms.service.MemberService;

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
	private DocumentService documentService;

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
				memberService.getNonActiveMembers());
		return "registrationRequestPage";
	}

	@RequestMapping("/activateMember")
	public void activateMember(@RequestParam("memberId") int memberId,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		memberService.activateMember(memberId);
		response.getWriter().append("");
	}

	@RequestMapping("/changeRole")
	public void changeRole(@RequestParam("memberId") int memberId,
			@RequestParam("role") int role, HttpServletResponse response,
			ModelMap modelMap) {
		memberService.changeRole(memberId, role);
	}

	@RequestMapping("/shiftDocumentsAccordingToTrainer")
	public String shiftDocumentsAccordingToTrainer(HttpSession session,
			ModelMap modelMap) {
		modelMap.addAttribute("trainers", memberService.getAllTrainers());

		return "shiftDocumentsAccordingToTrainerPage";
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
		System.out.println(fromTrainerId + "  " + toTrainerId + "  "
				+ documentsId);
		if (fromTrainerId == toTrainerId) {
			response.getWriter().append("bothSame");
			return;
		}
		documentService.shiftDocumentsByTrainer(fromTrainerId, toTrainerId,
				documentsId);
		response.getWriter().append("");

	}
}
