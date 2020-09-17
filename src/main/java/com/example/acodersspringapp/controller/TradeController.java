package com.example.acodersspringapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.acodersspringapp.model.AssetInfoModel;
import com.example.acodersspringapp.model.CurrentHoldingAssetInfo;
import com.example.acodersspringapp.model.TradeInfoModel;
import com.example.acodersspringapp.model.request.NewTradesRequestModel;
import com.example.acodersspringapp.model.response.PortfolioResponseModel;
import com.example.acodersspringapp.service.TokenService;
import com.example.acodersspringapp.service.TradeService;

@RestController
@RequestMapping(value="/api/trade")
@CrossOrigin
public class TradeController {

	@Autowired
	TradeService tradeService;
	@Autowired
	TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> sendTrades(@RequestBody NewTradesRequestModel model,@RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String username = tokenService.getUsernameFromToken(token);
		tradeService.acceptTrades(username,model);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/{username}")
	public ResponseEntity<?> getTradeHistory(@PathVariable String username, @RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String tokenUsername = tokenService.getUsernameFromToken(token);
		if (!tokenUsername.equals(username)) {
			return ResponseEntity.badRequest().body("Not authoirized to view this page");
		}
		List<TradeInfoModel> trades = tradeService.getTradeHistor1yByUsername(username);
		return ResponseEntity.ok(trades);
	}
	
	@GetMapping(value="/{username}/portfolio")
	public ResponseEntity<?> getPorfolio(@PathVariable String username,@RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String tokenUsername = tokenService.getUsernameFromToken(token);
		if (!tokenUsername.equals(username)) {
			return ResponseEntity.badRequest().body("Not authoirized to view this page");
		}
		List<AssetInfoModel> returnValue = tradeService.getPorfolioByUsername(username);
		return ResponseEntity.ok(returnValue);
	}
	
	@GetMapping(value="/{username}/holdingStock")
	public ResponseEntity<?> getCurrentStocks(@PathVariable String username, @RequestHeader("token") String token) {
		boolean isTokenValid = tokenService.isValidToken(token);
		if (!isTokenValid) {
			return ResponseEntity.badRequest().body("Token not valid");
		}
		String tokenUsername = tokenService.getUsernameFromToken(token);
		if (!tokenUsername.equals(username)) {
			return ResponseEntity.badRequest().body("Not authoirized to view this page");
		}
		List<CurrentHoldingAssetInfo> returnValue = tradeService.getCurrentStocksByUsername(username);
		return ResponseEntity.ok(returnValue);
	}
	
}
