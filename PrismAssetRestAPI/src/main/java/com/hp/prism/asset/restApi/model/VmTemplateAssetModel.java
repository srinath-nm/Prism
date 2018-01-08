package com.hp.prism.asset.restApi.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VmTemplateAssetModel {

	
	private String OSType;
	private String TemplateName;
	private String CreatedAt;
	private String UpdatedAt;
	private Integer OSDiskSize_GB;

	public VmTemplateAssetModel() {			
		
	}
	
	public VmTemplateAssetModel(String osType, String templateName, Integer osDiskSize_GB , String createdAt, String updatedAt) {
		
		this.OSType = osType;
		this.TemplateName = templateName;
		this.CreatedAt   = createdAt;
		this.UpdatedAt   = updatedAt;
		this.OSDiskSize_GB = osDiskSize_GB;
		
	}
	
	
	public String getOsType() {
		return OSType;
	}
	public void setOsType(String osType) {
		this.OSType = osType;
	}
	
	public String getTemplateName() {
		return TemplateName;
	}
	public void setTemplateName(String templateName) {
		this.TemplateName = templateName;
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
	
	public Integer getOSDiskSize_GB() {
		return OSDiskSize_GB;
	}

	public void setOSDiskSize_GB(Integer osDiskSize_GB) {
		this.OSDiskSize_GB = osDiskSize_GB;
	}
	
}
