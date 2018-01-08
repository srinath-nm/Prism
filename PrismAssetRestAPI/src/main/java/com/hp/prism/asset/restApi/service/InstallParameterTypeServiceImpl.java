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
import com.hp.prism.asset.restApi.model.InstallParameterTypeModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;

public class InstallParameterTypeServiceImpl implements InstallParameterTypeService{
	
private static final Logger logger = Logger.getLogger(InstallParameterTypeServiceImpl.class);
	
	
	public List<InstallParameterTypeModel> getInstallParameterType(String select, String parameterType) {
		
		logger.debug("Inside getInstallParameterType- ParameterType-"+parameterType); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<InstallParameterTypeModel> installParameterTypeList = new ArrayList<>();
		
		try {
						curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.INSTALL_PARAMETER_TYPE);
						
						if(select != null ||  parameterType != null ) {
							curlPart=curlPart+"?";
						
							if(select != null ) {
								curlPart=curlPart+"select="+select;
							}
							if (parameterType != null  ) {
														
									curlPart=curlPart+"&&ParameterType=eq."+parameterType;						
								
							}
						}
						
							logger.debug("curlPart"+curlPart);
							
							httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
						
						if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
							
							// read the response and set the ParameterType value to Install Parameter Type
							BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
							String httpBodyOutput = null;
							while ((httpBodyOutput = br.readLine()) != null) {
								
								JSONArray jsonArr = new JSONArray(httpBodyOutput);
								for(int i =0; i<jsonArr.length(); i++) {
									
									InstallParameterTypeModel installParameterType = new InstallParameterTypeModel();
									json = jsonArr.getJSONObject(i);
									if ( json.has("ParameterType") && !(json.isNull("ParameterType"))) {
										installParameterType.setParameterType(json.getString("ParameterType"));
									}
																															
									installParameterTypeList.add(installParameterType);
								
								}
							} 
						}
						else {
							throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
						}
					
						
						} catch (IOException e) {
					e.printStackTrace();
				}

				logger.debug("Exit from getinstallParameterType :: installParameterTypeList size- "+installParameterTypeList.size());
				
				return installParameterTypeList;
			}
	
}
