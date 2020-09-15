package com.example.acodersspringapp.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTradesRequestModel {

    private String type;
    private String ticker;
    private int quantity;
    private double price;
	private String instrument;
}
