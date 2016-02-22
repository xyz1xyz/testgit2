package cn.itcast.wms.storage.dao;

import java.util.List;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.wms.storage.entity.WmsStorage;

public interface StorageDao extends BaseDao<WmsStorage> {

	/**
	 * 根据仓库名查找地域
	 * @param address 地域
	 */
	public List<String> findStorageByAddess(String address);
	
}
