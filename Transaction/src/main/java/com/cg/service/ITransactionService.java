package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.WalletTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.TxnException;

public interface ITransactionService {
	
	public List<WalletTransaction> getTransactions(LocalDate startDt, LocalDate endDt, String phoneNo)throws TxnException,AccountActiveException ;
	public List<WalletTransaction> getTransactionById(String phoneNo) throws TxnException, AccountActiveException;
}
