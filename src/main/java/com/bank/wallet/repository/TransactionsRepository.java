package com.bank.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.wallet.model.TransactionDetails;

public interface TransactionsRepository extends JpaRepository<TransactionDetails, Long>{

}
