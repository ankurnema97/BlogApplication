package com.ankur.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min=4, message="Atleast 4 characters")
	private String name;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
}
