package com.ankur.blog.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private int id;
	
	@NotEmpty
	private String content;
}
