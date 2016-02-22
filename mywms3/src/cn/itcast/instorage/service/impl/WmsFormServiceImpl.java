package cn.itcast.instorage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.instorage.dao.WmsFormDao;
import cn.itcast.instorage.entity.WmsForm;
import cn.itcast.instorage.service.WmsFormService;

@Service
public class WmsFormServiceImpl extends BaseServiceImpl<WmsForm> implements WmsFormService {

	private WmsFormDao wmsFormDao;
	@Resource
	public void setWmsFormDao(WmsFormDao wmsFormDao) {
		super.setBaseDao(wmsFormDao);
		this.wmsFormDao = wmsFormDao;
	}
	
}
