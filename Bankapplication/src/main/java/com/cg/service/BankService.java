package com.cg.service;

import com.cg.dto.AccountForm;
import com.cg.entity.BankAccount;
import com.cg.exceptions.BankAccountException;

public interface BankService {
	public String verifyAccount(AccountForm form) throws BankAccountException;
	public String editAccount(String accountId, double balance) throws BankAccountException;
	

}
