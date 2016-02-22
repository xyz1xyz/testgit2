package cn.itcast.wms.storagebin.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import cn.itcast.base.dao.impl.BaseDaoImpl;
import cn.itcast.base.page.PageResult;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.storagebin.dao.StorageBinDao;
import cn.itcast.wms.storagebin.entity.WmsStorageBin;

public class StorageBinDaoImpl extends BaseDaoImpl<WmsStorageBin> implements StorageBinDao {

	@Override
	public List<String> findBinByStorageName(String storeName) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("select ws.name from  WmsStorageBin ws where storeName=?");
		query.setParameter(0, storeName);
		return query.list();
	}
	

	
}
