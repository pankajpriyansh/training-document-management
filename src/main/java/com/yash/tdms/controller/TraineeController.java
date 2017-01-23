package com.yash.tdms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yash.tdms.model.Document;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.DocumentService;

/**
 * TraineeController - All the requests regarding trainee pages are mapped using
 * this controller
 * 
 * @author goyal.ayush
 *
 */
@Controller
public class TraineeController {

	@Autowired
	private DocumentService documentService;

	@RequestMapping("/trainee")
	public String forwardToTraineePage(HttpSession session, ModelMap modelMap) {
		if (session.getAttribute("loggedInUser") == null
				|| ((Member) session.getAttribute("loggedInUser")).getRole() != 3) {
			return "failedAuthentication";
		}
		List<Document> documents = documentService.getAllActiveDocuments();
		modelMap.addAttribute("listOfDocuments", documents);
		System.out.println(documents);
		System.out.println(documents.size());

		return "trainee";
	}
}
