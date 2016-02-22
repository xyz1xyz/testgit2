<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common.jspf"%>

  <head>
   
    
    <title>登录</title>
    
	
  </head>
  
  <body>
  <div class="head">
      <table width="350" height="260" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="840" align="left"><img src="${pageContext.request.contextPath}/images/login/wms.png"   width="640" height="360"/></td>
          <td align="center">&nbsp;&nbsp;<a href="#"></a></td>
        </tr>
      </table>
    </div>
    <div id="loginDiv">
      <form id="ff" action="${pageContext.request.contextPath }/login_login.action" method="post" >   
      <div>   
        <label for="accLogin">帐号:</label>   
        <input  type="text" name="employee.name"  />   
      </div>   
      <div>   
        <label for="accPass">密码:</label>   
        <input  type="password" name="employee.password"  />   
    </div> 
    <div style="color:red">${requestScope.msg}</div>
     </form>  
   </div>   
   
   <script type="text/javascript">
      $("input[name='employee.name']").validatebox({    
        required: true,    
        //validType: 'email'  
        missingMessage:"请填写帐号" 
      }); 
       $("input[name='employee.password']").validatebox({    
        required: true,    
        //validType: 'email'  
        missingMessage:"请填写密码" 
      }); 
      //禁用验证
      $("#ff").form("disableValidation");
      $("#loginDiv").dialog({    
        title: '登录入口',    
        width:250,    
        height: 150,    
        closed: false,    
        cache : false,
		//设置关闭按钮可见
		closable : false,
		//模式化窗口.如果为true将锁屏
		modal : true,  
		buttons:[{
				text:'登录',
				handler:function(){
				
				 //重启验证
				 $("#ff").form('enableValidation');
				 if($("#ff").form("validate"))
				 {  
				    $("#ff").submit();
				 }
				 
				}
			},{
				text:'取消',
				handler:function(){
				}
			}]
       });    

      
   </script>
</form> 
  </body>
</html>
