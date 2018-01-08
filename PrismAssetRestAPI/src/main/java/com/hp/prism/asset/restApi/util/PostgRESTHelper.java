package com.hp.prism.asset.restApi.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;


public class PostgRESTHelper {
	
	private static final Logger logger = Logger.getLogger(PostgRESTHelper.class);
	
	public static HttpURLConnection getResponseFromPostgRestApi(String curlPart) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside getResponseFromPostgRestApi");			
		}
		HttpURLConnection httpURLConnection = null;
		URL url = null;
		String curl = null;		
		
		try {
			curl= PrismConfiguration.getInstance().getProperty(PrismConstant.CURL)+curlPart;
			logger.debug("curl-"+curl);
			url = new URL(curl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Authorization",PrismConfiguration.getInstance().getProperty(PrismConstant.JWT_TOKEN));
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()) {
			logger.debug("Exit from getResponseFromPostgRestApi");			
		}
		return httpURLConnection;
	}

	public static HttpURLConnection postRequestToPostgRestApi(String curlPart, String inputJson) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside postRequestToPostgRestApi");			
		}

		HttpURLConnection httpURLConnection = null;
		URL url = null;
		String curl = null;			

		try {
			curl= PrismConfiguration.getInstance().getProperty(PrismConstant.CURL)+curlPart;
			logger.debug("curl-"+curl);
			url = new URL(curl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Authorization", PrismConfiguration.getInstance().getProperty(PrismConstant.JWT_TOKEN));
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			OutputStream os = httpURLConnection.getOutputStream();
			if (inputJson != null) {
				os.write(inputJson.getBytes());
			}
			os.flush();

			httpURLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()) {
			logger.debug("Exit from postRequestToPostgRestApi");			
		}
		return httpURLConnection;
	}

	public static HttpURLConnection putRequestToPostgRestApi(String curlPart, String inputJson) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside putRequestToPostgRestApi");			
		}

		HttpURLConnection httpURLConnection = null;
		URL url = null;
		String curl = null;			

		try {
			curl= PrismConfiguration.getInstance().getProperty(PrismConstant.CURL)+curlPart;
			logger.debug("curl-"+curl);
			url = new URL(curl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("PUT");
			httpURLConnection.setRequestProperty("Authorization", PrismConfiguration.getInstance().getProperty(PrismConstant.JWT_TOKEN));
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			OutputStream os = httpURLConnection.getOutputStream();
			if (inputJson != null) {
				os.write(inputJson.getBytes());
			}
			os.flush();

			httpURLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()) {
			logger.debug("Exit from putRequestToPostgRestApi");			
		}
		return httpURLConnection;
	}

	public static HttpURLConnection deleteResponseFromPostgRestApi(String curlPart) {
		if(logger.isDebugEnabled()) {
			logger.debug("Inside deleteResponseFromPostgRestApi");			
		}
		HttpURLConnection httpURLConnection = null;
		URL url = null;
		String curl = null;		
		
		try {
			curl= PrismConfiguration.getInstance().getProperty(PrismConstant.CURL)+curlPart;
			logger.debug("curl-"+curl);
			url = new URL(curl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("DELETE");
			httpURLConnection.setRequestProperty("Authorization",PrismConfiguration.getInstance().getProperty(PrismConstant.JWT_TOKEN));
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()) {
			logger.debug("Exit from deleteResponseFromPostgRestApi");			
		}
		return httpURLConnection;
	}
	
}
