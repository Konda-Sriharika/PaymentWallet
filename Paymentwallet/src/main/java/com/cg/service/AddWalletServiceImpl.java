package com.cg.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cg.dao.WalletAccountDao;
import com.cg.dao.WalletBankDao;
import com.cg.dao.WalletTransactionDao;
import com.cg.dto.AccountForm;
import com.cg.dto.UserDto;
import com.cg.entity.WalletAccount;
import com.cg.entity.WalletBank;
import com.cg.entity.WalletTransaction;
import com.cg.exceptions.BankAccountException;
import com.cg.exceptions.WalletAccountExistsException;
import com.cg.util.WalletConstants;


@Service
public class AddWalletServiceImpl implements IAddWalletService{

	@Autowired
	private WalletAccountDao accountDao;
	@Autowired
	private WalletBankDao bankDao;
	@Autowired
	private WalletTransactionDao txDao;
	@Value("${cashback}")
	private double cashback;
	@Override
	@Transactional
	public String addNewWalletAccount(UserDto userDto) throws WalletAccountExistsException {
		 Optional<WalletAccount> optAccount = accountDao.findById(userDto.getPhoneNo());
		 if (optAccount.isPresent())
			 throw new WalletAccountExistsException(WalletConstants.PHONE_NO_ALREADY_EXISTS);
		WalletAccount account = new WalletAccount();
		account.setPhoneNo(userDto.getPhoneNo());
		account.setUserName(userDto.getUserName());
		account.setPassword(userDto.getPassword());
		account.setBalance(userDto.getBalance());
		account.setRole(WalletConstants.USER_ROLE);
		account.setStatus(WalletConstants.ACTIVE_USER);
		account.setAccCreatedDt(LocalDate.now());
		accountDao.save(account);
		if(cashback>WalletConstants.NO_CASHBACK) {
			WalletTransaction tx = new WalletTransaction();
			tx.setAmount(userDto.getBalance());
			tx.setDateOfTransaction(LocalDate.now());
			tx.setTxType(WalletConstants.CREDIT);
			tx.setDescription(WalletConstants.AMOUNT_ADDED);
			txDao.save(tx);
		}
		
		
		return WalletConstants.WALLET_ACCOUNT_CREATED;
	}
	@Override
	public String addBankAccount(AccountForm form) throws BankAccountException, WalletAccountExistsException {
		WalletBank bank = new WalletBank();
		Optional<WalletBank> opt = bankDao.findById(form.getAccountId());
		if(opt.isPresent()) {
			throw new BankAccountException(WalletConstants.BANK_ACCOUNT_EXISTS);
		}
		Optional<WalletAccount> optAccount = accountDao.findById(form.getPhoneNo());
		 if (!optAccount.isPresent())
			 throw new WalletAccountExistsException(WalletConstants.INVALID_WALLET);
		bank.setAccountId(form.getAccountId());
		bank.setBankName(form.getBankName());
		bank.setPhoneNo(form.getPhoneNo());
		bankDao.save(bank);
		return WalletConstants.BANK_ACCOUNT_ADDED;
	}
	
	
	
}
