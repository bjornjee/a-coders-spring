package com.example.acodersspringapp.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="trade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeEntity {
	@Id
    private long id;
    private String username;
    private Date created;
    private TradeState state = TradeState.CREATED;
    private TradeType type;
    private TradeInstrument instrument;
    private String ticker;
    private int quantity;
    private double price;
}
