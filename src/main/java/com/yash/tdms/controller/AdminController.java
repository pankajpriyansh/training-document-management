package com.yash.tdms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yash.tdms.model.Member;
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

}
