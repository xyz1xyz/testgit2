package cn.itcast.wms.employee.action;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.base.action.BaseAction;
import cn.itcast.base.util.QueryHelper;
import cn.itcast.wms.employee.entity.Employee;
import cn.itcast.wms.employee.service.EmployeeService;
import cn.itcast.wms.location.entity.WmsLocation;

public class EmployeeAction extends BaseAction {

	@Resource
	private EmployeeService employeeService;
	private Employee employee;
	
	// 列表页面
		public String listUI() throws Exception {
			QueryHelper queryHelper = new QueryHelper(Employee.class, "e");
			try {
				if (employee != null) {
					if (StringUtils.isNotBlank(employee.getName())) {
						
						queryHelper.addCondition("e.name like ?", "%" + employee.getName() + "%");
					}

				}
				pageResult = employeeService.getPageResult(queryHelper, getPageNo(), getPageSize());
				
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

			return "listUI";

		}

		//跳转到新增页面
		public String addUI(){
			
			return "addUI";
		}
		//保存新增
		public String add(){
			try {
				if(employee != null){
				
					employeeService.save(employee);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//跳转到编辑页面
		public String editUI(){
			
			if (employee != null && employee.getId() != null) {
			
				employee = employeeService.findObjectById(employee.getId());
				
			}
			return "editUI";
		}
		//保存编辑
		public String edit(){
			try {
				if(employee != null){
					

					employeeService.update(employee);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "list";
		}
		//删除
		public String delete(){
			if(employee != null && employee.getId() != null){
				employeeService.delete(employee.getId());
			}
			return "list";
		}
		//批量删除
		public String deleteSelected(){
			if(selectedRow != null){
				for(String id: selectedRow){
					employeeService.delete(id);
				}
			}
			return "list";
		}
		
		//唯一验证
				public void verify(){
					try {
						//接收数据和查询
						if(employee != null && StringUtils.isNotBlank(employee.getName())){
							
							QueryHelper query=new QueryHelper(Employee.class,"wl");
							query.addCondition("wl.name=?", employee.getName());
							List<Employee> list=employeeService.findObjects(query);
							
							String strResult = "true";
							if(list != null && list.size() > 0){
								//说明数据已经存在
								strResult = "false";
							}
							
							//输出
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setContentType("text/html");
							ServletOutputStream outputStream = response.getOutputStream();
							outputStream.write(strResult.getBytes());
							outputStream.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
