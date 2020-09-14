package com.example.acodersspringapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.acodersspringapp.model.request.RegisterRequestModel;
import com.example.acodersspringapp.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/api/auth/")
@Slf4j
public class AuthController {

	@Autowired
	AccountService accountService;
	
	@PostMapping(value="/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequestModel model) {
		String token = accountService.register(model);
		return ResponseEntity.ok(token);
	}
	
	@GetMapping(value="/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
		String token = accountService.login(username, password);
		return ResponseEntity.ok(token);
	}
}
