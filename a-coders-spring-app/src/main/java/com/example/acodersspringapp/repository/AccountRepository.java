package com.example.acodersspringapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.acodersspringapp.entity.AccountEntity;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, Long>{

	@Query("{'username':?0,'activated':1}")
	AccountEntity findAccountByUsername(String username);

	@Query("{'username':?0,'password':?1,'activated':1}")
	AccountEntity findAccountByUsernameAndPassword(String username, String encrPassword);

}
