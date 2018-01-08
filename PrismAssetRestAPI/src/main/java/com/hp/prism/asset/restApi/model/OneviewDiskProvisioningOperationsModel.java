package com.hp.prism.asset.restApi.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OneviewDiskProvisioningOperationsModel {
	
	private String ProvisioningType;
	private String CreatedAt;
	private String UpdatedAt;

	public OneviewDiskProvisioningOperationsModel() {			
		
	}
	
	public OneviewDiskProvisioningOperationsModel(String provisioningType, String createdAt, String updatedAt) {
		
		this.ProvisioningType = provisioningType;
		this.CreatedAt   = createdAt;
		this.UpdatedAt   = updatedAt;
		
	}
	
	
	public String getProvisioningType() {
		return ProvisioningType;
	}
	public void setProvisioningType(String provisioningType) {
		this.ProvisioningType = provisioningType;
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
