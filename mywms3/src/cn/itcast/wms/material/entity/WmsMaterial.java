package cn.itcast.wms.material.entity;

/**
 * WmsMaterial entity. @author MyEclipse Persistence Tools
 */

public class WmsMaterial implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Double price;

	// Constructors

	/** default constructor */
	public WmsMaterial() {
	}

	/** full constructor */
	public WmsMaterial(String name, Double price) {
		this.name = name;
		this.price = price;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}