package cn.itcast.wms.storagebin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.storagebin.dao.StorageBinDao;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;
import cn.itcast.wms.storagebin.service.StorageBinService;

@Service
public class StorageBinServiceImpl extends BaseServiceImpl<WmsStorageBin> implements
		StorageBinService {

	private StorageBinDao storageBinDao;
	@Resource
	public void setStorageBinDao(StorageBinDao storageBinDao) {
		super.setBaseDao(storageBinDao);
		this.storageBinDao = storageBinDao;
	}
	@Override
	public List<String> findBinByStorageName(String storeName) {
		// TODO Auto-generated method stub
		return storageBinDao.findBinByStorageName(storeName);
	}
	@Override
	public List<WmsStorageBin> findObjects(String name, String storeName) {
		// TODO Auto-generated method stub
		String hql="from WmsStorageBin where name=? and storeName=?";
		List<Object> parameters=new ArrayList<Object>();
		parameters.add(name);
		parameters.add(storeName);
		return storageBinDao.findObjects(hql, parameters);
	}
	
}
