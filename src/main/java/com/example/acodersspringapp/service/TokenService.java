package com.example.acodersspringapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acodersspringapp.entity.AccountEntity;
import com.example.acodersspringapp.repository.AccountRepository;
import com.example.acodersspringapp.utils.TokenUtil;
import com.example.acodersspringapp.utils.model.UtilModel;

@Service
public class TokenService {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	TokenUtil tokenUtil;
	
	public boolean isValidToken(String token) {
		UtilModel model = tokenUtil.decoder(token);
		if (model == null) {
			return false;
		} else {
			String username = model.getUsername();
			String password = model.getPassword();
			//Check if user and pass are valid
			AccountEntity acc = accountRepo.findAccountByUsernameAndPassword(username, password);
			if (acc == null) {
				return false;
			}
		}
		return true;	
	}
	
	public String getUsernameFromToken(String token) {
		String username ="";
		UtilModel model = tokenUtil.decoder(token);
		if (model == null) {
			return null;
		} else {
			username = model.getUsername();
		}
		return username;
	}
}
