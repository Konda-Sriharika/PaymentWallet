package com.cg.service;

import com.cg.dto.AccountForm;
import com.cg.dto.UserDto;
import com.cg.exceptions.BankAccountException;
import com.cg.exceptions.WalletAccountExistsException;

public interface IAddWalletService {
	
	
	public String addNewWalletAccount(UserDto userDto) throws WalletAccountExistsException;
	public String addBankAccount(AccountForm form) throws BankAccountException, WalletAccountExistsException ;
}
