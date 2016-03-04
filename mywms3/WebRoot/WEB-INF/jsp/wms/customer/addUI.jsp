<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title> 客户管理</title>
  
     <script type="text/javascript">
    
    	//提交表单
    	function doSubmit(){
    	// doVerify();
    	//doCheck();
    		
	    		//提交表单
	    		document.forms[0].submit();
    		}
    	
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }wms/customer_add.action" method="post" >
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong> 客户管理</strong>&nbsp;-&nbsp;新增客户</div></div>
    <div class="tableH2">新增客户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
       
         <tr>
            <td class="tdBg" width="200px">客户名：</td>
            <td><s:textfield id="name" name="customer.name"/> </td>
         </tr>
         <tr>
       
       </tr>
        
    </table>
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