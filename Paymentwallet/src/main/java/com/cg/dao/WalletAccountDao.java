package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.WalletAccount;

@Repository
public interface WalletAccountDao extends JpaRepository<WalletAccount, String>{

	//@Query( value = "select account_balance from wallet_account where user_name = :user_name")
	//double getAccountBalance(@Param("user_name") String user_name);
	
	
	

}
