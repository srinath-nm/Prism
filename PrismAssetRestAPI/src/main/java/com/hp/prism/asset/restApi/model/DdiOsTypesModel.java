package com.hp.prism.asset.restApi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DdiOsTypesModel {


	private String OS_Type;
	private String CreatedAt;
	private String UpdatedAt;

	public DdiOsTypesModel() {			
		
	}
	
	public DdiOsTypesModel(String os_Type, String createdAt, String updatedAt) {
		
		this.OS_Type = os_Type;
		this.CreatedAt   = createdAt;
		this.UpdatedAt   = updatedAt;
		
	}
	
	
	public String getOsType() {
		return OS_Type;
	}
	public void setOsType(String os_Type) {
		this.OS_Type = os_Type;
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
