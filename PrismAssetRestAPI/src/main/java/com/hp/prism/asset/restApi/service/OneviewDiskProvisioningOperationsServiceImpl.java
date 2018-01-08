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
import com.hp.prism.asset.restApi.model.OneviewDiskProvisioningOperationsModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;


public class OneviewDiskProvisioningOperationsServiceImpl implements OneviewDiskProvisioningOperationsService {
	
	private static final Logger logger = Logger.getLogger(OneviewDiskProvisioningOperationsServiceImpl.class);

	
	@Override
	public List<OneviewDiskProvisioningOperationsModel> getOneviewDiskProvisioningOperations(String select, String provisioningType) {
		logger.debug("Inside getOneviewDiskProvisioningOperations- ProvisioningType-"+provisioningType); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<OneviewDiskProvisioningOperationsModel> oneviewDiskProvisioningOperationsList = new ArrayList<>();
		
		try {
							curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.ONEVIEW_DISK_PROVISIONING_OPERATIONS);
							
							if(select != null ||  provisioningType != null ) {
								curlPart=curlPart+"?";
							
								if(select != null ) {
									curlPart=curlPart+"select="+select;
								}
								if (provisioningType != null  ) {
															
										curlPart=curlPart+"&&ProvisioningType=eq."+provisioningType;						
									
								}
							}
							
								logger.debug("curlPart"+curlPart);
								
								httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
							
							if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
								
								// read the response and set the ProvisioningType value to oneviewDiskProvisioningOperations_Asset
								BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
								String httpBodyOutput = null;
								while ((httpBodyOutput = br.readLine()) != null) {
									
									JSONArray jsonArr = new JSONArray(httpBodyOutput);
									for(int i =0; i<jsonArr.length(); i++) {
										
										OneviewDiskProvisioningOperationsModel oneviewDiskProvisioningOperations = new OneviewDiskProvisioningOperationsModel();
										json = jsonArr.getJSONObject(i);
										if ( json.has("ProvisioningType") && !(json.isNull("ProvisioningType"))) {
											oneviewDiskProvisioningOperations.setProvisioningType(json.getString("ProvisioningType"));
										}
																																
										oneviewDiskProvisioningOperationsList.add(oneviewDiskProvisioningOperations);
									
									}
								} 
							}
							else {
								throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
							}
						
							
							} catch (IOException e) {
						e.printStackTrace();
					}

					logger.debug("Exit from getOneviewDiskProvisioningOperations :: oneviewDiskProvisioningOperations size- "+oneviewDiskProvisioningOperationsList.size());
					
					return oneviewDiskProvisioningOperationsList;
				}
		
}
