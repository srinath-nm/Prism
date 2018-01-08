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
import com.hp.prism.asset.restApi.model.DdiOsTypesModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;


public class DdiOsTypesServiceImpl implements DdiOsTypesService {
	
	private static final Logger logger = Logger.getLogger(DdiOsTypesServiceImpl.class);

	@Override
	public List<DdiOsTypesModel> getDdiOsTypes(String select, String os_Type) {
		
		logger.debug("Entry to getAllDdiOsTypes"); 
		
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;		
		List<DdiOsTypesModel> DdiOsTypesList = new ArrayList<>();
		

		try {
				curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.DDI_OS_TYPES);
				
				if(select != null ||  os_Type != null ) {
					curlPart=curlPart+"?";
				
					if(select != null ) {
						curlPart=curlPart+"select="+select;
					}
					if (os_Type != null  ) {
												
							curlPart=curlPart+"&&OS_Type=eq."+os_Type;						
						
					}
				}
				
					logger.debug("curlPart"+curlPart);
					
					httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
				
				if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
					
					// read the response and set the OSType value to DDIOsTypes
					BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String httpBodyOutput = null;
					while ((httpBodyOutput = br.readLine()) != null) {
						
						JSONArray jsonArr = new JSONArray(httpBodyOutput);
						for(int i =0; i<jsonArr.length(); i++) {
							
							DdiOsTypesModel ddiOsTypes = new DdiOsTypesModel();
							json = jsonArr.getJSONObject(i);
							if ( json.has("OS_Type") && !(json.isNull("OS_Type"))) {
								ddiOsTypes.setOsType(json.getString("OS_Type"));
							}
						
																					
							DdiOsTypesList.add(ddiOsTypes);
						
						}
					} 
				}
				else {
					throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
				}
			
				
				} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("Exit from getDdiOsTypes :: DdiOsTypesList size- "+DdiOsTypesList.size());
		
		return DdiOsTypesList;
	}


}
