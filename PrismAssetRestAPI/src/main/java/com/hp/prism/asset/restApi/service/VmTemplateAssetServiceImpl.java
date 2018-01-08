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
import com.hp.prism.asset.restApi.model.VmTemplateAssetModel;
import com.hp.prism.asset.restApi.util.PostgRESTHelper;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;


public class VmTemplateAssetServiceImpl implements VmTemplateAssetService {
	
	private static final Logger logger = Logger.getLogger(VmTemplateAssetServiceImpl.class);

	@Override
	public List<VmTemplateAssetModel> getVmTemplateAsset(String select, String osType,String templateName, Integer osDiskSize_GB) {
		
		logger.debug("Inside getVmTemplateAsset- TemplateName-"+templateName); 
		JSONObject json = new JSONObject();
		HttpURLConnection httpURLConnection = null;
		String curlPart = null;
		List<VmTemplateAssetModel> vmTemplateAssetList = new ArrayList<>();
		
		try {
				curlPart = PrismConfiguration.getInstance().getProperty(PrismConstant.VM_TEMPLATE_ASSET);
				
				if(select != null || osType != null || templateName != null || osDiskSize_GB != null ) {
					
					curlPart=curlPart+"?";
				
					if(select != null ) {
						
						curlPart=curlPart+"select="+select;
					}
					if (osType != null || templateName != null) {
						
					
						if (osType != null) {
							
							curlPart=curlPart+"&&OSType=eq."+osType;
						
						}
						if (templateName != null) {
							
							curlPart=curlPart+"&&TemplateName=eq."+templateName;
						}
						
						if (osDiskSize_GB != null) {
							
							curlPart=curlPart+"&&OSDiskSize_GB=eq."+templateName;
						}
						
						
					}
				}
				
					logger.debug("curlPart"+curlPart);
					
					httpURLConnection = PostgRESTHelper.getResponseFromPostgRestApi(curlPart);
				
				if (httpURLConnection.getResponseCode() == PrismConstant.HTTP_OK) {
					// read the response and set the VmTemplate value to VmTemplate_Asset
					BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String httpBodyOutput = null;
					while ((httpBodyOutput = br.readLine()) != null) {
						
						JSONArray jsonArr = new JSONArray(httpBodyOutput);
						for(int i =0; i<jsonArr.length(); i++) {
							
							VmTemplateAssetModel vmTemplateAsset = new VmTemplateAssetModel();
							json = jsonArr.getJSONObject(i);
						
							if ( json.has("OSType")  && !(json.isNull("OSType"))) {
								vmTemplateAsset.setOsType( json.getString("OSType"));
							}
							if ( json.has("TemplateName")  && !(json.isNull("TemplateName"))) {
								vmTemplateAsset.setTemplateName(json.getString("TemplateName"));
							}
							
							if ( json.has("OSDiskSize_GB")  && !(json.isNull("OSDiskSize_GB"))) {
								vmTemplateAsset.setOSDiskSize_GB(json.getInt("OSDiskSize_GB"));
							}
							
							vmTemplateAssetList.add(vmTemplateAsset);
						
						}
					} 
				}
				else {
					throw new RequestException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
				}
			
				
				} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("Exit from getVmTemplateAsset :: vmTemplateAssetList size- "+vmTemplateAssetList.size());
		
		return vmTemplateAssetList;
	}

}
