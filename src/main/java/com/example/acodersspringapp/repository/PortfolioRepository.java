package com.example.acodersspringapp.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.acodersspringapp.entity.PortfolioEntity;

@Repository
public interface PortfolioRepository extends MongoRepository<PortfolioEntity, Long>{

	@Query("{'username':?0}")
	public PortfolioEntity findPortfolioByUsername(String username);
}
