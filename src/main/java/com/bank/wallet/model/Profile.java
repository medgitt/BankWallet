package com.bank.wallet.model;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static com.bank.wallet.utils.WalletUtils.*;

@Entity
@Table(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {CREATED_AT, UPDATED_AT},
allowGetters = true)
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	
	@Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Date created_at;
	
	@Column(name = "updated_at", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	private Date updated_at;
	
	@OneToMany(mappedBy = "profile")
	private Set<TransactionDetails> transactionDetails = new HashSet<>();
	
	public Profile() {
		super();
	}
	
	public Profile(int account_number, String name, String address, 
			String contact_number, String email_id, String account_type, 
			String bank_name, String branch_name, boolean is_active, double balance) {
		super();
		setAccount_number(account_number);
		setName(name);
		setAddress(address);
		setContact_number(contact_number);
		setEmail_id(email_id);
		setAccount_type(account_type);
		setBank_name(bank_name);
		setBranch_name(branch_name);
		setIs_active(is_active);
		setBalance(balance);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
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
	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	public Date getCreated_at() {
		return this.created_at;
	}
	
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public Date getUpdated_at() {
		return this.updated_at;
	}
}
