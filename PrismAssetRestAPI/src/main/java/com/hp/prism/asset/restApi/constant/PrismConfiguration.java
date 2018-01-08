package com.hp.prism.asset.restApi.constant;

import java.util.Properties;

import com.hp.prism.asset.restApi.constant.PrismConfiguration;
import com.hp.prism.asset.restApi.constant.PrismConstant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class PrismConfiguration {
	
	private Properties properties = null;
	private static PrismConfiguration instance = null;

	/** Private constructor */
	private PrismConfiguration (){
		this.properties = new Properties();
		InputStream inStream = null;

		try{
//			System.out.println(System.getProperty("user.dir"));
			//inStream = new FileInputStream(PrismConstant.PATH_CONFFILE);
			properties.load(getClass().getResourceAsStream(PrismConstant.PATH_CONFFILE));
			//properties.load(inStream);
		}catch(IOException ioex){
			ioex.printStackTrace();
		}  catch(Exception ex){
			ex.printStackTrace();
		}
	}   

	/** Creates the instance is synchronized to avoid multithreads problems */
	private synchronized static void createInstance () {
		if (instance == null) { 
			instance = new PrismConfiguration ();
		}
	}

	/** Get the properties instance. Uses singleton pattern */
	public static PrismConfiguration getInstance(){
		// Uses singleton pattern to guarantee the creation of only one instance
		if(instance == null) {
			createInstance();
		}
		return instance;
	}

	/** Get a property of the property file */
	public String getProperty(String key){
		String result = null;
		if(key !=null && !key.trim().isEmpty()){
			result = this.properties.getProperty(key);
		}
		return result;
	}

	/** Override the clone method to ensure the "unique instance" requeriment of this class */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
