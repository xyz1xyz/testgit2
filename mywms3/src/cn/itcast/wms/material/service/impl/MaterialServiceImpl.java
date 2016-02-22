package cn.itcast.wms.material.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.material.dao.MaterialDao;
import cn.itcast.wms.material.entity.WmsMaterial;
import cn.itcast.wms.material.service.MaterialService;
@Service
public class MaterialServiceImpl extends BaseServiceImpl<WmsMaterial> implements
		MaterialService {

	private MaterialDao materialDao;
	@Resource
	public void setMaterialDao(MaterialDao materialDao) {
		super.setBaseDao(materialDao);
		this.materialDao = materialDao;
	}
	
	

}
