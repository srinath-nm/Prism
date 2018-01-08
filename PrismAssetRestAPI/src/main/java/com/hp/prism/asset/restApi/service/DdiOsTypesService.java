package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.DdiOsTypesModel;

public interface DdiOsTypesService {
	
	public List<DdiOsTypesModel> getDdiOsTypes(String select, String os_Type);

}
