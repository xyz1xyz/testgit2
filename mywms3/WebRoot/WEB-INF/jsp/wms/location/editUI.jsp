<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>地域管理</title>
    
     <script type="text/javascript">
    var vResult = false;
    	//校验帐号唯一
    	function doVerify(){
    		//1、获取帐号
    		var name = $("#name").val();
    		var id=$("#id").val();
    		if(name != ""){
    			//2、校验 
    			$.ajax({
    				url:"${basePath}wms/location_verify.action",
    				data: {"wmsLocation.name": name,"wmsLocation.id":id},
    				type: "post",
    				async: false,//非异步
    				success: function(msg){
    				    
    					if("true" != msg){
    						//帐号已经存在
    						
    						alert("该地域已经存在，请使用其它地域！");
    						//定焦
    						$("#name").focus();
    						vResult = false;
    					} else {
    						vResult = true;
    					}
    				}
    			});
    		}else{
    		alert("请不要为空！");
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
<form id="form" name="form" action="${basePath }wms/location_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>地域管理</strong>&nbsp;-&nbsp;修改地域</div></div>
    <div class="tableH2">修改地域</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        
            <tr>
            <td class="tdBg" width="200px"/> 地域名称：</td>
            <td><s:textfield id="name" name="wmsLocation.name" onchange="doVerify()"></s:textfield></td>
            </tr>
              <tr>
             <td class="tdBg" width="200px">选择分类：</td>
         
	           <td><s:select name="wmsLocation.catogery" list="#{'0':'同一地域同一sn','1':'同一地域不同sn' }" /></td>
	         
        </tr>
             <input type="text" name="aa" style="display:none"/>
             
             
    </table>
    <s:hidden id="id" name="wmsLocation.id"/>
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