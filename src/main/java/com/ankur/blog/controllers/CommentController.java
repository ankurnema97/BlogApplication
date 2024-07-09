package com.ankur.blog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.blog.payloads.CommentDto;
import com.ankur.blog.services.CommentService;

@RequestMapping("/api/comment")
@RestController
public class CommentController {

	private CommentService comm_service;
	
	@PostMapping("/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto cdto, @PathVariable Integer postId) {
		CommentDto dto=comm_service.createComment(cdto,postId);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer cid) {
		comm_service.deleteComment(cid);
		return new ResponseEntity<>("Delted Comment Successfully", HttpStatus.OK);
	}
	
}
