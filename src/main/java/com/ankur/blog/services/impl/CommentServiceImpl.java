package com.ankur.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ankur.blog.entities.Comment;
import com.ankur.blog.entities.Post;
import com.ankur.blog.exceptions.ResouceNotFoundException;
import com.ankur.blog.payloads.CommentDto;
import com.ankur.blog.repositories.CommentRepo;
import com.ankur.blog.repositories.PostRepo;
import com.ankur.blog.services.CommentService;

public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private CommentRepo comm_repo;
	
	@Autowired
	private PostRepo post_repo;

	@Override
	public CommentDto createComment(CommentDto cdto, Integer postId) {
		Post p=post_repo.findById(postId).orElseThrow(() -> new ResouceNotFoundException("Post","id",postId));
		Comment c=commentdtoToComment(cdto);
		c.setPosts(p);
		Comment saved=comm_repo.save(c);
		return commenttoCommentDto(saved);
	}

	@Override
	public void deleteComment(Integer id) {
		Comment c=comm_repo.findById(id).orElseThrow(()-> new ResouceNotFoundException("Comment","id",id));
		comm_repo.deleteById(id);
	}

	public Comment commentdtoToComment(CommentDto cdto) {
		Comment c=modelmapper.map(cdto, Comment.class);
		return c;
	}
	
	public CommentDto commenttoCommentDto(Comment c) {
		CommentDto dto=modelmapper.map(c, CommentDto.class);
		return dto;
	}
}
