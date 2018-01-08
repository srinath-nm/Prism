package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.InstallItemTypeModel;

/**
 * @author jayanthi.krishnan
 *
 */

public interface InstallItemTypeService {
	
	public List<InstallItemTypeModel> getInstallItemType(String select, String itemType);

}