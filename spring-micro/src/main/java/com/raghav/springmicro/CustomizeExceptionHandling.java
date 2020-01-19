package com.raghav.springmicro;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.net.HttpHeaders;

@ControllerAdvice
@RestController
public class CustomizeExceptionHandling  extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception{
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),"User not found", request.getDescription(false));
		return new ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllUserException(Exception ex, WebRequest request) throws Exception{
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND);
		
	}
	

protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
	ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), request.getDescription(true), ex.getMessage());
	return new ResponseEntity<Object>(exceptionDetails,HttpStatus.BAD_REQUEST);
}

}
