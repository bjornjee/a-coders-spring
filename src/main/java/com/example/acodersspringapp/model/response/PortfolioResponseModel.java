package com.example.acodersspringapp.model.response;
import java.util.List;

import com.example.acodersspringapp.model.AssetInfoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResponseModel {
	List<AssetInfoModel> portfolio;
}
