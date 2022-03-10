package com.lavu.ojtsource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lavu.ojtsource.model.Category;

public interface CategoryService {

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<Category> findById(Long id);

	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);

	<S extends Category> S save(S entity);

	List<Category> findByNameContaining(String name);
	
	
}
