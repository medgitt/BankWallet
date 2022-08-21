package com.bank.wallet.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.bank.wallet.handler.CustomErrorHandler;
import com.bank.wallet.manager.TransactionRequest;
import com.bank.wallet.model.Profile;
import com.bank.wallet.model.TransactionDetails;
import com.bank.wallet.repository.ITransactionsService;
import com.bank.wallet.repository.ProfileRepository;
import com.bank.wallet.repository.TransactionsRepository;

@Service
public class TransactionService implements ITransactionsService{

	@Autowired
	private TransactionsRepository transactionRepository;
	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public TransactionDetails save(TransactionRequest transaction) {
		String transactionType = transaction.getTransaction_type();
		if(transactionType.equalsIgnoreCase("CREDIT") || transactionType.equalsIgnoreCase("DEBIT")) {
			double transactionAmount = transaction.getTransaction_amount();
			if(transactionAmount > 0) {
				Profile profile = profileRepository.findById(transaction.getProfile_id())
						.orElseThrow(() -> new CustomErrorHandler("Profile", "id", transaction.getProfile_id()));
				double balanceAmount = profile.getBalance();
				String uniqueID = "TRAN" + UUID.randomUUID().toString();
				if(transactionType.equalsIgnoreCase("DEBIT")) {
					if(!(transactionAmount <= balanceAmount)) {
						throw new CustomErrorHandler("Transaction Denied!! Insufficient Balance. You have ", balanceAmount);
					}
					balanceAmount = balanceAmount - transactionAmount;
					profile.setBalance(balanceAmount);
					profileRepository.save(profile);
					return transactionRepository.save(new TransactionDetails(uniqueID, transactionType, 
							transactionAmount, transaction.getTransaction_details(), profile));
				} else {
					balanceAmount = balanceAmount + transactionAmount;
					profile.setBalance(balanceAmount);
					profileRepository.save(profile);
					return transactionRepository.save(new TransactionDetails(uniqueID, transactionType, 
							transactionAmount, transaction.getTransaction_details(), profile));
				}
			} else {
				throw new CustomErrorHandler("Transaction Denied!! Invalid Amount. You tried ", transactionAmount);
			}
			
		} else {
			throw new CustomErrorHandler("Transaction Denied!! Invalid Transaction Type. Please try CREDIT or DEBIT. You tried", transactionType);
		}
		
	}
	
	private Profile getProfile(int account_number) {
		Profile profile = new Profile();
		profile.setAccount_number(account_number);;
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("account_number", 
				match -> match.exact()).withIgnorePaths("is_active").withIgnorePaths("balance");
		Example<Profile> exProfile = Example.of(profile, matcher);
		List<Profile> getAll = profileRepository.findAll(exProfile);
		if(getAll.size() == 0)
			throw new CustomErrorHandler("Transaction Denied!! Please enter valid account number. You entered ", account_number);
		return profileRepository.findAll(exProfile).get(0);
	}

	@Override
	public TransactionDetails transaction(TransactionRequest transaction) {
		int accountNumber = transaction.getAccount_number();
		try {
			if(accountNumber != 0) {
				String transactionType = transaction.getTransaction_type();
				if(transactionType.equalsIgnoreCase("CREDIT") || transactionType.equalsIgnoreCase("DEBIT")) {
					double transactionAmount = transaction.getTransaction_amount();
					if(transactionAmount > 0) {
						Profile profile = getProfile(accountNumber);
						double balanceAmount = profile.getBalance();
						String uniqueID = "TRAN" + UUID.randomUUID().toString();
						if(transactionType.equalsIgnoreCase("DEBIT")) {
							if(!(transactionAmount <= balanceAmount)) {
								throw new CustomErrorHandler("Transaction Denied!! Insufficient Balance. You have ", balanceAmount);
							}
							balanceAmount = balanceAmount - transactionAmount;
							profile.setBalance(balanceAmount);
							profileRepository.save(profile);
							return transactionRepository.save(new TransactionDetails(uniqueID, transactionType, 
									transactionAmount, transaction.getTransaction_details(), profile));
						} else {
							balanceAmount = balanceAmount + transactionAmount;
							profile.setBalance(balanceAmount);
							profileRepository.save(profile);
							return transactionRepository.save(new TransactionDetails(uniqueID, transactionType, 
									transactionAmount, transaction.getTransaction_details(), profile));
						}
					} else {
						throw new CustomErrorHandler("Transaction Denied!! Invalid Amount. You tried ", transactionAmount);
					}
					
				} else {
					throw new CustomErrorHandler("Transaction Denied!! Invalid Transaction Type. Please try CREDIT or DEBIT. You tried", transactionType);
				}
				
			} else {
				throw new CustomErrorHandler("Transaction Denied!! Please enter account number.", "");
			}
		} catch(CustomErrorHandler e) {
			throw new CustomErrorHandler(e.getMessage());
		}
	}

	@Override
	public List<TransactionDetails> getTransactionDetailByProfileId(Long profileId) {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setProfile_id(profileId);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("profile_id", 
				match -> match.exact()).withIgnorePaths("transaction_amount");
		Example<TransactionDetails> exTransaction = Example.of(transactionDetails, matcher);
		List<TransactionDetails> getAll = transactionRepository.findAll(exTransaction);
		return getAll;
	}

	@Override
	public List<TransactionDetails> getTransactionDetailByProfileIdAndType(Long profileId, String type) {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setProfile_id(profileId);
		transactionDetails.setTransaction_type(type);
		ExampleMatcher matcher = ExampleMatcher.matching().
				withMatcher("profile_id", match -> match.exact()).
				withMatcher("transaction_type", match -> match.exact()).
				withIgnorePaths("transaction_amount");
		Example<TransactionDetails> exTransaction = Example.of(transactionDetails, matcher);
		List<TransactionDetails> getAll = transactionRepository.findAll(exTransaction);
		return getAll;
	}
}
