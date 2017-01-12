package com.yash.tdms.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Demo Controller to check the complete MVC flow
 * 
 * @author sharma.pankaj
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String showIndexPage() {
		return "index";
	}

}
