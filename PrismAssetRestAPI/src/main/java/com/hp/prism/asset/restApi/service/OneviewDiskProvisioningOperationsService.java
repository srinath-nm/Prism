package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.OneviewDiskProvisioningOperationsModel;

public interface OneviewDiskProvisioningOperationsService {

	public List<OneviewDiskProvisioningOperationsModel> getOneviewDiskProvisioningOperations(String select, String provisioningType);
}

