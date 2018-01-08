package com.hp.prism.asset.restApi.service;

import java.util.List;


import com.hp.prism.asset.restApi.model.InstallParameterTypeModel;

public interface InstallParameterTypeService {
	
	public List<InstallParameterTypeModel> getInstallParameterType(String select, String parameterType);

}
