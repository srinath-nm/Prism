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

import com.hp.prism.asset.restApi.model.CustomerIdAssetModel;
import com.hp.prism.asset.restApi.service.CustomerIdAssetService;
import com.hp.prism.asset.restApi.service.CustomerIdAssetServiceImpl;
import com.google.gson.Gson;

@Path("CustomerIdAsset")
public class CustomerIdAssetController {


	CustomerIdAssetService ciaService= new CustomerIdAssetServiceImpl(); 


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCustomerIdAsset(@QueryParam("Select") String select,
			@QueryParam("Customer_ID") Long customer_Id, 
			@QueryParam("Customer") String customer) {		
		List<CustomerIdAssetModel> CustomerIdAssetList =  ciaService.getCustomerIdAsset(select,customer_Id,customer);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(CustomerIdAssetList);
		return jsonInString;		
	}
	
	
}
