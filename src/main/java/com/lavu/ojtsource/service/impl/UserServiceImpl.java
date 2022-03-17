package com.lavu.ojtsource.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lavu.ojtsource.model.User;
import com.lavu.ojtsource.repository.UserRepo;
import com.lavu.ojtsource.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User login(String username,String password) {
		Optional<User> optExist = userRepo.findByUsername(username);
		if(optExist.isPresent() && passwordEncoder.matches(password, optExist.get().getPassword())) {
			optExist.get().setPassword("");
			return optExist.get();
		}
		return null;
	}
	
	@Override
	public <S extends User> S save(S entity) {
		Optional<User> existed = findById(entity.getId());
		if (existed.isPresent()) {
			if (StringUtils.hasText(entity.getPassword())) {
				entity.setPassword(existed.get().getPassword());
			} else {
				entity.setPassword(passwordEncoder.encode(entity.getPassword()));
			}
		} else {
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		}
		return userRepo.save(entity);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepo.findAll(pageable);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}

}
