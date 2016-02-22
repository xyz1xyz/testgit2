package cn.itcast.instorage.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * WmsForm entity. @author MyEclipse Persistence Tools
 */

public class WmsForm implements java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private Timestamp createDate;
	private Timestamp optime;
	private String inStorage;
	private String outStorage;
	
	private String operator;
	//出库入库类型
	private static String FORM_TYPE_IN="0";
	private static String FORM_TYPE_OUT="1";
	public static Map<String, String> FORM_TYPE_MAP;
	static{
		 FORM_TYPE_MAP=new HashMap<String,String>();
		 FORM_TYPE_MAP.put(FORM_TYPE_IN, "入库");
		 FORM_TYPE_MAP.put(FORM_TYPE_OUT, "出库");
	}

	// Constructors

	/** default constructor */
	public WmsForm() {
	}

	/** full constructor */
	public WmsForm(String type, Timestamp createDate, Timestamp optime,
			String inStorage, String outStorage, String operator) {
		this.type = type;
		this.createDate = createDate;
		this.optime = optime;
		this.inStorage = inStorage;
		this.outStorage = outStorage;
		
		this.operator = operator;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getOptime() {
		return this.optime;
	}

	public void setOptime(Timestamp optime) {
		this.optime = optime;
	}

	public String getInStorage() {
		return this.inStorage;
	}

	public void setInStorage(String inStorage) {
		this.inStorage = inStorage;
	}

	public String getOutStorage() {
		return this.outStorage;
	}

	public void setOutStorage(String outStorage) {
		this.outStorage = outStorage;
	}

	

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}