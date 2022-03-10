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

import com.lavu.ojtsource.dto.CategoryDTO;
import com.lavu.ojtsource.model.Category;
import com.lavu.ojtsource.service.CategoryService;

@Controller
@RequestMapping("admin/category")
public class CategoryController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = { "", "search" })
	public String search(Model model, @RequestParam(name = "keyword", required = false) String name) {
		List<Category> results = null;
		if (StringUtils.hasText(name)) {
			results = categoryService.findByNameContaining(name);
		} else {
			results = categoryService.findAll();
		}
		model.addAttribute("categories", results);
		return "admin/category/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "admin/category/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
		CategoryDTO dto = new CategoryDTO();
		Optional<Category> opt = categoryService.findById(id);
		if (opt.isPresent()) {
			Category entity = opt.get();
			modelMapper.map(entity, dto);
//			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("category", dto);
			return "admin/category/addOrEdit";
		}
		attributes.addFlashAttribute("message", "Category not found!");
		return "redirect:/admin/category";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
		boolean isExist = categoryService.existsById(id);
		if(!isExist) {
			attributes.addFlashAttribute("message", "Delete not found");
			return "redirect:/admin/category";
		}
		categoryService.deleteById(id);
		attributes.addFlashAttribute("message", "Delete successfully");
		return "redirect:/admin/category";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("category") CategoryDTO dto, RedirectAttributes attributes) {
		Category entity = new Category();
		modelMapper.map(dto, entity);
//		BeanUtils.copyProperties(dto, entity);
		categoryService.save(entity);
		attributes.addFlashAttribute("message", "Category is saved!");
		return "redirect:/admin/category";
	}

}
