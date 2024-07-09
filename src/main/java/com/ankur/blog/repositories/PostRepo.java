package com.ankur.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ankur.blog.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{

	@Query("select p from Post p inner join Category c on p.category.id=c.id where c.category_title LIKE %:m")
	Page<Post> getPostByCategory(@Param("m") String cate, Pageable pa);
	
	@Query("select p from Post p where p.id=m")
	Page<Post> getPostByUser(@Param("m") Integer id, Pageable pa);
}
