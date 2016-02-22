package cn.itcast.wms.storage.service;

import java.util.List;

import cn.itcast.base.service.BaseService;
import cn.itcast.wms.storage.entity.WmsStorage;

public interface StorageService extends BaseService<WmsStorage> {

	/**
	 * 根据仓库名查找地域
	 * @param address 地域
	 */
	public List<String> findStorageByAddess(String address);
	
}
