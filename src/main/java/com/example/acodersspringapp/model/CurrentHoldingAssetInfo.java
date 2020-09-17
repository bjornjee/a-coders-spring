package com.example.acodersspringapp.model;


import com.example.acodersspringapp.entity.TradeEntity;
import com.example.acodersspringapp.entity.TradeInstrument;
import com.example.acodersspringapp.entity.TradeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentHoldingAssetInfo {
	
	private String ticker;
	private int quantity;
	private double price;
	private TradeInstrument instrument;
	
	public CurrentHoldingAssetInfo(TradeEntity e) {
		this.ticker = e.getTicker();
		this.quantity = (e.getType().equals(TradeType.BUY)) ? e.getQuantity() : -e.getQuantity();
		this.instrument = e.getInstrument();
	}
}
