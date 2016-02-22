package cn.itcast.instorage.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import cn.itcast.base.dao.impl.BaseDaoImpl;
import cn.itcast.base.page.PageResult;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.instorage.dao.WmsInventoryBinDao;
import cn.itcast.instorage.entity.WmsInventoryBin;

public class WmsInventoryBinDaoImpl extends BaseDaoImpl<WmsInventoryBin> implements
		WmsInventoryBinDao {

	@Override
	public List<String> findMaterials(String storename, String binName) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("select w.materialName from WmsInventoryBin w where storageName=? and storageBinName=?");
		query.setParameter(0, storename);
		query.setParameter(1, binName);
		return query.list();
		
	}

	

}
