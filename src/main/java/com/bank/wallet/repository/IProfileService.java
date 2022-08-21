package com.bank.wallet.repository;

import java.util.List;

import com.bank.wallet.manager.ProfileRequest;
import com.bank.wallet.model.Profile;

public interface IProfileService {

	List<Profile> findAll();
	Profile findById(Long id);
	Profile findByAccountNumber(int account_number);
	Profile updateAddress(int account_number, String address);
	Profile updateContactNumber(int account_number, String contact_number);
	Profile updateEmailId(int account_number, String email_id);
	Profile save(ProfileRequest profile);
	Profile closeAccount(int account_number);
	int deleteAccount(int account_number);
	List<Profile> findByClosedAccount();
}
