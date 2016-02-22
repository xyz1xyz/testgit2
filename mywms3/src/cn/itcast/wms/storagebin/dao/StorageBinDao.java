package cn.itcast.wms.storagebin.dao;

import java.util.List;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;

public interface StorageBinDao extends BaseDao<WmsStorageBin> {

	/**
	 * 根据仓库名查询仓位
	 * @param storeName 仓库名
	 */
	public List<String> findBinByStorageName(String storeName);
}
