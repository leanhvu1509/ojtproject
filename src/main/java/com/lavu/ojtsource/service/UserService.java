package com.lavu.ojtsource.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lavu.ojtsource.model.User;

public interface UserService {

	void deleteById(Long id);

	Optional<User> findById(Long id);

	List<User> findAll();

	Page<User> findAll(Pageable pageable);

	<S extends User> S save(S entity);

	User login(String username, String password);

}
