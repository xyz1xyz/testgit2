package cn.itcast.wms.sn.entity;

/**
 * WmsSn entity. @author MyEclipse Persistence Tools
 */

public class WmsSn implements java.io.Serializable {

	// Fields

	private String id;
	private String materialName;
	private String addressName;
	private String sn;
	


	// Constructors

	/** default constructor */
	public WmsSn() {
	}

	/** full constructor */
	public WmsSn(String materialName, String addressName, String sn) {
		this.materialName = materialName;
		this.addressName = addressName;
		this.sn = sn;
		
		
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getAddressName() {
		return this.addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	

}