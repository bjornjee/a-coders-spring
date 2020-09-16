package com.example.acodersspringapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="market_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataEntity {

	@Id
	private long id;
	private String datetime;
	private String ticker;
	private double quotePrice;
	private int volume;
	private double open;
	private double previousClose;
	private double peRatio;
}
