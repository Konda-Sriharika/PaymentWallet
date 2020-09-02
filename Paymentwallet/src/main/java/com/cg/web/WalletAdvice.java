package com.cg.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.dto.ErrorResponse;
import com.cg.exceptions.WalletAccountExistsException;
import com.cg.exceptions.WalletNotFoundException;

@RestControllerAdvice
public class WalletAdvice {

	@ExceptionHandler(WalletAccountExistsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleAccountException(WalletAccountExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage(), LocalDateTime.now().toString());
	}
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleBankException(HttpClientErrorException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getResponseBodyAsString(), LocalDateTime.now().toString());
	}
	@ExceptionHandler(WalletNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleAccountException(WalletNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage(), LocalDateTime.now().toString());
	}
	
}
