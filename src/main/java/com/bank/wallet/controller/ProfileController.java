package com.bank.wallet.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.wallet.handler.CustomErrorHandler;
import com.bank.wallet.handler.ResponseHandler;
import com.bank.wallet.manager.ProfileRequest;
import com.bank.wallet.model.Profile;
import com.bank.wallet.service.ProfileService;

import static com.bank.wallet.utils.WalletUtils.*;

@RestController
@RequestMapping(PROFILES_URL)
public class ProfileController {

	@Autowired
	private ProfileService profileRepository;

	private HttpHeaders getResponseHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SERVER, LOCAL);
		headers.add(DATE, new Date().toString());
		return headers;
	}
	
	@GetMapping(GET_ALL_PROFILES)
	public ResponseEntity<?> getAllProfiles() {
		List<Profile> lstProfiles = profileRepository.findAll();
		ResponseHandler<?> responseBody = new ResponseHandler<>(OK, SUCCESS, 
				FETCH_ALL_PROFILE_MESSAGE, lstProfiles);
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(GET_ALL_PROFILE_BY_ID)
	public ResponseEntity<?> getProfileById(@PathVariable(value = "id") Long profileId) {
		ResponseHandler<?> responseBody; 
		try {
			Profile profile = profileRepository.findById(profileId);	
			responseBody = new ResponseHandler<>(OK, SUCCESS, 
					FETCH_PROFILE_MESSAGE + profileId, profile);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NO_CONTENT, SUCCESS, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(GET_ALL_PROFILE_BY_ACCOUNT_NUMBER)
	public ResponseEntity<?> getProfileByAccountNumber(@PathVariable(value = "account_number") int accountNumber) {
		ResponseHandler<?> responseBody;
		try {
			Profile profile = profileRepository.findByAccountNumber(accountNumber);
			responseBody = new ResponseHandler<>(OK, SUCCESS, 
					FETCH_PROFILE_MESSAGE + accountNumber, profile);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NO_CONTENT, SUCCESS, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(GET_BALANCE_BY_ACCOUNT_NUMBER)
	public ResponseEntity<?> getCurrentBalanceByAccountNumber(@PathVariable(value = "account_number") int accountNumber) {
		ResponseHandler<?> responseBody;
		try {
			Profile profile = profileRepository.findByAccountNumber(accountNumber);
			double balance = profile.getBalance();
			responseBody = new ResponseHandler<>(OK, SUCCESS, 
					CURRENT_BALANCE_MESSAGE + accountNumber, balance);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NO_CONTENT, SUCCESS, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@PostMapping(CREATE_PROFILE)
    public ResponseEntity<?> createAccount(@RequestBody ProfileRequest profile) {
		ResponseHandler<?> responseBody;
		try {
			Profile resProfile = profileRepository.save(profile);	
			responseBody = new ResponseHandler<>(CREATED, SUCCESS, CREATE_PROFILE_MESSAGE, resProfile);
		}catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(CONFLICT, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
    }
	
	@PutMapping(UPDATE_ADDRESS_IN_PROFILE)
	public ResponseEntity<?> updateAddress(@PathVariable(value = "account_number") int account_number, @RequestParam("address") String address) {
		ResponseHandler<?> responseBody;
		try {
			Profile resProfile = profileRepository.updateAddress(account_number, address);
			responseBody = new ResponseHandler<>(OK, SUCCESS, UPDATE_PROFILE_MESSAGE, resProfile);
		}catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NOT_FOUND, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@PutMapping(UPDATE_CONTACT_NUMBER_IN_PROFILE)
	public ResponseEntity<?> updateContactNumber(@PathVariable(value = "account_number") int account_number, @RequestParam("contact_number") String contactNumber) {
		ResponseHandler<?> responseBody;
		try {
			Profile resProfile = profileRepository.updateContactNumber(account_number, contactNumber);
			responseBody = new ResponseHandler<>(OK, SUCCESS, UPDATE_PROFILE_MESSAGE, resProfile);
		}catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NOT_FOUND, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@PutMapping(UPDATE_EMAIL_ID_IN_PROFILE)
	public ResponseEntity<?> updateEmailId(@PathVariable(value = "account_number") int account_number, @RequestParam("email_id") String emailId) {
		ResponseHandler<?> responseBody;
		try {
			Profile resProfile = profileRepository.updateEmailId(account_number, emailId);
			responseBody = new ResponseHandler<>(OK, SUCCESS, UPDATE_PROFILE_MESSAGE, resProfile);
		}catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NOT_FOUND, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@PutMapping(CLOSE_ACCOUNT)
	public ResponseEntity<?> closeAccount(@PathVariable(value = "account_number") int account_number) {
		ResponseHandler<?> responseBody;
		try {
			Profile resProfile = profileRepository.closeAccount(account_number);
			responseBody = new ResponseHandler<>(OK, SUCCESS, UPDATE_PROFILE_MESSAGE, resProfile);
		}catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NOT_FOUND, ERROR, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@DeleteMapping(DELETE_ACCOUNT)
	public ResponseEntity<?> deleteAccount(@PathVariable(value = "account_number") int account_number) {
		profileRepository.deleteAccount(account_number);
		ResponseHandler<?> responseBody = new ResponseHandler<>(OK, SUCCESS, DELETE_PROFILE_MESSAGE);
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(GET_ALL_CLOSED_ACCOUNT)
	public ResponseEntity<?> getAllClosedAccount() {
		ResponseHandler<?> responseBody;
		try {
			List<Profile> lstProfile = profileRepository.findByClosedAccount();
			responseBody = new ResponseHandler<>(OK, SUCCESS, 
					FETCH_CLOSED_PROFILE_MESSAGE, lstProfile);
		} catch(CustomErrorHandler e) {
			responseBody = new ResponseHandler<>(NO_CONTENT, SUCCESS, e.getMessage());
		}
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
	
	@GetMapping(PROFILES_TEST_URL)
	public ResponseEntity<?> getHelloWorld() {
		ResponseHandler<?> responseBody = new ResponseHandler<>(OK, SUCCESS, HELLOWORLD);
		return ResponseEntity.ok().headers(getResponseHeaders()).body(responseBody);
	}
}
