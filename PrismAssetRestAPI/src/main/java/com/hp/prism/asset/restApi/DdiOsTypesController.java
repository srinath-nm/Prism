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
import com.hp.prism.asset.restApi.model.DdiOsTypesModel;
import com.hp.prism.asset.restApi.service.DdiOsTypesService;
import com.hp.prism.asset.restApi.service.DdiOsTypesServiceImpl;

@Path("DdiOsTypes")
public class DdiOsTypesController {
	
	DdiOsTypesService dotService= new DdiOsTypesServiceImpl(); 

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDdiOsTypes(@QueryParam("Select") String select,
			@QueryParam("OS_Type") String os_Type) {		
		List<DdiOsTypesModel> DdiOsTypesList =  dotService.getDdiOsTypes(select,os_Type);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(DdiOsTypesList);
		return jsonInString;		
	}
}
