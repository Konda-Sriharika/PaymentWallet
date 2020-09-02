package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.WalletBank;

@Repository
public interface WalletBankDao extends JpaRepository<WalletBank, String>  {

}
