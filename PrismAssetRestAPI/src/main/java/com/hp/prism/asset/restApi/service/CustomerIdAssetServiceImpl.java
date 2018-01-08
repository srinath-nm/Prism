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
import com.hp.prism.asset.restApi.model.CustomerIdAssetModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;

public class CustomerIdAssetServiceImpl implements CustomerIdAssetService {
	
	private static final Logger logger = Logger.getLogger(CustomerIdAssetServiceImpl.class);
	
	@Override
	public List<CustomerIdAssetModel> getCustomerIdAsset(String select, Long customer_Id, String customer) {
		
		logger.debug("Inside getCustomerIdAsset- customerId-"+customer_Id); 
		
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<CustomerIdAssetModel> CustomerIdAssetList = new ArrayList<>();
		
		try {
				curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.CUSTOMER_ID_ASSET);
				
				if(select != null || customer_Id != null ||  customer != null ) {
					curlPart=curlPart+"?";
				
					if(select != null ) {
						curlPart=curlPart+"select="+select;
					}
					if (customer_Id != null || customer != null ) {
						
												
						if ( customer_Id != null) {
						
							curlPart=curlPart+"&&Customer_ID=eq."+customer_Id;
						
						}
						
						if (customer != null) {
							
							curlPart=curlPart+"&&Customer=eq."+customer;
						
						}
						
					}
				}
				
					logger.debug("curlPart"+curlPart);
					
					httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
				
				if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
					
					// read the response and set the CustomerId value to Customer_Id_Asset
					BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String httpBodyOutput = null;
					while ((httpBodyOutput = br.readLine()) != null) {
						
						JSONArray jsonArr = new JSONArray(httpBodyOutput);
						for(int i =0; i<jsonArr.length(); i++) {
							
							CustomerIdAssetModel customerIdAsset = new CustomerIdAssetModel();
							json = jsonArr.getJSONObject(i);
							if ( json.has("Customer_ID") && !(json.isNull("Customer_ID"))) {
								customerIdAsset.setCustomer_Id(json.getLong("Customer_ID"));
							}
						
							if ( json.has("Customer") && !(json.isNull("Customer"))) {
								customerIdAsset.setCustomer( json.getString("Customer"));
							}
														
							CustomerIdAssetList.add(customerIdAsset);
						
						}
					} 
				}
				else {
					throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
				}
			
				
				} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("Exit from getCustomerIdAsset :: CustomerIdAssetList size- "+CustomerIdAssetList.size());
		
		return CustomerIdAssetList;
	}

	}


