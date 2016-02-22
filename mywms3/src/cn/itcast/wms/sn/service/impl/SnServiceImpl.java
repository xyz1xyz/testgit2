package cn.itcast.wms.sn.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.sn.dao.SnDao;
import cn.itcast.wms.sn.entity.WmsSn;
import cn.itcast.wms.sn.service.SnService;
@Service
public class SnServiceImpl extends BaseServiceImpl<WmsSn> implements SnService {

	private SnDao snDao;
	@Resource
	public void setSnDao(SnDao snDao) {
		super.setBaseDao(snDao);
		this.snDao = snDao;
	}
}
