package cn.itcast.wms.storage.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.storage.dao.StorageDao;
import cn.itcast.wms.storage.entity.WmsStorage;
import cn.itcast.wms.storage.service.StorageService;

@Service
public class StorageServiceImpl extends BaseServiceImpl<WmsStorage> implements
		StorageService {

	private StorageDao storageDao;
	@Resource
	public void setStorageDao(StorageDao storageDao) {
		super.setBaseDao(storageDao);
		this.storageDao = storageDao;
	}
	@Override
	public List<String> findStorageByAddess(String address) {
		// TODO Auto-generated method stub
		return storageDao.findStorageByAddess(address);
	}
	
}
