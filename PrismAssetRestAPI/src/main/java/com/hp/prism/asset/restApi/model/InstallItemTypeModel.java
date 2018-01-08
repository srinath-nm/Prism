package com.hp.prism.asset.restApi.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InstallItemTypeModel {

	
	private String ItemType;
	private String CreatedAt;
	private String UpdatedAt;

	public InstallItemTypeModel() {			
		
	}
	
	public InstallItemTypeModel(String itemType, String createdAt, String updatedAt) {
		
		this.ItemType    = itemType;
		this.CreatedAt   = createdAt;
		this.UpdatedAt   = updatedAt;
		
	}
	

	public String getItemType() {
		return ItemType;
	}
	public void setItemType(String itemType) {
		this.ItemType = itemType;
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
