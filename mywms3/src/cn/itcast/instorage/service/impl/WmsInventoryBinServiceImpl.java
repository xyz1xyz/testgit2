package cn.itcast.instorage.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.dao.WmsInventoryBinDao;
import cn.itcast.instorage.entity.WmsInventoryBin;
import cn.itcast.instorage.service.WmsInventoryBinService;
@Service
public class WmsInventoryBinServiceImpl extends BaseServiceImpl<WmsInventoryBin> implements
		WmsInventoryBinService {

	private WmsInventoryBinDao wmsInventoryBinDao;
	@Resource
	public void setWmsInventoryBinDao(WmsInventoryBinDao wmsInventoryBinDao) {
		super.setBaseDao(wmsInventoryBinDao);
		this.wmsInventoryBinDao = wmsInventoryBinDao;
	}
	@Override
	public List<String> findMaterials(String storename, String binName) {
		// TODO Auto-generated method stub
		return wmsInventoryBinDao.findMaterials(storename, binName);
	}
}
