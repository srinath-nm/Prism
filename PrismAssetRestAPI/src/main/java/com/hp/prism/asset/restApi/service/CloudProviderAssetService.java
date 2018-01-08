package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.CloudProviderAssetModel;

public interface CloudProviderAssetService {

	public List<CloudProviderAssetModel> getCloudProviderAsset(String select,/* Long id,*/ String cloudProvider,
			String cloudEnvironment);

}
