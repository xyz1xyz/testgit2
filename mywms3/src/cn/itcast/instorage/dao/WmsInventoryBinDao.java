package cn.itcast.instorage.dao;

import java.util.List;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.instorage.entity.WmsInventoryBin;

public interface WmsInventoryBinDao extends BaseDao<WmsInventoryBin> {

	/**
	 * ͨ���ֿ�Ͳ�λ��ѯ����Ʒ�б�
	 * @param storename �ֿ���
	 * @param binName ��λ��
	 */
	public List<String> findMaterials(String storename,String binName);
	
	
	
}
