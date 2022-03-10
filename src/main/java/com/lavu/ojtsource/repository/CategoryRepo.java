package com.lavu.ojtsource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lavu.ojtsource.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

	List<Category> findByNameContaining(String name);
}
