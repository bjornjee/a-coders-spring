package com.example.acodersspringapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.acodersspringapp.entity.TradeEntity;

@Repository
public interface TradeRepository extends MongoRepository<TradeEntity, Long>{

	@Query("{'username':?0}")
	List<TradeEntity> findAllByUsername(String username);
	
	@Query("{'username':?0,'state':'FILLED'}")
	List<TradeEntity> findAllFilledTradesByUsername(String username);
	
}
