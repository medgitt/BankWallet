package com.bank.wallet.model;

import static com.bank.wallet.utils.WalletUtils.CREATED_AT;
import static com.bank.wallet.utils.WalletUtils.UPDATED_AT;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transaction_details")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {CREATED_AT, UPDATED_AT},
allowGetters = true)
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "profile_id", insertable = false, updatable = false)
	private Long profile_id;
	
	private String transaction_id;
	private String transaction_type;
	private double transaction_amount;
	private String transaction_details;
	@Column(name = "transaction_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	private Date transaction_date;
	
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;
	
	public TransactionDetails() {
	}
	
	public TransactionDetails(String transaction_id, String transaction_type, 
			double transaction_amount, String transaction_details, Profile profile) {
		super();
		setTransaction_id(transaction_id);
		setTransaction_type(transaction_type);
		setTransaction_amount(transaction_amount);
		setTransaction_details(transaction_details);
		setProfile(profile);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setProfile_id(Long profile_id) {
		this.profile_id = profile_id;
	}
	
	public Long getProfile_id() {
		return this.profile_id;
	}
	
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public String getTransaction_id() {
		return this.transaction_id;
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
	
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	public Date getTransaction_date() {
		return this.transaction_date;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Profile getProfile() {
		return this.profile;
	}
}
