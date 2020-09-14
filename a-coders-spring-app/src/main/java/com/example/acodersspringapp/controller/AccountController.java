package com.example.acodersspringapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.acodersspringapp.model.request.UpdateUserRequestModel;
import com.example.acodersspringapp.model.response.PortfolioResponseModel;
import com.example.acodersspringapp.service.AccountService;
import com.example.acodersspringapp.service.TokenService;
import com.example.acodersspringapp.service.TradeService;

@RestController
@RequestMapping(value="/api/account")
public class AccountController {

	@Autowired
	private TradeService tradeService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value="/{username}")
	public ResponseEntity<?> getPortfolio(@PathVariable String username, @RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String tokenUsername = tokenService.getUsernameFromToken(token);
		if (tokenUsername != username) {
			return ResponseEntity.badRequest().body("Not authoirized to view this page");
		}
		PortfolioResponseModel portfolio = tradeService.getPorfolioByUsername(username);
		return ResponseEntity.ok(portfolio);
	}
	
	@DeleteMapping(value="/{username}")
	public ResponseEntity<?> removeUser(@PathVariable String username, @RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String tokenUsername = tokenService.getUsernameFromToken(token);
		if (!tokenUsername.equals(username)) {
			return ResponseEntity.badRequest().body("Not authoirized to view this page");
		}
		accountService.removeUser(username);
		return ResponseEntity.ok("User Removed");
	}
	
	@PutMapping(value="/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UpdateUserRequestModel model, @RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		String newToken;
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String tokenUsername = tokenService.getUsernameFromToken(token);
		if (!tokenUsername.equals(username)) {
			return ResponseEntity.badRequest().body("Not authoirized to view this page");
		}
		try {
			newToken = accountService.updateUser(username,model);
		} catch (DuplicateKeyException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok(newToken);
	}
	
}  
