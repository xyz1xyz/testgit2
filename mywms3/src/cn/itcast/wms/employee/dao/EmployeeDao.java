package cn.itcast.wms.employee.dao;

import java.util.List;

import cn.itcast.base.dao.BaseDao;
import cn.itcast.wms.employee.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee> {

	//根据用户的帐号和密码查询用户列表
	public List<Employee> findUsersByAcccountAndPass(String name, String password);
}
