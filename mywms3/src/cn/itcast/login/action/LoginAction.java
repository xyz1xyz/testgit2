package cn.itcast.login.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import cn.itcast.base.action.BaseAction;
import cn.itcast.wms.employee.entity.Employee;
import cn.itcast.wms.employee.service.EmployeeService;

public class LoginAction extends BaseAction{

	@Resource
	private EmployeeService employeeService;
	private Employee employee;
	
	
	//跳转到登录页面
	public String toLoginUI()
	{
		return "loginUI";
	}
	
	//登录
	public String login() throws IOException
	{
		//防止表单重复提交
		if(ServletActionContext.getRequest().getSession().getAttribute("USER")!=null){
			ServletActionContext.getRequest().setAttribute("msg", "请不要重复登录！");
			return toLoginUI();
		}
		if(employee!=null)
		{
			
			
			  List<Employee> employees=employeeService.findUsersByAcccountAndPass(employee.getName(), employee.getPassword());
			  if(employees!=null&&employees.size()>0)//登录成功
			    {
				   Employee employee=employees.get(0);
				   ServletActionContext.getRequest().getSession().setAttribute("USER", employee);
				   //return "sussess";//跳转到首页
				   HttpServletResponse reponse=ServletActionContext.getResponse();
				   reponse.sendRedirect("home_manager.action");
				
			    }
			  else
			    {
					ServletActionContext.getRequest().setAttribute("msg", "帐号或密码不正确！");
			    }
			
			
		}
		
	    
		//跳转到登录页面
		return toLoginUI();
		
	}
	public String logout()
	{
		//清除session中保存的用户信息
		ServletActionContext.getRequest().getSession().removeAttribute("USER");
		return toLoginUI();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
