package cn.itcast.wms.storagebin.entity;

/**
 * WmsStorageBin entity. @author MyEclipse Persistence Tools
 */

public class WmsStorageBin implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String storeName;
	private String storeId;

	// Constructors

	/** default constructor */
	public WmsStorageBin() {
	}

	/** full constructor */
	public WmsStorageBin(String name, String storeName,String storeId) {
		this.name = name;
		this.storeName = storeName;
		this.storeId=storeId;
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

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}