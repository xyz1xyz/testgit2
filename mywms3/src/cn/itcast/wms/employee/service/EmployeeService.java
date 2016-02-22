package cn.itcast.wms.employee.service;

import java.util.List;

import cn.itcast.base.service.BaseService;
import cn.itcast.wms.employee.entity.Employee;

public interface EmployeeService extends BaseService<Employee> {


	//根据用户的帐号和密码查询用户列表
	public List<Employee> findUsersByAcccountAndPass(String name, String password);
}
