package cn.itcast.wms.location.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * WmsLocation entity. @author MyEclipse Persistence Tools
 */

public class WmsLocation implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String catogery;


	// Constructors

	/** default constructor */
	public WmsLocation() {
		
		
	}

	//所属分类
	private static String CATOGERY_SAME="0";
	private static String CATOGERY_NOSAME="1";
	public static Map<String,String> CATOGERY_MAP;
	
	static{
		CATOGERY_MAP=new HashMap<String,String>();
		CATOGERY_MAP.put(CATOGERY_SAME, "同一地域同一sn");
		CATOGERY_MAP.put(CATOGERY_NOSAME, "同一地域不同sn");
	}
	
	

	// Property accessors

	public WmsLocation(String id, String name, String catogery) {
		super();
		this.id = id;
		this.name = name;
		this.catogery = catogery;
	
	}



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



	public String getCatogery() {
		return catogery;
	}



	public void setCatogery(String catogery) {
		this.catogery = catogery;
	}






	




}