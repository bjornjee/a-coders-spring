package com.example.acodersspringapp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.acodersspringapp.entity.AccountEntity;
import com.example.acodersspringapp.model.request.RegisterRequestModel;
import com.example.acodersspringapp.model.request.UpdateUserRequestModel;
import com.example.acodersspringapp.model.response.TokenResponseModel;
import com.example.acodersspringapp.repository.AccountRepository;
import com.example.acodersspringapp.utils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	Base64.Encoder passwordEncoder;

	public String register(RegisterRequestModel model) throws Exception {
		String username = model.getUsername();
		// Encrypt password
		String password = passwordEncoder.encodeToString(model.getPassword().getBytes());
		String email = model.getEmail();
		String token = "";
		// check if account is already in database
		AccountEntity acc = accountRepo.findAccountByUsername(username);
		if (acc == null) {
			// Create new entity model
			// Get count 
			long count = accountRepo.count();
			AccountEntity newAccount = AccountEntity.builder()
					.id(++count)
					.username(username)
					.password(password)
					.email(email)
					.activated(1)
					.build();

			// register new user
			accountRepo.save(newAccount);
			// generate token
			token = tokenUtil.encoder(username, password);

		} else {
			throw new Exception(String.format("Username %s is already taken", username));
		}
		return token;
	}

	public String login(String username, String password) throws Exception {
		String encrPassword = passwordEncoder.encodeToString(password.getBytes());
		AccountEntity acc = accountRepo.findAccountByUsernameAndPassword(username, encrPassword);
		String token = "";
		if (acc == null) {
			throw new Exception("Either username or password is incorrect");
		} else {
			token = tokenUtil.encoder(username, encrPassword);
		}
		return token;
	}

	public void removeUser(String username) {
		//Get account
		AccountEntity acc = accountRepo.findAccountByUsername(username);
		acc.setActivated(0);
		accountRepo.save(acc);
	}
	
	public String updateUser(String username, UpdateUserRequestModel model) throws DuplicateKeyException {
		AccountEntity acc = accountRepo.findAccountByUsername(username);
		String newUsername = model.getUsername();
		String newPassowrd = model.getPassword();
		String newEmail = model.getEmail();
		// Check if username is different and duplicated
		if (!newUsername.equals(username)) {
			AccountEntity usernameAcc = accountRepo.findAccountByUsername(newUsername);
			if (usernameAcc != null) {
				throw new DuplicateKeyException("Trying to change to a username that already exists");
			}
		}
		// Encrypt password 
		String encrPassword = passwordEncoder.encodeToString(newPassowrd.getBytes());
		acc.setUsername(newUsername);
		acc.setPassword(encrPassword);
		acc.setEmail(newEmail);
		accountRepo.save(acc);
		//Generate new token
		String token = tokenUtil.encoder(newUsername, encrPassword);
		return token;
	}

}
