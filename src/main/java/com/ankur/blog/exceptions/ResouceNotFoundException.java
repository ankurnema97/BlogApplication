package com.ankur.blog.exceptions;

public class ResouceNotFoundException extends RuntimeException {
	String resource;
	String field;
	long field_value;
	
	public ResouceNotFoundException(String resource, String field, long field_value) {
		super(String.format("%s is not found with %s = %s",resource,field,field_value));
		this.resource = resource;
		this.field = field;
		this.field_value = field_value;
	}
	
	
}
