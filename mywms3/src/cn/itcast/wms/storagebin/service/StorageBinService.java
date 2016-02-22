package cn.itcast.wms.storagebin.service;

import java.util.List;

import cn.itcast.base.service.BaseService;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;

public interface StorageBinService extends BaseService<WmsStorageBin> {


	/**
	 * 根据仓库名查询仓位
	 * @param storeName 仓库名
	 */
	public List<String> findBinByStorageName(String storeName);
	/**
	 * 根据仓库名和仓位名查找ID
	 * @param name 仓位名
	 * @param storeName 仓库名
	 * 
	 */
	public List<WmsStorageBin> findObjects(String name,String storeName);
	
}
