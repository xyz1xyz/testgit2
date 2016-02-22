<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>仓库管理系统</title>
  </head>
  
  <frameset rows="25%,*">
  	<frame src="${pageContext.request.contextPath }/home_head.action" name="head">
  
  	<frameset cols="15%,*">
	  	<frame src="${pageContext.request.contextPath }/home_left.action" name="left">
	  	<frame src="${pageContext.request.contextPath }/home_body.action" name="body">
  	</frameset>
  
  </frameset>
