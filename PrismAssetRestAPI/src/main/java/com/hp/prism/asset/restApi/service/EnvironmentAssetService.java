package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.EnvironmentAssetModel;

/**
 * @author jayanthi.krishnan
 *
 */

public interface EnvironmentAssetService {
	
	public List<EnvironmentAssetModel> getEnvironmentAsset(String select, String environmentName, String environmentCode);
	public EnvironmentAssetModel postEnvironmentAsset(EnvironmentAssetModel environmentAsset);

}
