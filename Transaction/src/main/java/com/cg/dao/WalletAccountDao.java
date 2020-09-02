package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.WalletTransaction;
import com.cg.entity.WalletAccount;

@Repository
public interface WalletAccountDao  extends  JpaRepository<WalletAccount,String>{
	
	
	@Query("from WalletTransaction tx  where tx.dateOfTransaction >= :startDt and tx.dateOfTransaction <= :endDt and tx.account.phoneNo=:phno")
	public List<WalletTransaction> getTransactions(@Param("startDt")LocalDate startDt, @Param("endDt") LocalDate endDt, @Param("phno")String phoneNo);
	
	@Query("from WalletTransaction tx where tx.account.phoneNo=:phno")
	public List<WalletTransaction> getTransactionById(@Param("phno") String phoneNo);

	

}