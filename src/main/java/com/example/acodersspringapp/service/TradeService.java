package com.example.acodersspringapp.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acodersspringapp.entity.PortfolioEntity;
import com.example.acodersspringapp.entity.TradeEntity;
import com.example.acodersspringapp.entity.TradeInstrument;
import com.example.acodersspringapp.entity.TradeState;
import com.example.acodersspringapp.entity.TradeType;
import com.example.acodersspringapp.model.TradeInfoModel;
import com.example.acodersspringapp.model.request.NewTradesRequestModel;
import com.example.acodersspringapp.model.response.PortfolioResponseModel;
import com.example.acodersspringapp.repository.PortfolioRepository;
import com.example.acodersspringapp.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepo;
	@Autowired
	PortfolioRepository portfolioRepo;
	
	public void acceptTrades(String username, NewTradesRequestModel model) {
		TradeType type = model.getType() == "BUY" ? TradeType.BUY : TradeType.SELL;
		TradeState state = TradeState.CREATED;
		TradeInstrument instrument = getInstrument(model.getInstrument());
		Date now = new Date(System.currentTimeMillis());
		//Insert into trade collection
		long count = tradeRepo.count();
		TradeEntity newTrade = TradeEntity.builder()
									.id(++count)
									.ticker(model.getTicker())
									.username(username)
									.quantity(model.getQuantity())
									.price(model.getPrice())
									.state(state)
									.created(now)
									.type(type)
									.instrument(instrument)
									.build();
		tradeRepo.save(newTrade);
	}
	
	
	private TradeInstrument getInstrument(String instrument) {
		TradeInstrument out = null;
		instrument = instrument.toUpperCase();
		switch (instrument) {
			case "BOND":
				out = TradeInstrument.BOND;
				break;
			case "FUTURE":
				out= TradeInstrument.FUTURE;
				break;
			case "CASH":
				out = TradeInstrument.CASH;
				break;
			case "SWAP":
				out = TradeInstrument.SWAP;
				break;
		}
		return out;
	}


	public PortfolioResponseModel getPorfolioByUsername(String username) {
		PortfolioEntity portfolio = portfolioRepo.findPortfolioByUsername(username);
		PortfolioResponseModel returnValue = new PortfolioResponseModel(portfolio.getAssets());
		return returnValue;
	}


	public List<TradeInfoModel> getTradeHistor1yByUsername(String username) {
		List<TradeEntity> tradeData = tradeRepo.findAllByUsername(username);
		return tradeData.stream().map(TradeInfoModel::new).collect(Collectors.toList());
	}
}
