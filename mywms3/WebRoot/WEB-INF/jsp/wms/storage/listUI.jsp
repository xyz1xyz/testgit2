<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>仓库管理</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">
      	//全选、全反选
		function doSelectAll(){
			// jquery 1.6 前
			//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
			//prop jquery 1.6+建议使用
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
      	//新增
      	function doAdd(){
      		document.forms[0].action = "${basePath}wms/storage_addUI.action";
      		document.forms[0].submit();
      	}
      	//编辑
      	function doEdit(id){
      	   result=false;
      	    $.ajax({
      	      url:"${basePath}wms/storage_verifyStorage.action",
      	      data:{"storage.id":id},
      	      type:"post",
      	      async: false,//非异步,执行完这个ajax请求之后才能执行下面的提交请求
      	      success:function(msg)
      	      { 
      	         
      	        if("true"!=msg)
      	        {
      	          alert("该仓库已被使用，不能编辑");
      	          result=false;
      	        }
      	        else{
      	         result=true;
      	           
      	        }
      	      }
      	    
      	    });
      	  
      	    if(result){
      		document.forms[0].action = "${basePath}wms/storage_editUI.action?storage.id=" + id;
      		document.forms[0].submit();
      		}
      	}
      	//删除
      	function doDelete(id){
      	 result=false;
      	    $.ajax({
      	      url:"${basePath}wms/storage_verifyStorage.action",
      	      data:{"storage.id":id},
      	      type:"post",
      	      async: false,//非异步,执行完这个ajax请求之后才能执行下面的提交请求
      	      success:function(msg)
      	      { 
      	         
      	        if("true"!=msg)
      	        {
      	          alert("该仓库已被使用，不能删除");
      	          result=false;
      	        }
      	        else{
      	         result=true;
      	           
      	        }
      	      }
      	    
      	    });
      	  
      	    if(result){
      		document.forms[0].action = "${basePath}wms/storage_delete.action?storage.id=" + id;
      		document.forms[0].submit();
      		}
      		
      	}
      	//批量删除
      	function doDeleteAll(){
      		document.forms[0].action = "${basePath}wms/storage_deleteSelected.action";
      		document.forms[0].submit();
      	}
      	
      	var list_url = "${basePath}wms/storage_listUI.action";
    	//搜索
      	function doSearch(){
      		//重置页号
      		$("#pageNo").val(1);
      		document.forms[0].action = list_url;
      		document.forms[0].submit();
      	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post" >
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong> 仓库管理</strong></div> </div>
                <div class="search_art">
                    <li>
                          仓库名：<s:textfield name="storage.name" cssClass="s_text" id="storageName"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                       

                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="140" align="center">仓库名</td>
                            <td width="140" align="center"> 所属地域</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='id'/>" /></td>
                                <td align="center"><s:property value="name"/></td>
                                <td align="center"><s:property value="address"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property value='id'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='id'/>')">删除</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
        <jsp:include page="/common/pageNavigator.jsp"/>
        </div>
    </div>
</form>

</body>
</html>