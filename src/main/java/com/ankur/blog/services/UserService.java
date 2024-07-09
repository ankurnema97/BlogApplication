package com.ankur.blog.services;

import java.util.List;

import com.ankur.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
}
