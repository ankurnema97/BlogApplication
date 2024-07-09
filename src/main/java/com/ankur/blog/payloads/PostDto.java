package com.ankur.blog.payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.ankur.blog.payloads.CategoryDto;
import com.ankur.blog.payloads.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private int id;
	
	@NotEmpty
	@Size(min=5, message="Atleast 5 characters")
	private String post_title;
	
	@Size(min=5, message="Atleast 50 characters")
	private String post_Des;
	
    private String Post_imagename;
	
	private Date Post_date;
	
	private CategoryDto category;
	
	private UserDto user;
}
