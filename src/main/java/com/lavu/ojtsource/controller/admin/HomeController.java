package com.lavu.ojtsource.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lavu.ojtsource.dto.AdminLoginDTO;
import com.lavu.ojtsource.model.User;
import com.lavu.ojtsource.service.UserService;

@Controller
@RequestMapping("/admin")
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("")
	public String viewHomePage() {
		return "admin/home/index";
	}
	@GetMapping("login")
	public String loginForm(Model model) {
		model.addAttribute("user", new AdminLoginDTO());
		return "admin/home/login";
	}
	@PostMapping("login")
	public String login(Model model,
			@ModelAttribute("user")AdminLoginDTO dto) {
		User entity = userService.login(dto.getUsername(), dto.getPassword());
		if(entity == null) {
			model.addAttribute("message", "error");
			return "admin/home/login";
		}
		session.setAttribute("username", entity.getUsername());
		return "forward:/admin/";
	}
	
}
