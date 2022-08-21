package com.bank.wallet.manager;


public class TransactionRequest {

	private Long profile_id;
	private int account_number;
	private String transaction_type;
	private double transaction_amount;
	private String transaction_details;
	
	public void setProfile_id(Long profile_id) {
		this.profile_id = profile_id;
	}
	
	public Long getProfile_id() {
		return this.profile_id;
	}
	
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	
	public int getAccount_number() {
		return this.account_number;
	}
	
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
	public String getTransaction_type() {
		return this.transaction_type;
	}
	
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	
	public double getTransaction_amount() {
		return this.transaction_amount;
	}
	
	public void setTransaction_details(String transaction_details) {
		this.transaction_details = transaction_details;
	}
	
	public String getTransaction_details() {
		return this.transaction_details;
	}
}
