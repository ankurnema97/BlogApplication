package com.ankur.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankur.blog.entities.Category;
import com.ankur.blog.entities.User;
import com.ankur.blog.exceptions.ResouceNotFoundException;
import com.ankur.blog.payloads.CategoryDto;
import com.ankur.blog.repositories.CategoryRepo;
import com.ankur.blog.services.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo cate_repo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto c_dto) {
		Category cat = CategoryDtoToCategory(c_dto);
		Category saved=cate_repo.save(cat);
		return CategoryToCatDto(saved);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto c_dto, Integer id) {
		Category cat=cate_repo.findById(id).orElseThrow(()-> new ResouceNotFoundException("Category","id",id));
		cat.setCategory_title(c_dto.getCategory_title());
		cat.setCategory_des(c_dto.getCategory_des());	
		Category saved=cate_repo.save(cat);
		return CategoryToCatDto(saved);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category cat=cate_repo.findById(id).orElseThrow(()-> new ResouceNotFoundException("Category","id",id));
		return CategoryToCatDto(cat);
	}

	@Override
	public void deleteCategoryById(Integer id) {
		Category cat = cate_repo.findById(id).orElseThrow(()-> new ResouceNotFoundException("Category","id",id));
		cate_repo.delete(cat);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> cl=cate_repo.findAll();
		List<CategoryDto> cld = cl.stream().map(cat -> CategoryToCatDto(cat)).collect(Collectors.toList());
		return cld;
	}
	
	@Override
	public List<CategoryDto> getCategoryByTitle(String title) {
		List<Category> cl=cate_repo.findCategoryByTitle(title);
		List<CategoryDto> cld = cl.stream().map(cat -> CategoryToCatDto(cat)).collect(Collectors.toList());
		return cld;
	}
	
	public CategoryDto CategoryToCatDto(Category c) {
		CategoryDto dto=modelmapper.map(c, CategoryDto.class);
		return dto;
	}
	
	public Category CategoryDtoToCategory(CategoryDto dto) {
		Category cate=modelmapper.map(dto, Category.class);
		return cate;
	}
	
}
