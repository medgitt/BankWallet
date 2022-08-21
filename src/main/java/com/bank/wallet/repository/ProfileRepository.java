package com.bank.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.wallet.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
