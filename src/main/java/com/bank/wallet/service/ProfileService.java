package com.bank.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.bank.wallet.handler.CustomErrorHandler;
import com.bank.wallet.manager.ProfileRequest;
import com.bank.wallet.model.Profile;
import com.bank.wallet.repository.IProfileService;
import com.bank.wallet.repository.ProfileRepository;

@Service
public class ProfileService implements IProfileService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public List<Profile> findAll() {
		return (List<Profile>) profileRepository.findAll();
	}
	
	@Override
	public Profile findById(Long id) {
		return profileRepository.findById(id)
				.orElseThrow(() -> new CustomErrorHandler("Profile", "id", id));
	}

	@Override
	public Profile findByAccountNumber(int account_number) {
		Profile profile = new Profile();
		profile.setAccount_number(account_number);;
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("account_number", 
				match -> match.exact()).withIgnorePaths("is_active").withIgnorePaths("balance");
		Example<Profile> exProfile = Example.of(profile, matcher);
		List<Profile> getAll = profileRepository.findAll(exProfile);
		if(getAll.size() == 0)
			throw new CustomErrorHandler("Profile", "account_number", account_number);
		return profileRepository.findAll(exProfile).get(0);
	}
	
	@Override
	public Profile updateAddress(int account_number, String address) {
		try {
			Profile profile = findByAccountNumber(account_number);
			profile.setAddress(address);
			Profile updateProfile = profileRepository.save(profile);
			return updateProfile;
		} catch(CustomErrorHandler e) {
			throw new CustomErrorHandler("The account number is not available. Account Number", account_number);
		}
	}

	@Override
	public Profile updateContactNumber(int account_number, String contact_number) {
		try {
			Profile profile = findByAccountNumber(account_number);
			profile.setContact_number(contact_number);
			Profile updateProfile = profileRepository.save(profile);
			return updateProfile;
		} catch(CustomErrorHandler e) {
			throw new CustomErrorHandler("The account number is not available. Account Number", account_number);
		}
	}

	@Override
	public Profile updateEmailId(int account_number, String email_id) {
		try {
			Profile profile = findByAccountNumber(account_number);
			profile.setEmail_id(email_id);;
			Profile updateProfile = profileRepository.save(profile);
			return updateProfile;
		} catch(CustomErrorHandler e) {
			throw new CustomErrorHandler("The account number is not available. Account Number", account_number);
		}
	}

	@Override
	public Profile save(ProfileRequest profile) {
		try {
			findByAccountNumber(profile.getAccount_number());
		} catch(CustomErrorHandler e) {
			return profileRepository.save(new Profile(profile.getAccount_number(), profile.getName(), profile.getAddress(),
					profile.getContact_number(), profile.getEmail_id(), profile.getAccount_type(), profile.getBank_name(),
					profile.getBranch_name(), profile.getIs_active(), profile.getBalance()));
		}
		throw new CustomErrorHandler("The account number is already available. Account Number", profile.getAccount_number());		
	}

	@Override
	public Profile closeAccount(int account_number) {
		try {
			Profile profile = findByAccountNumber(account_number);
			profile.setIs_active(false);
			Profile updateProfile = profileRepository.save(profile);
			return updateProfile;
		} catch(CustomErrorHandler e) {
			throw new CustomErrorHandler("The account number is not available. Account Number", account_number);
		}
	}

	@Override
	public int deleteAccount(int account_number) {
		try {
			Profile profile = findByAccountNumber(account_number);
			profileRepository.delete(profile);
			return 1;
		} catch(CustomErrorHandler e) {
			throw new CustomErrorHandler("The account number is not available. Account Number", account_number);
		}
	}

	@Override
	public List<Profile> findByClosedAccount() {
		Profile profile = new Profile();
		profile.setIs_active(false);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("is_active", 
				match -> match.exact()).withIgnorePaths("account_number").withIgnorePaths("balance");
		Example<Profile> exProfile = Example.of(profile, matcher);
		List<Profile> getAll = profileRepository.findAll(exProfile);
		if(getAll.size() == 0)
			throw new CustomErrorHandler("There is no Closed Account");
		return getAll;
	}

}
