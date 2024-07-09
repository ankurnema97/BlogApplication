package com.ankur.blog.services;

import java.util.List;

import com.ankur.blog.payloads.PostDto;
import com.ankur.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto pdto, Integer cid, Integer uid);
	PostDto updatePost(PostDto pdto, Integer id);
	void deletePost(Integer id);
	PostDto getPostById(Integer id);
	PostResponse getAllPost(int PageNo, int PageSize, String sortBy);
	PostResponse getPostByCategory(String cate, int pageNo, int pageSize, String sortBy);
	PostResponse getPostByUser(Integer uid, int pageNo, int pageSize, String sortBy);
}
