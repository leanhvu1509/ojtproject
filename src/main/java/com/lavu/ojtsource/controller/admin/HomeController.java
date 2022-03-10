package com.lavu.ojtsource.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {

	@GetMapping("")
	public String viewHomePage() {
		return "admin/home/index";
	}
}
