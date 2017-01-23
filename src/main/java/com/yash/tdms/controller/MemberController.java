package com.yash.tdms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.MemberService;

@Controller
public class MemberController {

	public MemberController() {
		System.out.println("MemberController object created");
	}

	@Autowired
	private MemberService memberService;

	/*
	 * -------------------------------------------------------------------------
	 * ------------------ Member related Mapping on trainer page
	 * ---------------------------------------------------------------------
	 */

	@RequestMapping(value = "/getMembers")
	public void getMembers(Model model, HttpServletResponse response) throws IOException {
		String jsonOfMembers = new Gson().toJson(memberService.getAllMembers());
		response.setContentType("application/json");
		response.getWriter().append(jsonOfMembers);
	}

	@RequestMapping("/authenticate")
	public void authenticate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, HttpServletResponse response) throws IOException {
		if (memberService.checkForAuthentication(email, password)) {
			Member member = memberService.getMemberByEmail(email);
			session.setAttribute("loggedInUser", member);
			String jsonOfMember = new Gson().toJson(member);
			response.setContentType("application/json");
			response.getWriter().append(jsonOfMember);
		} else {
			response.getWriter().append("errorOfAuthentication");
		}
	}

	@RequestMapping("/saveMember")
	public void saveMember(@ModelAttribute("member") Member member, HttpServletResponse response) throws IOException {
		if (memberService.checkIfEmailExists(member.getEmail())) {
			response.getWriter().append("emailExists");
		} else {
			System.out.println(member);
			memberService.addMember(member);
			response.getWriter().append("Member Created");
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "failedAuthentication";
	}
}
