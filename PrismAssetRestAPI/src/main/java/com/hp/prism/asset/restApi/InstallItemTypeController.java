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
import com.hp.prism.asset.restApi.model.InstallItemTypeModel;
import com.hp.prism.asset.restApi.service.InstallItemTypeService;
import com.hp.prism.asset.restApi.service.InstallItemTypeServiceImpl;


@Path("InstallItemType")

public class InstallItemTypeController {
	

	InstallItemTypeService iitService= new InstallItemTypeServiceImpl(); 
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getInstallItemType(@QueryParam("Select") String select,
			@QueryParam("ItemType") String itemType) {		
		List<InstallItemTypeModel> installItemTypeList =  iitService.getInstallItemType(select,itemType);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(installItemTypeList);
		return jsonInString;		
	}
	

}
