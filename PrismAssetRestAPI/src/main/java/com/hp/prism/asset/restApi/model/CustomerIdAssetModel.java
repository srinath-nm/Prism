package com.hp.prism.asset.restApi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerIdAssetModel {
	
	private Long Customer_ID;
	private String Customer;
	private String CreatedAt;
	private String UpdatedAt;

	public CustomerIdAssetModel() {			
		
	}
	
	public CustomerIdAssetModel(Long customer_Id, String customer, String createdAt, String updatedAt) {
		
		this.Customer_ID = customer_Id;
		this.Customer    = customer;
		this.CreatedAt   = createdAt;
		this.UpdatedAt   = updatedAt;
		
	}
	
	public long getCustomer_Id() {
		return Customer_ID;
	}

	public void setCustomer_Id(Long customer_Id) {
		this.Customer_ID = customer_Id;
	}
	
	
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		this.Customer = customer;
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
