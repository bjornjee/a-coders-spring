package com.example.acodersspringapp.model;


import java.text.SimpleDateFormat;

import com.example.acodersspringapp.entity.TradeEntity;
import com.example.acodersspringapp.entity.TradeInstrument;
import com.example.acodersspringapp.entity.TradeState;
import com.example.acodersspringapp.entity.TradeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeInfoModel {
    private String created;
    private TradeState state;
    private TradeType type;
    private TradeInstrument instrument;
    private String ticker;
    private int quantity;
    private double price;
    
    public TradeInfoModel(TradeEntity e) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	this.created = formatter.format(e.getCreated());
    	this.state = e.getState();
    	this.type = e.getType();
    	this.instrument = e.getInstrument();
    	this.ticker = e.getTicker();
    	this.quantity = e.getQuantity();
    	this.price = e.getPrice();
    }
}
