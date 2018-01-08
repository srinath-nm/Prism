package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.DiskBuildOperationsModel;

public interface DiskBuildOperationsService {

	public List<DiskBuildOperationsModel> getDiskBuildOperations(String select, String operation);

}

