package com.ankur.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ankur.blog.entities.User;
import com.ankur.blog.exceptions.ResouceNotFoundException;
import com.ankur.blog.payloads.UserDto;
import com.ankur.blog.repositories.UserRepo;
import com.ankur.blog.services.UserService;

@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserRepo user_repo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		User u=dtoToUser(userdto);
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		User saved=user_repo.save(u);
		return usertoDto(saved);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer id) {
		User u=user_repo.findById(id).orElseThrow(()-> new ResouceNotFoundException("User","id",id));
		u.setName(userdto.getName());
		u.setEmail(userdto.getEmail());
		u.setPassword(userdto.getPassword());
		
		User saved=user_repo.save(u);
		return usertoDto(saved);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User u=user_repo.findById(id).orElseThrow(() -> new ResouceNotFoundException("User","id",id));
		return usertoDto(u);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> ul=user_repo.findAll();
		List<UserDto> ul1=ul.stream().map(user -> usertoDto(user)).collect(Collectors.toList());
		return ul1;
	}

	@Override
	public void deleteUser(Integer id) {
		User u=user_repo.findById(id).orElseThrow(() -> new ResouceNotFoundException("User","id",id));
		user_repo.delete(u);
	}
	
	public User dtoToUser(UserDto dto) {
		User u=modelmapper.map(dto, User.class);
		return u;
	}
	
	public UserDto usertoDto(User u) {
		UserDto userdto=modelmapper.map(u, UserDto.class);
		return userdto;
	}

}
