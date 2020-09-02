package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.WalletAccountDao;
import com.cg.entity.WalletAccount;
import com.cg.entity.WalletTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.TxnException;
import com.cg.util.TransactionConstants;
@Service
public class TransactionServiceImpl implements ITransactionService {
	@Autowired
	private WalletAccountDao walaccDao;

	@Override
	public List<WalletTransaction> getTransactions(LocalDate startDt, LocalDate endDt, String phoneNo) throws TxnException, AccountActiveException {
		if(validateDate(startDt, endDt)) {
			Optional<WalletAccount> optaccount = walaccDao.findById(phoneNo);
			if(!optaccount.isPresent())
				throw new AccountActiveException(TransactionConstants.IN_ACTIVE_ACCOUNT);
				
			List<WalletTransaction>  txlist = walaccDao.getTransactions(startDt, endDt,phoneNo);
			if(txlist.isEmpty())
				throw new TxnException(TransactionConstants.TXN_NOT_AVAILABLE);
			txlist.sort((txn1, txn2)->txn1.getDateOfTransaction().compareTo(txn2.getDateOfTransaction()));
			
			return txlist;
		}
		return null;
	}
	
	public boolean validateDate(LocalDate startDt, LocalDate endDt) throws TxnException {
		
		if(startDt.isAfter(LocalDate.now()))
			throw new TxnException(TransactionConstants.START_AFTER_DATE);
		if(endDt.isBefore(startDt))
			throw new TxnException(TransactionConstants.BEFORE_START_DATE);
		return true;
	}
	
	
	private boolean validateDate(String phoneNo) throws TxnException {
		if(phoneNo.isEmpty())
			throw new TxnException(TransactionConstants.START_AFTER_DATE);
		
		return true;
	}
	@Override
	public List<WalletTransaction> getTransactionById(String phoneNo) throws TxnException, AccountActiveException {
	
		if(validateDate(phoneNo)) {
			Optional<WalletAccount> optaccount = walaccDao.findById(phoneNo);
			if(!optaccount.isPresent())
				throw new AccountActiveException(TransactionConstants.NO_ACCOUNT_FOUND);
		
			List<WalletTransaction>  txlist = walaccDao.getTransactionById(phoneNo);
			if(txlist.isEmpty())
				throw new TxnException(TransactionConstants.TXN_NOT_AVAILABLE);
			txlist.sort((txn1, txn2)->txn1.getDateOfTransaction().compareTo(txn2.getDateOfTransaction()));
			
			return txlist;
		}
		return null;
}
}