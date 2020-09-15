package com.example.acodersspringapp.model;

import java.util.List;

import com.example.acodersspringapp.entity.TradeInstrument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstumentInfoModel {
	private TradeInstrument instrument;
	List<AssetInfoModel> assets;
}
