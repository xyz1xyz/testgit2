package cn.itcast.base.util;

import java.util.UUID;

public class SnUtil {

	public static final String PRODUCT_SN_PREFIX ="SN_";//货物编号前缀
	//public static final String PRODUCT_SN_MIDDLE="XT_";
	//public static final String PRODUCT_SN_MIDDLE2="BXT_";
	
	/**
	 * 产生货物二维码
	 * 
	 * @return 二维码
	 */
	public synchronized static  String buildProductSn()
	{
		String uuid=UUID.randomUUID().toString();
		return PRODUCT_SN_PREFIX+(uuid.substring(0, 8) + uuid.substring(9, 13)).toUpperCase();
		
	}
	
	
	
	
	
}
