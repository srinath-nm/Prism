package com.hp.prism.asset.restApi.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONArray;

import com.hp.prism.asset.restApi.exception.RequestException;
import com.hp.prism.asset.restApi.model.CloudProviderAssetModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;
import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;


public class CloudProviderAssetServiceImpl implements CloudProviderAssetService {
	
	private static final Logger logger = Logger.getLogger(CloudProviderAssetServiceImpl.class);

	
	@Override
	public List<CloudProviderAssetModel> getCloudProviderAsset(String select, String cloudProvider,
			String cloudEnvironment) {
		logger.debug("Inside getCloudProviderAsset- CloudProvider-"+cloudProvider); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<CloudProviderAssetModel> cloudProviderAssetList = new ArrayList<>();
		
		try {
				curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.CLOUD_PROVIDER_ASSET) ;
				if(select != null ||  cloudProvider != null || cloudEnvironment != null ) {
					curlPart=curlPart+"?";
				
					if(select != null ) {
						curlPart=curlPart+"select="+select;
					}
					if (cloudProvider != null || cloudEnvironment != null) {
						
												
					
						if (cloudProvider != null) {
							
							curlPart=curlPart+"&&CloudProvider=eq."+cloudProvider;
						
						}
						if (cloudEnvironment != null) {
							
							curlPart=curlPart+"&&CloudEnvironment=eq."+cloudEnvironment;
						}
					}
				}
				
					logger.debug("curlPart"+curlPart);
					
					httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
				
				if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
					// read the response and set the CloudProvider value to Cloud_Provider_Asset
					BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String httpBodyOutput = null;
					while ((httpBodyOutput = br.readLine()) != null) {
						
						JSONArray jsonArr = new JSONArray(httpBodyOutput);
						for(int i =0; i<jsonArr.length(); i++) {
							
							CloudProviderAssetModel cloudProviderAsset = new CloudProviderAssetModel();
							json = jsonArr.getJSONObject(i);
						
							if ( json.has("CloudProvider")&& !(json.isNull("CloudProvider"))) {
								cloudProviderAsset.setCloudProvider( json.getString("CloudProvider"));
							}
							if ( json.has("CloudEnvironment") && !(json.isNull("CloudEnvironment")) ) {
								cloudProviderAsset.setCloudEnvironment(json.getString("CloudEnvironment"));
							}
							
							cloudProviderAssetList.add(cloudProviderAsset);
						
						}
					} 
				}
				else {
					throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
				}
			
				
				} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("Exit from getCloudProviderAsset :: CloudProviderAsset size- "+cloudProviderAssetList.size());
		
		return cloudProviderAssetList;
	}

	
}
