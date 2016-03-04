package cn.itcast.wms.customer.action;

import javax.annotation.Resource;
import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.customer.entity.WmsCustomer;
import cn.itcast.wms.customer.service.CustomerService;


public class CustomerAction extends BaseAction{

	@Resource
	private CustomerService customerService;
     
	private WmsCustomer customer;
	
	//列表页面
		public String listUI() throws Exception {
			QueryHelper queryHelper = new QueryHelper(WmsCustomer.class, "wc");
			try {
				/*if (sn != null) {
					if (StringUtils.isNotBlank(sn.getMaterialName())) {

						queryHelper.addCondition("e.materialName like ?",
								"%" + sn.getMaterialName() + "%");
					}
					if (StringUtils.isNotBlank(sn.getAddressName())) {

						queryHelper.addCondition("e.addressName like ?",
								"%" + sn.getAddressName() + "%");
					}
					
					if (StringUtils.isNotBlank(sn.getSn())) {

						queryHelper.addCondition("e.sn like ?",
								"%" + sn.getSn() + "%");
					}

				}*/
				pageResult = customerService.getPageResult(queryHelper, getPageNo(),
						getPageSize());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

			return "listUI";

		}

		// 新增页面
		public String addUI() {
			
			return "addUI";
		}

		// 保存新增
		public String add() {
			try {
				if (customer != null) {

					customerService.save(customer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}

		// 编辑页面
		public String editUI() {
			
			
			if (customer != null && customer.getId() != null) {

				customer = customerService.findObjectById(customer.getId());

			}
			return "editUI";
		}

		//保存编辑
		public String edit() {

			try {
				if (customer != null) {

					customerService.update(customer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}

		// 删除
		public String delete() {
			if (customer != null && customer.getId() != null) {
				customerService.delete(customer.getId());
			}
			return "list";
		}

		// 批量删除
		public String deleteSelected() {
			if (selectedRow != null) {
				for (String id : selectedRow) {
					customerService.delete(id);
				}
			}
			return "list";
		}

		public WmsCustomer getCustomer() {
			return customer;
		}

		public void setCustomer(WmsCustomer customer) {
			this.customer = customer;
		}
	
}
