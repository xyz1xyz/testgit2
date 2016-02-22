package cn.itcast.instorage.entity;

/**
 * WmsFormDetail entity. @author MyEclipse Persistence Tools
 */

public class WmsFormDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String formId;
	private String materialId;
	private String materialName;
	private Integer quantity;
	private String instorageBinCode;
	private String outstorageBinCode;
	private String instorageBinName;
	private String outstorageBinName;

	// Constructors

	/** default constructor */
	public WmsFormDetail() {
	}

	/** full constructor */
	public WmsFormDetail(String formId, String materialId, String materialName,
			Integer quantity, String instorageBinCode, String outstorageBinCode, 
			String instorageBinName, String outstorageBinName) {
		this.formId = formId;
		this.materialId = materialId;
		this.materialName = materialName;
		this.quantity = quantity;
		this.instorageBinCode = instorageBinCode;
		this.outstorageBinCode = outstorageBinCode;
		this.instorageBinName = instorageBinName;
		this.outstorageBinName = outstorageBinName;
		
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return this.materialName;
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

	public String getInstorageBinCode() {
		return this.instorageBinCode;
	}

	public void setInstorageBinCode(String instorageBinCode) {
		this.instorageBinCode = instorageBinCode;
	}

	public String getOutstorageBinCode() {
		return this.outstorageBinCode;
	}

	public void setOutstorageBinCode(String outstorageBinCode) {
		this.outstorageBinCode = outstorageBinCode;
	}

	public String getInstorageBinName() {
		return instorageBinName;
	}

	public void setInstorageBinName(String instorageBinName) {
		this.instorageBinName = instorageBinName;
	}

	public String getOutstorageBinName() {
		return outstorageBinName;
	}

	public void setOutstorageBinName(String outstorageBinName) {
		this.outstorageBinName = outstorageBinName;
	}

}