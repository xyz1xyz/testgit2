package cn.itcast.instorage.entity;

/**
 * WmsInventoryBin entity. @author MyEclipse Persistence Tools
 */

public class WmsInventoryBin implements java.io.Serializable {

	// Fields

	private String id;
	private String storageName;
	private String storageBinName;
	private String materialName;
	private Integer quantity;
	private String storeId;
	private String storeBinId;

	// Constructors

	/** default constructor */
	public WmsInventoryBin() {
	}

	/** full constructor */
	public WmsInventoryBin(String storageName, String storageBinName,
			String materialName, Integer quantity) {
		this.storageName = storageName;
		this.storageBinName = storageBinName;
		this.materialName = materialName;
		this.quantity = quantity;
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

	public String getStorageBinName() {
		return storageBinName;
	}

	public void setStorageBinName(String storageBinName) {
		this.storageBinName = storageBinName;
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

	public String getStoreBinId() {
		return storeBinId;
	}

	public void setStoreBinId(String storeBinId) {
		this.storeBinId = storeBinId;
	}

}