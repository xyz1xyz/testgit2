package cn.itcast.wms.customer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.base.service.impl.BaseServiceImpl;
import cn.itcast.wms.customer.dao.CustomerDao;
import cn.itcast.wms.customer.entity.WmsCustomer;
import cn.itcast.wms.customer.service.CustomerService;




@Service
public class CustomerServiceImpl extends BaseServiceImpl<WmsCustomer> implements CustomerService{

	
	private CustomerDao customerDao;
	@Resource
	public void setCustomerDao(CustomerDao customerDao) {
		super.setBaseDao(customerDao);
		this.customerDao = customerDao;
	}
}
