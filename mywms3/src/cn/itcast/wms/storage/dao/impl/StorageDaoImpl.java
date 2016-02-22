package cn.itcast.wms.storage.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import cn.itcast.base.dao.impl.BaseDaoImpl;
import cn.itcast.base.page.PageResult;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.storage.dao.StorageDao;
import cn.itcast.wms.storage.entity.WmsStorage;

public class StorageDaoImpl extends BaseDaoImpl<WmsStorage> implements StorageDao {

	@Override
	public List<String> findStorageByAddess(String address) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("select w.name from WmsStorage w where address=?");
		query.setParameter(0, address);
		return query.list();
		
		
		
	}

	
}
