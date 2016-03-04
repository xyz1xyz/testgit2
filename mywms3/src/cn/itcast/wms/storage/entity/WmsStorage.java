package cn.itcast.wms.storage.entity;

/**
 * WmsStorage entity. @author MyEclipse Persistence Tools
 */

public class WmsStorage implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String address;
	private String addressId;

	// Constructors

	/** default constructor */
	public WmsStorage() {
	}

	/** full constructor */
	public WmsStorage(String name, String address,String addressId) {
		this.name = name;
		this.address = address;
		this.addressId=addressId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	

}