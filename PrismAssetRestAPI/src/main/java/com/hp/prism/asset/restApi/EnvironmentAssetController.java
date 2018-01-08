package com.hp.prism.asset.restApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
//import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hp.prism.asset.restApi.model.EnvironmentAssetModel;
import com.hp.prism.asset.restApi.service.EnvironmentAssetService;
import com.hp.prism.asset.restApi.service.EnvironmentAssetServiceImpl;

@Path("EnvironmentAsset")
public class EnvironmentAssetController {
	
	EnvironmentAssetService eaService= new EnvironmentAssetServiceImpl(); 

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getEnvironmentAsset(@QueryParam("Select") String select,
			@QueryParam("EnvironmentName") String environmentName,
			@QueryParam("EnvironmentCode") String environmentCode) {		
		List<EnvironmentAssetModel> environmentAssetList =  eaService.getEnvironmentAsset(select,environmentName,environmentCode);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(environmentAssetList);
		return jsonInString;		
	}	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createEnvironmentAsset(String jsonString) {
		System.out.println("createEnvironmentAsset ");
		EnvironmentAssetModel environmentAsset= null;
		try {
		Gson gson = new Gson();
		environmentAsset = gson.fromJson(jsonString, EnvironmentAssetModel.class);
		}
		catch(JsonSyntaxException jse) {
			
			
		}
		environmentAsset = eaService.postEnvironmentAsset(environmentAsset);
		System.out.println(environmentAsset);
		return environmentAsset.getMessage();
	}

}
