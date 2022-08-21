package com.bank.wallet.manager;

public class ProfileRequest {

	private int account_number;
	private String name;
	private String address;
	private String contact_number;
	private String email_id;
	private String account_type;
	private String bank_name;
	private String branch_name;
	private boolean is_active;
	private double balance;
	
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	
	public int getAccount_number() {
		return this.account_number;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	
	public String getContact_number() {
		return this.contact_number;
	}
	
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public String getEmail_id() {
		return this.email_id;
	}
	
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
	public String getAccount_type() {
		return this.account_type;
	}
	
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	public String getBank_name() {
		return this.bank_name;
	}
	
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	public String getBranch_name() {
		return this.branch_name;
	}
	
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	public boolean getIs_active() {
		return this.is_active;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return this.balance;
	}
}
