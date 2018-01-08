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
import com.hp.prism.asset.restApi.model.VmTemplateAssetModel;
import com.hp.prism.asset.restApi.service.VmTemplateAssetService;
import com.hp.prism.asset.restApi.service.VmTemplateAssetServiceImpl;

@Path("VmTemplateAsset")
public class VmTemplateAssetController {
	
	VmTemplateAssetService vtaService= new VmTemplateAssetServiceImpl(); 

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getVmTemplateAsset(@QueryParam("Select") String select,
			@QueryParam("OSType") String osType,
			@QueryParam("TemplateName") String templateName,
			@QueryParam("OSDiskSize_GB") Integer osDiskSize_GB) {		
		List<VmTemplateAssetModel> vmTemplateAssetList =  vtaService.getVmTemplateAsset(select,osType,templateName,osDiskSize_GB);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(vmTemplateAssetList);
		return jsonInString;		
	}


}
