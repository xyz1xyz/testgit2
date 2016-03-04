package cn.itcast.wms.customer.entity;

public class WmsCustomer {

	private String id;
	private String name;
	
	public WmsCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WmsCustomer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
