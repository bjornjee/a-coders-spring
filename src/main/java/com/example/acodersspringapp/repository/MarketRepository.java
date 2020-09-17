package com.example.acodersspringapp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.acodersspringapp.entity.MarketDataEntity;

@Repository
public interface MarketRepository extends MongoRepository<MarketDataEntity, Long>{

	@Query("{'ticker':?0}")
	public MarketDataEntity getOneByTicker(String ticker);
}
