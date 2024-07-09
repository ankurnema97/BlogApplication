package com.ankur.blog.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.blog.config.AppConstants;
import com.ankur.blog.payloads.PostDto;
import com.ankur.blog.payloads.PostResponse;
import com.ankur.blog.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService post_service;
	
	@PostMapping("/{id}/{category_id}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto pdto, @PathVariable Integer category_id, @PathVariable Integer id) {
		PostDto dto=post_service.createPost(pdto, category_id, id);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto pdto, @PathVariable int id) {
		PostDto dto=post_service.updatePost(pdto,id);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
		PostDto dto=post_service.getPostById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNo", defaultValue=AppConstants.pageNo, required=false) Integer pageNo, @RequestParam(value="pageSize", defaultValue=AppConstants.pageSize, required=false) Integer pageSize, @RequestParam(value="sortBy", defaultValue=AppConstants.sortBy, required=false) String sortBy) {
		PostResponse postResponse=post_service.getAllPost(pageNo, pageSize, sortBy);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<String> deletePost(@PathVariable Integer id) {
		post_service.deletePost(id);
		return new ResponseEntity<>("Deleted Post Sucessfully", HttpStatus.OK);
	}
	
	@GetMapping("/title/{category_title}")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable("category_title") String category_title, @RequestParam(value="pageNo", defaultValue=AppConstants.pageNo, required=false) Integer pageNo, @RequestParam(value="pageSize", defaultValue=AppConstants.pageSize, required=false) Integer pageSize, @RequestParam(value="sortBy", defaultValue=AppConstants.sortBy, required=false) String sortBy) {
		PostResponse postResponse=post_service.getPostByCategory(category_title, pageNo, pageSize, sortBy);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable int id, @RequestParam(value="pageNo", defaultValue=AppConstants.pageNo, required=false) Integer pageNo, @RequestParam(value="pageSize", defaultValue=AppConstants.pageSize, required=false) Integer pageSize, @RequestParam(value="sortBy", defaultValue=AppConstants.sortBy, required=false) String sortBy) {
		PostResponse postResponse=post_service.getPostByUser(id, pageNo, pageSize, sortBy);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
}
