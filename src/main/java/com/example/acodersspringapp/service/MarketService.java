package com.example.acodersspringapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acodersspringapp.model.response.MarketDataResponseModel;
import com.example.acodersspringapp.repository.MarketRepository;

@Service
public class MarketService {

	@Autowired
	MarketRepository marketRepo;
	
	public List<MarketDataResponseModel> getAllData() {
		return marketRepo.findAll().stream().map(MarketDataResponseModel::new).collect(Collectors.toList());
	}
	
}
