<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
     <script type="text/javascript">
    var vResult = false;
    	//校验帐号唯一
    	function doVerify(){
    		//1、获取帐号
    		var name = $("#name").val();
    		if(name != ""){
    			//2、校验 
    			$.ajax({
    				url:"${basePath}wms/employee_verify.action",
    				data: {"employee.name": name},
    				type: "post",
    				async: false,//非异步
    				success: function(msg){
    				
    					if("true" != msg){
    					
    						alert("该用户名已经存在！");
    						//定焦
    						$("#name").focus();
    						vResult = false;
    					} else {
    						vResult = true;
    					}
    				}
    			});
    		}
    	}
    	
    	//提交表单
    	function doSubmit(){
    		var name = $("#name");
    		if(name.val() == ""){
    			alert("用户名不能为空！");
    			name.focus();
    			return false;
    		}
    		var password = $("#password");
    		if(password.val() == ""){
    			alert("密码不能为空！");
    			password.focus();
    			return false;
    		}
    		var email = $("#email");
    		if(email.val() == ""){
    			alert("邮箱不能为空！");
    			email.focus();
    			return false;
    		}
    		var mobile = $("#mobile");
    		if(mobile.val() == ""){
    			alert("电话号码不能为空！");
    			mobile.focus();
    			return false;
    		}
    		
    		doVerify();
    		
    		if(vResult){
	    		//提交表单
	    		document.forms[0].submit();
    		}
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }wms/employee_edit.action" method="post" >
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>员工管理</strong>&nbsp;-&nbsp;编辑用户</div></div>
    <div class="tableH2">编辑员工</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="employee.dept" list="#{'部门A':'部门A','部门B':'部门B' }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">员工名：</td>
    
            <td><s:textfield id="name" name="employee.name"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield id="password" name="employee.password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="employee.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="employee.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="employee.mobile"/></td>
        </tr>
    </table>
    <s:hidden name="employee.id"/>
    <s:hidden name="pageNo"/>
    <div class="tc mt20">
         <input type="button" class="btnB2" value="保存" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>