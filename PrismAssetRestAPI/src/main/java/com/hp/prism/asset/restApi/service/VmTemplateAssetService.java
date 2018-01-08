package com.hp.prism.asset.restApi.service;

import java.util.List;
import com.hp.prism.asset.restApi.model.VmTemplateAssetModel;

public interface VmTemplateAssetService {

	public List<VmTemplateAssetModel> getVmTemplateAsset(String select, String osType,String templateName,Integer osDiskSize_GB);

}

