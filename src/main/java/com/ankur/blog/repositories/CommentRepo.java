package com.ankur.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankur.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
