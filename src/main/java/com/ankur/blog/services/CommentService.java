package com.ankur.blog.services;

import com.ankur.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto cdto, Integer postId);
	void deleteComment(Integer id);
}
