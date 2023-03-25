package com.jaydeep.blogapplication.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jaydeep.blogapplication.exception.CategoryException;
import com.jaydeep.blogapplication.exception.PostException;
import com.jaydeep.blogapplication.exception.UserException;

@RestControllerAdvice
public class ExceptionHandlingClass {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorInfo> handleUserException(UserException userException){
		
		ErrorInfo errorInfo = new ErrorInfo();
		
		errorInfo.setErrorMsg(userException.getMessage());
		errorInfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ErrorInfo> handleUserException(CategoryException categoryException){
		
		ErrorInfo errorInfo = new ErrorInfo();
		
		errorInfo.setErrorMsg(categoryException.getMessage());
		errorInfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorInfo> handlePostException(PostException postException){
		
		ErrorInfo errorInfo = new ErrorInfo();
		
		errorInfo.setErrorMsg(postException.getMessage());
		errorInfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
		
		Map<String, String> response = new HashMap<String, String>();
		
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error ->{
			
			String fieldError = error.getDefaultMessage();
			String fieldName = ((FieldError)error).getField();
			
			response.put(fieldName, fieldError);
		});
		
		return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
	}
}
