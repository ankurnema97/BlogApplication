package com.ankur.blog.services;

import java.util.List;

import com.ankur.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto c_dto);
	CategoryDto updateCategory(CategoryDto c_dto, Integer id);
	CategoryDto getCategoryById(Integer id);
	List<CategoryDto> getCategoryByTitle(String title);
	void deleteCategoryById(Integer id);
	List<CategoryDto> getAllCategory();
}
