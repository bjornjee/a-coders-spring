package com.example.acodersspringapp.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.acodersspringapp.model.AssetInfoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="portfolio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioEntity {
	@Id
	private long id;
	private String username;
	private List<AssetInfoModel> assets;
}
