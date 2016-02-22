package cn.itcast.instorage.service.impl;




import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.instorage.dao.WmsFormDetailDao;
import cn.itcast.instorage.entity.WmsFormDetail;
import cn.itcast.instorage.service.WmsFormDetailService;

@Service
public class WmsFormDetailServiceImpl extends BaseServiceImpl<WmsFormDetail> implements
		WmsFormDetailService {

	private WmsFormDetailDao wmsFormDetailDao;
	@Resource
	public void setWmsFormDetailDao(WmsFormDetailDao wmsFormDetailDao) {
		super.setBaseDao(wmsFormDetailDao);
		this.wmsFormDetailDao = wmsFormDetailDao;
	}
	
	

}
