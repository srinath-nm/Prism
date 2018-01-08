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
import com.hp.prism.asset.restApi.model.InstallParameterTypeModel;
import com.hp.prism.asset.restApi.service.InstallParameterTypeService;
import com.hp.prism.asset.restApi.service.InstallParameterTypeServiceImpl;


@Path("InstallParameterType")

public class InstallParameterTypeController {
	

	InstallParameterTypeService iptService= new InstallParameterTypeServiceImpl(); 

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getInstallParameterType(@QueryParam("Select") String select,
			@QueryParam("ParameterType") String parameterType) {		
		List<InstallParameterTypeModel> installParameterTypeList =  iptService.getInstallParameterType(select,parameterType);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(installParameterTypeList);
		return jsonInString;		
	}
	

}
