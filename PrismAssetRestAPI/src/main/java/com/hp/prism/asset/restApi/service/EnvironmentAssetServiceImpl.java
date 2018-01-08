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
import com.hp.prism.asset.restApi.model.EnvironmentAssetModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;
import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;


public class EnvironmentAssetServiceImpl implements EnvironmentAssetService {
	
	private static final Logger logger = Logger.getLogger(EnvironmentAssetServiceImpl.class);

	@Override
	public List<EnvironmentAssetModel> getEnvironmentAsset(String select, String environmentName, String environmentCode){
		logger.debug("Inside getEnvironmentAsset- EnvironmentCode-"+environmentCode); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<EnvironmentAssetModel> environmentAssetList = new ArrayList<>();
		
		try {
				curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.ENVIRONMENT_ASSET) ;
				if(select != null || environmentName != null || environmentCode != null ) {
					curlPart=curlPart+"?";
				
					if(select != null ) {
						curlPart=curlPart+"select="+select;
					}
					if (environmentName != null || environmentCode != null) {
						
					
						if (environmentName != null) {
							
							curlPart=curlPart+"&&EnvironmentName=eq."+environmentName;
						
						}
						if (environmentCode != null) {
							
							curlPart=curlPart+"&&EnvironmentCode=eq."+environmentCode;
						}
					}
				}
				
					logger.debug("curlPart"+curlPart);
					
					httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
				
				if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
					// read the response and set the Environment value to Environment_Asset
					BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String httpBodyOutput = null;
					while ((httpBodyOutput = br.readLine()) != null) {
						
						JSONArray jsonArr = new JSONArray(httpBodyOutput);
						for(int i =0; i<jsonArr.length(); i++) {
							
							EnvironmentAssetModel environmentAsset = new EnvironmentAssetModel();
							json = jsonArr.getJSONObject(i);
						
							if ( json.has("EnvironmentName")  && !(json.isNull("EnvironmentName"))) {
								environmentAsset.setEnvironmentName( json.getString("EnvironmentName"));
							}
							if ( json.has("EnvironmentCode")  && !(json.isNull("EnvironmentCode"))) {
								environmentAsset.setEnvironmentCode(json.getString("EnvironmentCode"));
							}
							
							environmentAssetList.add(environmentAsset);
						
						}
					} 
				}
				else {
					throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
				}
			
				
				} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("Exit from getEnvironmentAsset :: EnvironmentAssetList size- "+environmentAssetList.size());
		
		return environmentAssetList;
	}
	
	
	public EnvironmentAssetModel postEnvironmentAsset(EnvironmentAssetModel environmentAsset){
		logger.debug("Inside postEnvironmentAsset"); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		try {
			curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.ENVIRONMENT_ASSET_TAB);
			json.put("EnvironmentName", environmentAsset.getEnvironmentName());
			json.put("EnvironmentCode", environmentAsset.getEnvironmentCode());
			String inputJson = json.toString();
			
			httpURLConnection = PostgRESTHelper.postRequestToPostgRestApi(curlPart, inputJson);
			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				String header=httpURLConnection.getHeaderField("Location");
							
			} else {				
				environmentAsset.setMessage("Error in Environment Asset Creation "+httpURLConnection.getResponseMessage());
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		logger.debug("Exit from postEnvironmentAsset :: message---"+environmentAsset.getMessage());
		return environmentAsset;

	}


}
