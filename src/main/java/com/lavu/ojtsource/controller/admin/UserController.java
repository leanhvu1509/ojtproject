package com.lavu.ojtsource.controller.admin;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lavu.ojtsource.dto.UserDTO;
import com.lavu.ojtsource.model.User;
import com.lavu.ojtsource.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = { "", "search" })
	public String search(Model model, @RequestParam(name = "keyword", required = false) String name) {
		List<User> results = null;
		if (StringUtils.hasText(name)) {
			//results = userService.findByNameContaining(name);
		} else {
			results = userService.findAll();
		}
		model.addAttribute("users", results);
		return "admin/user/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("user", new UserDTO());
		return "admin/user/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
		UserDTO dto = new UserDTO();
		Optional<User> opt = userService.findById(id);
		if (opt.isPresent()) {
			User entity = opt.get();
			modelMapper.map(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("user", dto);
			return "admin/user/addOrEdit";
		}
		attributes.addFlashAttribute("message", "User not found!");
		return "redirect:/admin/User";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
//		boolean isExist = userService.existsById(id);
//		if(!isExist) {
//			attributes.addFlashAttribute("message", "Delete not found");
//			return "redirect:/admin/User";
//		}
		userService.deleteById(id);
		attributes.addFlashAttribute("message", "Delete successfully");
		return "redirect:/admin/User";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("user") UserDTO dto, RedirectAttributes attributes) {
		User entity = new User();
		modelMapper.map(dto, entity);
		userService.save(entity);
		attributes.addFlashAttribute("message", "User is saved!");
		return "redirect:/admin/user";
	}

	
}
