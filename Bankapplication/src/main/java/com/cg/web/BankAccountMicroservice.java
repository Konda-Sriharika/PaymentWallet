package com.cg.web;

import java.time.LocalDateTime;

import javax.websocket.ClientEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.AccountForm;
import com.cg.dto.Error;
import com.cg.entity.BankAccount;
import com.cg.exceptions.BankAccountException;
import com.cg.service.BankService;
import com.cg.service.BankServiceImpl;
import com.cg.util.BankConstants;


@RestController
public class BankAccountMicroservice {
	
	@Autowired
	private BankService service;
	
	@PostMapping(BankConstants.VERIFY_URL)
	public String getAccount(@RequestBody AccountForm form) throws BankAccountException {
		return service.verifyAccount(form) ;
	}
	
	@PostMapping(BankConstants.EDIT_URL)
	public String editAccount(@RequestBody AccountForm form) throws BankAccountException {
		return service.editAccount(form.getAccountId(), form.getBalance()) ;
	}
	
	@ExceptionHandler(BankAccountException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Error handleAccountException(BankAccountException ex) {
		return new Error(HttpStatus.NOT_FOUND.toString(), BankConstants.INVALID_ACCOUNT, LocalDateTime.now().toString());
	}
	

}
