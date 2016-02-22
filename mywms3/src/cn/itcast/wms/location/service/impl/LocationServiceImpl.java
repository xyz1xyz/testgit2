package cn.itcast.wms.location.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.location.dao.LocationDao;
import cn.itcast.wms.location.entity.WmsLocation;
import cn.itcast.wms.location.service.LocationService;

@Service
public class LocationServiceImpl extends BaseServiceImpl<WmsLocation> implements
		LocationService {

	private LocationDao locationDao;
	@Resource
	public void setLocationDao(LocationDao locationDao) {
		super.setBaseDao(locationDao);
		this.locationDao = locationDao;
	}
}
