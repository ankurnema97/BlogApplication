package com.ankur.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ankur.blog.entities.Category;
import com.ankur.blog.payloads.CategoryDto;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.category_title LIKE %:m")
	public List<Category> findCategoryByTitle(@Param("m") String cat_title);
}
