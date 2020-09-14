package com.example.acodersspringapp.model;

import com.example.acodersspringapp.entity.TradeInstrument;

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
	
}
