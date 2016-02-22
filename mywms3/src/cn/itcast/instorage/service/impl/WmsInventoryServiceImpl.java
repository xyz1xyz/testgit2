package cn.itcast.instorage.service.impl;





import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.instorage.dao.WmsInventoryDao;
import cn.itcast.instorage.entity.WmsInventory;
import cn.itcast.instorage.service.WmsInventoryService;
@Service
public class WmsInventoryServiceImpl extends BaseServiceImpl<WmsInventory> implements
		WmsInventoryService {

	private WmsInventoryDao wmsInventoryDao;
	@Resource
	public void setWmsInventoryDao(WmsInventoryDao wmsInventoryDao) {
		super.setBaseDao(wmsInventoryDao);
		this.wmsInventoryDao = wmsInventoryDao;
	}

}
