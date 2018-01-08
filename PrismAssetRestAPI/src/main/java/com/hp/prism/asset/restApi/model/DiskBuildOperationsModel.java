package com.hp.prism.asset.restApi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiskBuildOperationsModel {

	private String Operation;
	private String CreatedAt;
	private String UpdatedAt;

	public DiskBuildOperationsModel() {			
		
	}
	
	public DiskBuildOperationsModel(String operation, String createdAt, String updatedAt) {
		
		this.Operation = operation;
		this.CreatedAt   = createdAt;
		this.UpdatedAt   = updatedAt;
		
	}
	
	
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		this.Operation = operation;
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
