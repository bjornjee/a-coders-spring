package com.example.acodersspringapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.acodersspringapp.service.MarketService;

@RestController
@RequestMapping("/api/market")
public class MarketController {
	
	@Autowired
	MarketService marketService;

	@GetMapping("/data")
	public ResponseEntity<?> getMarketData() {
		return ResponseEntity.ok(marketService.getAllData());
	}
}
