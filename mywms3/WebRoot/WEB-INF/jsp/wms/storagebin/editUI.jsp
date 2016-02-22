<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>仓位管理</title>
    <script type="text/javascript">
    var vResult = false;
    	//校验帐号唯一
    	function doVerify(){
    		//1、获取帐号
    		var name = $("#name").val();
    		var storage=$("#storage").val();
    		if(name != ""){
    			//2、校验 
    			$.ajax({
    				url:"${basePath}wms/storagebin_verify.action",
    				data: {"storageBin.name": name,"storageBin.storeName":storage},
    				type: "post",
    				async: false,//非异步
    				success: function(msg){
    				
    					if("true" != msg){
    						//帐号已经存在
    						alert("该仓库的仓位已经存在，请更换仓位名或更换仓库名！");
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
<form id="form" name="form" action="${basePath }wms/storagebin_edit.action" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>仓位管理</strong>&nbsp;-&nbsp;修改仓位</div></div>
    <div class="tableH2">修改仓位</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        
            <tr>
            <td class="tdBg" width="200px"/> 仓位名：</td>
            <td><s:textfield id="name" name="storageBin.name"></s:textfield></td>
            </tr>
          
            <input type="text" name="aa" style="display:none"/>
          <td class="tdBg" width="200px">所属地域：</td>
          <td>
           <s:textfield id="storage" name="storageBin.storeName" readonly="true"></s:textfield>
          </td>
          </tr>
    </table>
    <s:hidden name="storageBin.id"/>
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