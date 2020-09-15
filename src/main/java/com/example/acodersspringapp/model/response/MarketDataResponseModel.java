package com.example.acodersspringapp.model.response;

import com.example.acodersspringapp.entity.MarketDataEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataResponseModel {

	private String ticker;
	private double quotePrice;
	private int volume;
	private double open;
	private double previousClose;
	private double peRatio;
	
	public MarketDataResponseModel(MarketDataEntity e) {
		this.ticker = e.getTicker();
		this.quotePrice = e.getQuotePrice();
		this.volume = e.getVolume();
		this.open = e.getOpen();
		this.previousClose = e.getPreviousClose();
		this.peRatio = e.getPeRatio();
	}
}
