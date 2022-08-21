package com.bank.wallet.controller;

import static com.bank.wallet.utils.WalletUtils.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.wallet.handler.CustomErrorHandler;
import com.bank.wallet.handler.ResponseHandler;
import com.bank.wallet.manager.TransactionRequest;
import com.bank.wallet.model.Profile;
import com.bank.wallet.model.TransactionDetails;
import com.bank.wallet.service.ProfileService;
import com.bank.wallet.service.TransactionService;

@RestController
@RequestMapping(TRANSACTIONS_URL)
public class TransactionController {

	@Autowired
	private TransactionService transactionRepository;
	@Autowired
	private ProfileService profileRepository;
	
	private HttpHeaders getResponseHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SERVER, LOCAL);
		headers.add(DATE, new Date().toString());
		return headers;
	}
	
	@PostMapping(CREATE_TRANSACTIONS_BY_ID_URL)
    public ResponseEntity<?> createTransactionByID(@RequestBody TransactionRequest transactionRequest) {
		ResponseHandler<?> responseBody;
		try {
	        TransactionDetails resTransactionDetails = transactionRepository.save(transactionRequest);
	        responseBody = new ResponseHandler<>(CREATED, SUCCESS, CREATE_TRANSACTION_DETAIL_MESSAGE, resTransactionDetails);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(CONFLICT, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
    }
	
	@PostMapping(CREATE_TRANSACTIONS_URL)
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest transactionRequest) {
		ResponseHandler<?> responseBody;
		try {
	        TransactionDetails resTransactionDetails = transactionRepository.transaction(transactionRequest);
	        responseBody = new ResponseHandler<>(CREATED, SUCCESS, CREATE_TRANSACTION_DETAIL_MESSAGE, resTransactionDetails);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(CONFLICT, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
    }
	
	@GetMapping(GET_TRANSACTIONS_BY_ACCOUNT_NUMBER_URL)
	public ResponseEntity<?> getTransactionHistoryByAccountNumber(@PathVariable(value = "account_number") int accountNumber) {
		ResponseHandler<?> responseBody;
		try {
			Profile profile = profileRepository.findByAccountNumber(accountNumber);
			Long profileId = profile.getId();
			List<TransactionDetails> lstTransactionDetails = transactionRepository.getTransactionDetailByProfileId(profileId);
			responseBody = new ResponseHandler<>(OK, SUCCESS, 
					FETCH_PROFILE_MESSAGE + accountNumber, lstTransactionDetails);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NO_CONTENT, SUCCESS, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(GET_TRANSACTIONS_BY_ACCOUNT_NUMBER_TYPE_URL)
	public ResponseEntity<?> getTransactionHistoryByAccountNumberAndType(@PathVariable(value = "account_number") int accountNumber, 
			@PathVariable(value = "type") String type) {
		ResponseHandler<?> responseBody;
		try {
			Profile profile = profileRepository.findByAccountNumber(accountNumber);
			Long profileId = profile.getId();
			List<TransactionDetails> lstTransactionDetails = transactionRepository.getTransactionDetailByProfileIdAndType(profileId, type);
			responseBody = new ResponseHandler<>(OK, SUCCESS, 
					FETCH_PROFILE_MESSAGE + accountNumber, lstTransactionDetails);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NO_CONTENT, SUCCESS, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(TRANSACTIONS_TEST_URL)
	public ResponseEntity<?> getHelloWorld() {
		ResponseHandler<?> responseBody = new ResponseHandler<>(OK, SUCCESS, HELLOWORLD);
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
}
