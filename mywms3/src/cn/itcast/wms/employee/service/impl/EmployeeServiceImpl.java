package cn.itcast.wms.employee.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.page.PageResult;
import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.employee.dao.EmployeeDao;
import cn.itcast.wms.employee.entity.Employee;
import cn.itcast.wms.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements
		EmployeeService {

	private EmployeeDao employeeDao;
	@Resource
	public void setEmployeeDao(EmployeeDao employeeDao) {
		super.setBaseDao(employeeDao);
		this.employeeDao = employeeDao;
	}
	@Override
	public List<Employee> findUsersByAcccountAndPass(String name,
			String password) {
		// TODO Auto-generated method stub
		return employeeDao.findUsersByAcccountAndPass(name, password);
	}

}
