package cn.itcast.wms.employee.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import cn.itcast.base.dao.impl.BaseDaoImpl;
import cn.itcast.base.page.PageResult;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.employee.dao.EmployeeDao;
import cn.itcast.wms.employee.entity.Employee;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public List<Employee> findUsersByAcccountAndPass(String name,
			String password) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery("FROM Employee WHERE name=? AND password=?");
		query.setParameter(0, name);
		query.setParameter(1, password);
		return query.list();
	}



}
