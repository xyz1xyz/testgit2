package cn.itcast.base.service.impl;



import java.io.Serializable;
import java.util.List;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.BaseService;
import cn.itcast.base.util.QueryHelper;


public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		return baseDao.findObjects(hql, parameters);
	}

	

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		return baseDao.getPageResult(queryHelper, pageNo, pageSize);
	}

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		// TODO Auto-generated method stub
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public List<T> findObjectsTwo(QueryHelper queryHelper) {
		// TODO Auto-generated method stub
		return baseDao.findObjectsTwo(queryHelper);
	}
	

}
