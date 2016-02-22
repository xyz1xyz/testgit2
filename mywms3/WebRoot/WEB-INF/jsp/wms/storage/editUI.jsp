<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>仓库管理</title>
       <script type="text/javascript">
    var vResult = false;
    	//校验帐号唯一
    	function doVerify(){
    		//1、获取帐号
    		var name = $("#name").val();
    		//var address=$("#address").val();
    		
    		if(name != ""){
    			//2、校验 
    			$.ajax({
    				url:"${basePath}wms/storage_verify.action",
    				data: {"storage.name": name},
    				type: "post",
    				async: false,//非异步
    				success: function(msg){
    				
    					if("true" != msg){
    						//帐号已经存在
    						alert("该仓库名已经存在，请使用其它名称！");
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
    	 doVerify();
    	
    		if(vResult){
	    		//提交表单
	    		document.forms[0].submit();
    		}
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }wms/storage_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>仓库管理</strong>&nbsp;-&nbsp;修改仓库</div></div>
    <div class="tableH2">修改仓库</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        
            <tr>
            <td class="tdBg" width="200px"/> 仓库名：</td>
            <td><s:textfield id="name" name="storage.name" onchange="doVerify()"></s:textfield></td>
            </tr>
            <tr>
          <td class="tdBg" width="200px">所属地域：</td>
          <td><s:select list="#locationlist" name="storage.address" listKey="name" listValue="name"></s:select></td>
          </tr>
            <input type="text" name="aa" style="display:none"/>
    </table>
    <s:hidden name="storage.id"/>
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