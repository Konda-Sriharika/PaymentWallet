package com.cg.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.TxnException;
import com.cg.dto.ErrorResponse;

@RestControllerAdvice

public class BankingAdvice {
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public ErrorResponse handleAccountException(AccountActiveException ex) {
		return new ErrorResponse(HttpStatus.FORBIDDEN.toString(),ex.getMessage(), LocalDateTime.now().toString());
		
	}
	
	@ExceptionHandler
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public ErrorResponse handleTxnException(TxnException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.toString(),ex.getMessage(), LocalDateTime.now().toString());
		
	}
	
}