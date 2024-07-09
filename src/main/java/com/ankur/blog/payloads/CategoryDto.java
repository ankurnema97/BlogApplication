package com.ankur.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private int id;
	
	@NotEmpty
	@Size(min=5, message="Must have alteast 5 letters")
	private String category_title;
	
	@NotEmpty
	private String category_des;
}
