package com.hp.prism.asset.restApi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EnvironmentAssetModel {


	private String EnvironmentName;
	private String EnvironmentCode;
	private String CreatedAt;
	private String UpdatedAt;
	private String Message;

	public EnvironmentAssetModel() {			
		
	}
	
	public EnvironmentAssetModel(String environmentName, String environmentCode,String createdAt, String updatedAt) {
		
		this.EnvironmentCode    = environmentCode;
		this.EnvironmentName    = environmentName;
		this.CreatedAt        = createdAt;
		this.UpdatedAt        = updatedAt;
		
	}
	

	public String getEnvironmentName() {
		return EnvironmentName;
	}
	public void setEnvironmentName(String environmentName) {
		this.EnvironmentName = environmentName;
	}
	
	public String getEnvironmentCode() {
		return EnvironmentCode;
	}
	public void setEnvironmentCode(String environmentCode) {
		this.EnvironmentCode = environmentCode;
	}
	public String getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(String createdAt) {
		this.CreatedAt = createdAt;
	}

	public String getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.UpdatedAt = updatedAt;
	}
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	@Override
	public String toString() {
		return "EnvironmentAsset"
				+ " {EnvironmentName=" + EnvironmentName +
				", EnvironmentCode="+ EnvironmentCode 
				+ ", Message=" + Message + "}";
	}

}
