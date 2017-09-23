package com.asseco.arp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.asseco.arp.domain.ExceptionResult;

@ControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResult> handleGlobalException(Exception e) {

		final ExceptionResult result = new ExceptionResult();

		result.setErrorCode(HttpStatus.BAD_REQUEST.value());
		result.setErrorDescription(HttpStatus.BAD_REQUEST.getReasonPhrase());
		result.setErrorMessage("Error during the proces execution! See the log for more details.");

		e.printStackTrace();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}
	
}
