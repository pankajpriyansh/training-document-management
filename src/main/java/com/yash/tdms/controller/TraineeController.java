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

		/** set logged in user to get its id on further pages */
		Member member = new Member();
		member.setId(1);
		session.setAttribute("loggedInUser", member);
		// attach documents to request so that they display on browser
		List<Document> documents = documentService.getAllActiveDocuments();
		modelMap.addAttribute("listOfDocuments", documents);
		System.out.println(documents);
		System.out.println(documents.size());

		return "trainee";
	}
}
