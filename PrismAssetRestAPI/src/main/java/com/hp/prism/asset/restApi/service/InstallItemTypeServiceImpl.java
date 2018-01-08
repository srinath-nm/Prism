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
import com.hp.prism.asset.restApi.model.InstallItemTypeModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;

public class InstallItemTypeServiceImpl implements InstallItemTypeService{
	
private static final Logger logger = Logger.getLogger(InstallItemTypeServiceImpl.class);
	
	public List<InstallItemTypeModel> getInstallItemType(String select, String itemType) {
		logger.debug("Inside getInstallItemType- ItemType-"+itemType); 
		
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<InstallItemTypeModel> installItemTypeList = new ArrayList<>();
		
		try {
								curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.INSTALL_ITEM_TYPE);
								
								if(select != null ||  itemType != null ) {
									curlPart=curlPart+"?";
								
									if(select != null ) {
										curlPart=curlPart+"select="+select;
									}
									if (itemType != null  ) {
																
											curlPart=curlPart+"&&ItemType=eq."+itemType;						
										
									}
								}
								
									logger.debug("curlPart"+curlPart);
									
									httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
								
								if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
									
									// read the response and set the ItemType value to Install_Item_Type
									BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
									String httpBodyOutput = null;
									while ((httpBodyOutput = br.readLine()) != null) {
										
										JSONArray jsonArr = new JSONArray(httpBodyOutput);
										for(int i =0; i<jsonArr.length(); i++) {
											
											InstallItemTypeModel installItemType = new InstallItemTypeModel();
											json = jsonArr.getJSONObject(i);
											if ( json.has("ItemType")  && !(json.isNull("ItemType"))) {
												installItemType.setItemType(json.getString("ItemType"));
											}
										
																									
											installItemTypeList.add(installItemType);
										
										}
									} 
								}
								else {
									throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
								}
							
								
								} catch (IOException e) {
							e.printStackTrace();
						}

						logger.debug("Exit from getInstallItemType :: installItemTypeList size- "+installItemTypeList.size());
						
						return installItemTypeList;
					}

}
