package com.cg.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.WalletTransaction;
import com.cg.exceptions.AccountActiveException;
import com.cg.exceptions.TxnException;
import com.cg.service.ITransactionService;
@RestController
public class TransactionMicroservice {
	@Autowired
	private ITransactionService transactionService;
	
	@GetMapping("viewtransaction/{startdt}/{enddt}/{aid}")
	public List<WalletTransaction> getPassBookService(@PathVariable("startdt") @DateTimeFormat(pattern="yyyy-M-d") LocalDate startDt,
			@PathVariable("enddt") @DateTimeFormat(pattern="yyyy-M-d")LocalDate endDt,@PathVariable("aid") String phoneNo) throws TxnException, AccountActiveException{
		
		
		return transactionService.getTransactions(startDt, endDt, phoneNo);
	}
	@GetMapping("viewtransaction/{aid}")
	public List<WalletTransaction> getPassBookService(@PathVariable("aid") String phoneNo) throws TxnException, AccountActiveException {
		
		
		
		return transactionService.getTransactionById(phoneNo);
	}
	

}
