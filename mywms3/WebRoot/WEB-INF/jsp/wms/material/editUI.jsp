<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>货物管理</title>
     <script type="text/javascript">
    var vResult = false;
    	
    	function doVerify(){
    	
    		var price = $("#price").val();
    		var re=/^[1-9]+[0-9]*]*$/;
    		var result=re.test(price);
    		if(!result)
    		{
    		   alert("请输入正整数");
    		}
    		else{
    		vResult=true;
    		}
    		
    	}
    	//提交表单
    	function doSubmit(){
    	   doVerify();
    	
    		if(vResult){
	    		//提交表单
	    		document.forms[0].submit();
    		}
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }wms/material_edit.action" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>货物管理</strong>&nbsp;-&nbsp;修改货物</div></div>
    <div class="tableH2">修改货物</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        
            <tr>
            <td class="tdBg" width="200px"/> 货物名：</td>
            <td><s:textfield id="name" name="material.name" readonly="true"></s:textfield></td>
            </tr>
             <td class="tdBg" width="200px"/> 价格：</td>
            <td><s:textfield id="price" name="material.price" onchange="doVerify()"></s:textfield></td>
            <tr>
         
          </tr>
    </table>
    <s:hidden name="material.id"/>
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