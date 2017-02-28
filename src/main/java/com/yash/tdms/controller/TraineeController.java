package com.yash.tdms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.yash.tdms.model.Document;
import com.yash.tdms.model.Mail;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.DocumentService;
import com.yash.tdms.service.MemberService;

/**
 * TraineeController - All the requests regarding trainee pages are mapped using
 * this controller
 * 
 * @author goyal.ayush
 *
 */
@Controller
public class TraineeController {

	public TraineeController() {

		System.out.println("in trainee controller");
	}

	@Autowired
	private DocumentService documentService;

	@Autowired
	private MemberService memberService;

	private VelocityEngine velocityEngine = new VelocityEngine();

	@RequestMapping("/trainee")
	public String forwardToTraineePage(HttpSession session, ModelMap modelMap) {
		if (session.getAttribute("loggedInUser") == null
				|| ((Member) session.getAttribute("loggedInUser")).getRole() != 3) {
			return "failedAuthentication";
		}
		List<Document> documents = documentService.getAllActiveDocuments(
				((Member) session.getAttribute("loggedInUser")).getBatchId(),
				((Member) session.getAttribute("loggedInUser")).getId());
		modelMap.addAttribute("listOfDocuments", documents);
		System.out.println(documents);
		System.out.println(documents.size());

		return "trainee";
	}

	/*
	 * Function for sending mail by trainee
	 */

	String host;

	@RequestMapping(value = "/MailController")
	public void getMessageDataFromMailFormAndSendMessage(
			@ModelAttribute("mail") Mail mail, HttpServletResponse response,
			HttpSession session) throws IOException {

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream input = classLoader
				.getResourceAsStream("credentialsForMail.properties");
		Properties prop = new Properties();
		prop.load(input);
		String email = prop.getProperty("email");
		String password = prop.getProperty("password");
		System.out.println(email + " ---------------------------- " + password);
		System.out.println("in java controller");
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.Yash.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session sessionForProperties = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email, password);
					}
				});

		try {

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("memberEmailId",
					((Member) session.getAttribute("loggedInUser")).getEmail());
			velocityContext.put("reason", mail.getSubject());
			velocityContext.put("documentRequested", mail.getBody());
			System.out.println("bodyyyy" + mail.getBody());
			velocityEngine.setProperty("resource.loader", "class");
			velocityEngine
					.setProperty("class.resource.loader.class",
							"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

			velocityEngine.init();
			Template template = velocityEngine.getTemplate("emailtemplate.vm");
			StringWriter stringWriter = new StringWriter();
			// System.out.println(mail);
			template.merge(velocityContext, stringWriter);
			System.out.println(mail.getReceiver());
			Message message = new MimeMessage(sessionForProperties);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail.getReceiver()));
			message.setSubject("Request For Document");
			message.setText(stringWriter.toString().replaceAll(",", "   ,   "));
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		response.getWriter().append("");
	}

	/**
	 * this method will fetch list of trainers
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/GetTrainerList")
	public void getListOfTrainers(HttpServletResponse response)
			throws IOException {
		List<Member> listOfTrainers = memberService.getAllTrainers();
		String jsonOfTrainers = new Gson().toJson(listOfTrainers);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfTrainers);

	}

	/**
	 * this method will fetch list of documents
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/GetDocumentList")
	public void getListOfDocumentsByBatchId(HttpServletResponse response,
			HttpSession session) throws IOException {
		List<Document> listOfDocument = documentService
				.getAllDocumentsByBatchId(((Member) session
						.getAttribute("loggedInUser")).getBatchId());
		String jsonOfDocument = new Gson().toJson(listOfDocument);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfDocument);

	}

}