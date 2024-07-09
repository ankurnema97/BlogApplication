package com.ankur.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<String> resourceException(ResouceNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> dataNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> map = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((err)-> {
			String field=((FieldError)err).getField();
			String msg = err.getDefaultMessage();
			map.put(field, msg);
		});
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
}
