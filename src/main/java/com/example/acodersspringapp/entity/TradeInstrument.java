package com.example.acodersspringapp.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TradeInstrument {
	BOND("BOND"),
	FUTURE("FUTURE"),
	CASH("CASH"),
	SWAP("SWAP"),
	STOCK("STOCK"),
	ETF("ETF");
	private String instrument;
	
	public String getInstrument() {
		return this.instrument;
	}
}
