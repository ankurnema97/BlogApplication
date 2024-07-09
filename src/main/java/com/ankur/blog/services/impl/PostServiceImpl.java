package com.ankur.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ankur.blog.entities.Category;
import com.ankur.blog.entities.Post;
import com.ankur.blog.entities.User;
import com.ankur.blog.exceptions.ResouceNotFoundException;
import com.ankur.blog.payloads.PostDto;
import com.ankur.blog.payloads.PostResponse;
import com.ankur.blog.repositories.CategoryRepo;
import com.ankur.blog.repositories.PostRepo;
import com.ankur.blog.repositories.UserRepo;
import com.ankur.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo post_repo;
	
	@Autowired
	private UserRepo user_repo;
	
	@Autowired
	private CategoryRepo cate_repo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public PostDto createPost(PostDto pdto, Integer cid, Integer uid) {
		Post p=PostDtoToPost(pdto);
		User u=user_repo.findById(uid).orElseThrow(() -> new ResouceNotFoundException("User","id",uid));
		Category c=cate_repo.findById(cid).orElseThrow(() -> new ResouceNotFoundException("Category","id",cid));
		p.setCategory(c);
		p.setUser(u);
		p.setPost_date(new Date());
		p.setPost_imagename("default.png");
		Post saved=post_repo.save(p);
		return PosttoPostDto(saved);
	}

	@Override
	public PostDto updatePost(PostDto pdto, Integer id) {
		Post p = PostDtoToPost(pdto);
		p.setPost_title(pdto.getPost_title());
		p.setPost_Des(pdto.getPost_Des());
		Post saved= post_repo.save(p);
		return PosttoPostDto(saved);
	}

	@Override
	public void deletePost(Integer id) {
		Post p=post_repo.findById(id).orElseThrow(() -> new ResouceNotFoundException("Post","id",id));
		post_repo.deleteById(id);

	}

	@Override
	public PostDto getPostById(Integer id) {
		Post p=post_repo.findById(id).orElseThrow(() -> new ResouceNotFoundException("Post","id",id));
		return PosttoPostDto(p);
	}

	@Override
	public PostResponse getAllPost(int PageNo, int PageSize, String sortBy) {
		Pageable pa=PageRequest.of(PageNo, PageSize, Sort.by(sortBy));
		
		Page<Post> pagepost= post_repo.findAll(pa);
		List<Post> pl=pagepost.getContent();
		List<PostDto> pdl=  pl.stream().map(post -> PosttoPostDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setData(pdl);
		postResponse.setPageNo(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponse getPostByCategory(String cate, int pageNo, int pageSize, String sortBy) {
		Pageable pa=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Post> pagepost=post_repo.getPostByCategory(cate,pa);
		
		List<Post> pl=pagepost.getContent();
		List<PostDto> pdl=pl.stream().map(post -> PosttoPostDto(post)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setData(pdl);
		postResponse.setPageNo(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer uid, int pageNo, int pageSize, String sortBy) {
		Pageable pa=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Post> pagepost=post_repo.getPostByUser(uid, pa);
		
		
		List<Post> pl=pagepost.getContent();
		List<PostDto> pdl= pl.stream().map(post -> PosttoPostDto(post)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setData(pdl);
		postResponse.setPageNo(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		return postResponse;
	}

	public Post PostDtoToPost(PostDto pdto) {
		Post p = modelmapper.map(pdto, Post.class);
		return p;
	}
	
	public PostDto PosttoPostDto(Post p) {
		PostDto pdto= modelmapper.map(p, PostDto.class);
		return pdto;
	}
	
}