package com.example.acodersspringapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.acodersspringapp.entity.TradeEntity;
import com.example.acodersspringapp.entity.TradeInstrument;
import com.example.acodersspringapp.entity.TradeState;
import com.example.acodersspringapp.entity.TradeType;
import com.example.acodersspringapp.model.AssetInfoModel;
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
		TradeType type = model.getType().toUpperCase().equals("BUY") ? TradeType.BUY : TradeType.SELL;
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
			case "STOCK":
				out = TradeInstrument.STOCK;
				break;
			case "ETF":
				out = TradeInstrument.ETF;
				break;
		}
		return out;
	}


	public PortfolioResponseModel getPorfolioByUsername(String username) {
		PortfolioResponseModel returnValue = new PortfolioResponseModel();
		List<TradeEntity> trades = tradeRepo.findAllFilledTradesByUsername(username);
		//Loop through trades by instruments and get aggregate price
		HashMap<String,HashMap<String,AssetInfoModel>> portfolio = new HashMap<>();
		for (TradeEntity trade : trades) {
			String instr = trade.getInstrument().toString();
			String ticker = trade.getTicker();
			if (!portfolio.containsKey(instr)) {
				portfolio.put(instr, new HashMap<String,AssetInfoModel>());
				//Add current ticker
				portfolio.get(instr).put(ticker,new AssetInfoModel(trade));
			} else {
				//Check if ticker exists 
				HashMap<String,AssetInfoModel> assets = portfolio.get(instr);
				if (!assets.containsKey(ticker)) {
					assets.put(ticker, new AssetInfoModel(trade));
					portfolio.put(instr, assets);
				} else {
					AssetInfoModel asset = assets.get(ticker);
					//logic for buy/sell
					int qtyDelta = trade.getType().equals(TradeType.BUY) ? trade.getQuantity() : -trade.getQuantity();
					double priceDelta = qtyDelta * trade.getPrice();
					asset.setQuantity(asset.getQuantity() + qtyDelta);		
					asset.setTotalCost(asset.getTotalCost() + priceDelta);
					assets.put(ticker, asset);
					portfolio.put(instr, assets);
				}
			}
			

		}
		//convert inner hashmap to list
		HashMap<String,List<AssetInfoModel>> convertedPortfolio = new HashMap<>();
		for (Map.Entry<String,HashMap<String,AssetInfoModel>> set: portfolio.entrySet()) {
			List<AssetInfoModel> assetList = new ArrayList<>(set.getValue().values());
			convertedPortfolio.put(set.getKey(),assetList);
		}
		
		returnValue.setPortfolio(convertedPortfolio);
		return returnValue;
	}


	public List<TradeInfoModel> getTradeHistor1yByUsername(String username) {
		List<TradeEntity> tradeData = tradeRepo.findAllByUsername(username);
		return tradeData.stream().map(TradeInfoModel::new).collect(Collectors.toList());
	}
}
