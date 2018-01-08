package com.hp.prism.asset.restApi.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InstallParameterTypeModel {

	
	private String ParameterType;
	private String CreatedAt;
	private String UpdatedAt;

	public InstallParameterTypeModel() {			
		
	}
	
	public InstallParameterTypeModel(String parameterType, String createdAt, String updatedAt) {
		
		this.ParameterType    = parameterType;
		this.CreatedAt        = createdAt;
		this.UpdatedAt        = updatedAt;
		
	}
	

	public String getParameterType() {
		return ParameterType;
	}
	public void setParameterType(String parameterType) {
		this.ParameterType = parameterType;
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
	

}
