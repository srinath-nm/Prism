package com.hp.prism.asset.restApi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CloudProviderAssetModel {
	

	private Long   ID;
	private String CloudProvider;
	private String CloudEnvironment;
	private String CreatedAt;
	private String UpdatedAt;

	public CloudProviderAssetModel() {			
		
	}
	
	public CloudProviderAssetModel(Long id,String cloudProvider, String cloudEnvironment,String createdAt, String updatedAt) {
		
		this.ID = id;
		this.CloudProvider    = cloudProvider;
		this.CloudEnvironment    = cloudEnvironment;
		this.CreatedAt        = createdAt;
		this.UpdatedAt        = updatedAt;
		
	}
	
	public Long getId() {
		return ID;
	}
	public void setId(Long id) {
		this.ID = id;
	}

	public String getCloudProvider() {
		return CloudProvider;
	}
	public void setCloudProvider(String cloudProvider) {
		this.CloudProvider = cloudProvider;
	}
	
	public String getCloudEnvironment() {
		return CloudEnvironment;
	}
	public void setCloudEnvironment(String cloudEnvironment) {
		this.CloudEnvironment = cloudEnvironment;
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
