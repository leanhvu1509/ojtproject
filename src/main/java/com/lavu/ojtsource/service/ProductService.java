package com.lavu.ojtsource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lavu.ojtsource.model.Product;

public interface ProductService {

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<Product> findById(Long id);

	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	<S extends Product> S save(S entity);

	List<Product> findByNameContaining(String name);

}
