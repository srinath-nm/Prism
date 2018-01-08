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
import com.hp.prism.asset.restApi.model.DiskBuildOperationsModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;



public class DiskBuildOperationsServiceImpl implements DiskBuildOperationsService {
	
	private static final Logger logger = Logger.getLogger(DiskBuildOperationsServiceImpl.class);

	@Override
	public List<DiskBuildOperationsModel> getDiskBuildOperations(String select, String operation) {
		
		logger.debug("Inside getDiskBuildOperations- Operation-"+operation); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<DiskBuildOperationsModel> diskBuildOperationsList = new ArrayList<>();
		

		try {
				curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.DISK_BUILD_OPERATIONS);
				
				if(select != null ||  operation != null ) {
					curlPart=curlPart+"?";
				
					if(select != null ) {
						curlPart=curlPart+"select="+select;
					}
					if (operation != null  ) {
												
							curlPart=curlPart+"&&Operation=eq."+operation;						
						
					}
				}
				
					logger.debug("curlPart"+curlPart);
					
					httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
				
				if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
					
					// read the response and set the Operations value to DiskBuildOperatons
					BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String httpBodyOutput = null;
					while ((httpBodyOutput = br.readLine()) != null) {
						
						JSONArray jsonArr = new JSONArray(httpBodyOutput);
						for(int i =0; i<jsonArr.length(); i++) {
							
							DiskBuildOperationsModel diskBuildOperation = new DiskBuildOperationsModel();
							json = jsonArr.getJSONObject(i);
							if ( json.has("Operation") && !(json.isNull("Operation"))) {
								diskBuildOperation.setOperation(json.getString("Operation"));
							}
						
																					
							diskBuildOperationsList.add(diskBuildOperation);
						
						}
					} 
				}
				else {
					throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
				}
			
				
				} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("Exit from getDiskBuildOperations :: DiskBuildOperations size- "+diskBuildOperationsList.size());
		
		return diskBuildOperationsList;
	}


}
