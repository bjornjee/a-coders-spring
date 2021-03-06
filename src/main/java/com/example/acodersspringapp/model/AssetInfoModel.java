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
public class AssetInfoModel {
	private String ticker;
	private int quantity;
	private double totalCost;
	private TradeInstrument instrument;
	
	public AssetInfoModel(TradeEntity e) {
		ticker = e.getTicker();
		quantity = e.getQuantity();
		totalCost = e.getType().equals(TradeType.BUY) ? e.getPrice() * this.quantity : -e.getPrice() * this.quantity;
		instrument =e.getInstrument();
	}
}

