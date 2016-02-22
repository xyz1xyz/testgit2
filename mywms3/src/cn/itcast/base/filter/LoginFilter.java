package cn.itcast.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri = request.getRequestURI();
		//判断当前请求地址是否是登录的请求地址
				if(!uri.contains("login_"))
				{
					if(request.getSession().getAttribute("USER")!=null)
					{
						chain.doFilter(request, response);
					}
					else{
						//跳转到登录页面
						response.sendRedirect(request.getContextPath() + "/login_toLoginUI.action");
					}
				}
				
				else
				{
					//登录请求
					chain.doFilter(request, response);
				}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
