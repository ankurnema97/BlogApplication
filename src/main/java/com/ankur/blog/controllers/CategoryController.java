package com.ankur.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.blog.payloads.CategoryDto;
import com.ankur.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService cate_service;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto c_dto) {
		CategoryDto dto = cate_service.createCategory(c_dto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto c_dto, @PathVariable int id) {
		CategoryDto dto=cate_service.updateCategory(c_dto, id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id) {
		CategoryDto dto=cate_service.getCategoryById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/title/{category_title}")
	public ResponseEntity<List<CategoryDto>> getCategoryByTitle(@PathVariable("category_title") String title) {
		List<CategoryDto> dtolist=cate_service.getCategoryByTitle(title);
		return new ResponseEntity<>(dtolist, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		cate_service.deleteCategoryById(id);
		return new ResponseEntity<>("Deleted Category Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> cl=cate_service.getAllCategory();
		return new ResponseEntity<>(cl, HttpStatus.OK);
	}
}
