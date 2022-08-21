package com.bank.wallet.utils;

public class WalletUtils {
	
	public static final String BASE_URL = "/api";
	public static final String PROFILES_URL = BASE_URL + "/profiles";
	public static final String PROFILES_TEST_URL = "/test";
	public static final String GET_ALL_PROFILES = "/get_all_profiles";
	public static final String GET_ALL_PROFILE_BY_ID = "/get_profile_by_id/{id}";
	public static final String GET_ALL_PROFILE_BY_ACCOUNT_NUMBER = "/get_profile_by_account_number/{account_number}";
	public static final String GET_BALANCE_BY_ACCOUNT_NUMBER = "/get_balance_by_account_number/{account_number}";
	public static final String GET_ALL_CLOSED_ACCOUNT = "/get_all_closed_account";
	public static final String CREATE_PROFILE = "/create_profile";
	public static final String UPDATE_ADDRESS_IN_PROFILE = "/update_address/{account_number}";
	public static final String UPDATE_CONTACT_NUMBER_IN_PROFILE = "/update_contact_number/{account_number}";
	public static final String UPDATE_EMAIL_ID_IN_PROFILE = "/update_email_id/{account_number}";
	public static final String CLOSE_ACCOUNT = "/close_account/{account_number}";
	public static final String DELETE_ACCOUNT = "/delete_account/{account_number}";
	
	public static final String TRANSACTIONS_URL = BASE_URL + "/transactions";
	public static final String TRANSACTIONS_TEST_URL = "/test";
	public static final String CREATE_TRANSACTIONS_URL = "/create_transaction";
	public static final String CREATE_TRANSACTIONS_BY_ID_URL = "/create_transaction_by_id";
	public static final String GET_TRANSACTIONS_BY_ACCOUNT_NUMBER_URL = "/get_transaction_by_account_number/{account_number}";
	public static final String GET_TRANSACTIONS_BY_ACCOUNT_NUMBER_TYPE_URL = "/get_transaction_by_account_number_and_type/{account_number}/{type}";
	
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final String DESCRIPTION = "description";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int NO_CONTENT = 204;
	public static final int NOT_FOUND = 404;
	public static final int CONFLICT = 409;
	public static final String SERVER = "Server";
	public static final String LOCAL = "Windows/Local System";
	public static final String DATE = "Date";
	
	public static final String CREATED_AT = "created_at";
	public static final String UPDATED_AT = "updated_at";
	
	public static final String HELLOWORLD = "Hello World";
	public static final String FETCH_ALL_PROFILE_MESSAGE = "Successfully fetched all profiles";
	public static final String FETCH_PROFILE_MESSAGE = "Successfully fetched profile of ";
	public static final String FETCH_CLOSED_PROFILE_MESSAGE = "Successfully fetched all closed accounts";
	public static final String CURRENT_BALANCE_MESSAGE = "Successfully fetched the balance amount of account number : ";
	public static final String CREATE_PROFILE_MESSAGE = "Profile Created Successfully.";
	public static final String UPDATE_PROFILE_MESSAGE = "Profile Updated Successfully.";
	public static final String CREATE_TRANSACTION_DETAIL_MESSAGE = "Your Transaction Details";
	public static final String DELETE_PROFILE_MESSAGE = "Profile Deleted Successfully.";
}
