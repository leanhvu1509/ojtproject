package com.lavu.ojtsource.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import com.lavu.ojtsource.dto.ProductDTO;
import com.lavu.ojtsource.model.Category;
import com.lavu.ojtsource.model.Product;
import com.lavu.ojtsource.service.CategoryService;
import com.lavu.ojtsource.service.ProductService;
import com.lavu.ojtsource.service.StorageService;

@Controller
@RequestMapping("admin/product")
public class ProductController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ModelMapper modelMapper;

	@ModelAttribute("categories")
	public List<CategoryDTO> getCategories() {
		return categoryService.findAll().stream().map(cate -> modelMapper.map(cate, CategoryDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping(value = { "", "search" })
	public String viewAll(Model model, @RequestParam(name = "keyword", required = false) String name) {
		List<Product> results = null;
		if (StringUtils.hasText(name)) {
			results = productService.findByNameContaining(name);
		} else {
			results = productService.findAll();
		}
		model.addAttribute("products", results);
		return "admin/product/list";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("product", new ProductDTO());
		return "admin/product/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
		ProductDTO dto = new ProductDTO();
		Optional<Product> opt = productService.findById(id);
		if (opt.isPresent()) {
			Product entity = opt.get();
			modelMapper.map(entity, dto);
			dto.setCategory_id(entity.getCategory().getId());
			dto.setIsEdit(true);
			model.addAttribute("product", dto);
			return "admin/product/addOrEdit";
		}
		attributes.addFlashAttribute("message", "Product not found!");
		return "redirect:/admin/product";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
		boolean isExist = productService.existsById(id);
		if(!isExist) {
			attributes.addFlashAttribute("message", "Delete not found");
			return "redirect:/admin/product";
		}
		productService.deleteById(id);
		attributes.addFlashAttribute("message", "Delete successfully");
		return "redirect:/admin/product";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(@ModelAttribute("product") ProductDTO dto, RedirectAttributes attributes) {
		Product entity = new Product();
		modelMapper.map(dto, entity);
		
		Category cate = new Category();
		cate.setId(dto.getCategory_id());
		entity.setCategory(cate);
		
		if(!dto.getImageFile().isEmpty()) {
			UUID uuid =UUID.randomUUID();
			String idImage = uuid.toString();
			
			entity.setImage(storageService.getStoredFilename(dto.getImageFile(), idImage));
			storageService.store(dto.getImageFile(), entity.getImage());
		}
		productService.save(entity);
		attributes.addFlashAttribute("message", "Product is saved!");
		return "redirect:/admin/category";
	}
	@GetMapping("images/{filename:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\""+file.getFilename()+"\"").body(file);
	}
}
