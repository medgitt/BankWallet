package com.bank.wallet.repository;

import java.util.List;

import com.bank.wallet.manager.TransactionRequest;
import com.bank.wallet.model.TransactionDetails;

public interface ITransactionsService {

	TransactionDetails save(TransactionRequest transaction);
	TransactionDetails transaction(TransactionRequest transaction);
	List<TransactionDetails> getTransactionDetailByProfileId(Long profileId);
	List<TransactionDetails> getTransactionDetailByProfileIdAndType(Long profileId, String type);
}
