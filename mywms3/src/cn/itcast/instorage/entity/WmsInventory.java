package cn.itcast.instorage.entity;

/**
 * WmsInventory entity. @author MyEclipse Persistence Tools
 */

public class WmsInventory implements java.io.Serializable {

	// Fields

	private String id;
	private String storageName;
	private String materialName;
	private Integer quantity;
	private String storeId;

	// Constructors

	/** default constructor */
	public WmsInventory() {
	}

	/** full constructor */
	public WmsInventory(String storageName, String materialName, Integer quantity,String storeId) {
		this.storageName = storageName;
		this.materialName = materialName;
		this.quantity = quantity;
		this.storeId=storeId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}