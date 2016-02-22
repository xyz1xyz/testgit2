<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>二唯码管理</title>
       <script type="text/javascript">
    var vResult = false;
    	 //校验二维码唯一                                                       
      function doVerifySN(){
     var sn = $("#sn").val();
  
       $.ajax({
    				url:"${basePath}wms/sn_verifySN.action",
    				data: {"sn.sn":sn},
    				type: "post",
    				
    				success: function(msg){
    				 
    					if("true" != msg){
    						
    						alert("该二维码已存在，请更改！");
    						//定焦
    						$("#sn").focus();
    						vResult = false;
    					} else {
    						vResult = true;
    					}
    					
                   }
         });
        
       }
       
    	//提交表单
    	function doSubmit(){
    	var sn = $("#sn").val();
    	 if(sn=="")
    	 {
    	  alert("请输入二维码!");
    	  return false;
    	 }
    	doVerifySN();
    	
    	
    		if(vResult){
	    		//提交表单
	    		document.forms[0].submit();
    		}
    	}
    </script>
     
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }wms/sn_edit.action" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>二唯码管理</strong>&nbsp;-&nbsp;修改二唯码</div></div>
    <div class="tableH2">修改二唯码</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
            <tr>
          <td class="tdBg" width="200px">地域名：</td>
           <td><s:textfield id="addressName" name="sn.addressName" readonly="true"/> </td>
         
          </tr>
          
          
            
            <tr>
            <td class="tdBg" width="200px">货物名：</td>
             <td><s:textfield id="materialName" name="sn.materialName" readonly="true"/> </td>
         
           </tr>
          
        <tr>
            <td class="tdBg" width="200px">二维码：</td>
            <td><s:textfield id="sn" name="sn.sn"/> </td>
         </tr>
         <input type="text" name="aa" style="display:none"/>
    </table>
    <s:hidden name="sn.id"/>
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