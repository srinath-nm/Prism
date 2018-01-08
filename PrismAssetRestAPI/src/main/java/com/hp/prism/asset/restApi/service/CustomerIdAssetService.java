/**
 * 
 */
package com.hp.prism.asset.restApi.service;

import java.util.List;

import com.hp.prism.asset.restApi.model.CustomerIdAssetModel;

/**
 * @author jayanthi.krishnan
 *
 */
public interface CustomerIdAssetService {
	public List<CustomerIdAssetModel> getCustomerIdAsset(String select, Long customer_Id, String customer);

}
