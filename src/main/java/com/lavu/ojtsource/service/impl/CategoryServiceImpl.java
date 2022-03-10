package com.lavu.ojtsource.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lavu.ojtsource.model.Category;
import com.lavu.ojtsource.repository.CategoryRepo;
import com.lavu.ojtsource.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepo.save(entity);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepo.findAll(pageable);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryRepo.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return categoryRepo.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public List<Category> findByNameContaining(String name) {
		return categoryRepo.findByNameContaining(name);
	}
	
	
	
}
