package cn.itcast.instorage.dao;

import java.util.List;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.instorage.entity.WmsInventoryBin;

public interface WmsInventoryBinDao extends BaseDao<WmsInventoryBin> {

	/**
	 * 通过仓库和仓位查询库存产品列表
	 * @param storename 仓库名
	 * @param binName 仓位名
	 */
	public List<String> findMaterials(String storename,String binName);
	
	
	
}
