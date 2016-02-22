<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
    <title>后台页头</title>
  </head>
  
  <body style="text-align: center;">
    	<h1>仓库管理系统</h1>
    	<table>
    	
    		<tr>
					    <td align="center"></td>
                        <td align="left"></td>
                        <td align="left"><a><b></b><font color="red">欢迎您，${sessionScope.USER.name }</font></a></td>
          
						<td width="17" align="center"><img src="${basePath}images/nsfw/exit.png"width="14" height="14"/></td>
						<td align="left" valign="middle"><a href="${basePath}/login_logout.action" target="_top">退出</a></td>
					</tr>
		</table>
  </body>
  
</html>
