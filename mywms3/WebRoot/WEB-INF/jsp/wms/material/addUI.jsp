<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title> 货物管理</title>
  
     <script type="text/javascript">
    var vResult = false;
     var Result=false;
    	//校验帐号唯一
    	function doVerify(){
    		//1、获取帐号
    		var name = $("#name").val();
    		if(name != ""){
    			//2、校验 
    			$.ajax({
    				url:"${basePath}wms/material_verify.action",
    				data: {"material.name": name},
    				type: "post",
    				async: false,//非异步
    				success: function(msg){
    				 
    					if("true" != msg){
    						//帐号已经存在
    						alert("该货物已经存在，请使用其它名称！");
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
    	function doCheck(){
    	
    		var price = $("#price").val();
    		var re=/^[1-9]+[0-9]*]*$/;
    		var result=re.test(price);
    		if(!result)
    		{
    		   alert("请输入正整数");
    		}
    		else{
    		Result=true;
    		}
    		
    	}
    	//提交表单
    	function doSubmit(){
    	 doVerify();
    	doCheck();
    		if(vResult==true&&Result==true){
	    		//提交表单
	    		document.forms[0].submit();
    		}
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }wms/material_add.action" method="post" >
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong> 货物管理</strong>&nbsp;-&nbsp;新增货物</div></div>
    <div class="tableH2">新增货物</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
       
         <tr>
            <td class="tdBg" width="200px">货物名：</td>
            <td><s:textfield id="name" name="material.name"/> </td>
         </tr>
         <tr>
        <td class="tdBg" width="200px">价格：</td>
            <td><s:textfield id="price"  name="material.price"/> </td>
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