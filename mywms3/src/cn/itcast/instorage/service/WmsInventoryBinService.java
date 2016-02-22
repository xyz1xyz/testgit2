package cn.itcast.instorage.service;

import java.util.List;

import cn.itcast.base.service.BaseService;
import cn.itcast.instorage.entity.WmsInventoryBin;

public interface WmsInventoryBinService extends BaseService<WmsInventoryBin> {

	/**
	 * 根据仓库名和仓位名查找货物
	 * @param storename 仓库名
	 * @param binName 仓位名
	 */
	public List<String> findMaterials(String storename,String binName);

}
