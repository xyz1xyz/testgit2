<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>地域管理</title>
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
      		document.forms[0].action = "${basePath}wms/location_addUI.action";
      		document.forms[0].submit();
      	}
      	//编辑
      	function doEdit(id){
      	/*  result=false;
      	    $.ajax({
      	      url:"${basePath}wms/location_verifyLocation.action",
      	      data:{"wmsLocation.id":id},
      	      type:"post",
      	      async: false,//非异步,执行完这个ajax请求之后才能执行下面的提交请求
      	      success:function(msg)
      	      { 
      	         
      	        if("true"!=msg)
      	        {
      	         //alert(msg);
      	         alert("该地域已被使用，不能编辑");
      	      //window.location.href="${ctx }outstorage/storage_listUI.action";
      	    
      	       
      	        // <a class="" href="${ctx }outstorage/outstorage_listUI.action" target="body"><b></b>出库</a>
      	         result=false;
      	        }
      	        else{
      	         result=true;
      	           
      	        }
      	      }
      	    
      	    });
      	  
      	    if(result){
      		document.forms[0].action = "${basePath}wms/location_editUI.action?wmsLocation.id=" + id;
      		document.forms[0].submit();
      		} */
      		
      		     document.forms[0].action = "${basePath}wms/location_editUI.action?wmsLocation.id=" + id;
      		document.forms[0].submit();
      	   
      		 
      		
      	}
      	//删除
      	function doDelete(id,name){
      	 
      	    result=false;
      	    if (confirm('您确定要删除吗？') == true) {
      	    $.ajax({
      	      url:"${basePath}wms/location_verifyLocation.action",
      	      data:{"wmsLocation.id":id},
      	      type:"post",
      	      async: false,//非异步,执行完这个ajax请求之后才能执行下面的提交请求
      	      success:function(msg)
      	      { 
      	         
      	        if("true"!=msg)
      	        {
      	          alert("已有仓库，请删除"+name+"的所有仓库");
      	          
      	           window.open("${ctx }wms/storage_listUI.action");
      	          result=false;
      	        }
      	        else{
      	         result=true;
      	          
      	        }
      	      }
      	    
      	    });
      	    }
      	  
      	    if(result){
      	       
      		document.forms[0].action = "${basePath}wms/location_delete.action?wmsLocation.id=" + id;
      		document.forms[0].submit();
      		
      		}
      	}
      	//批量删除
      	function doDeleteAll(){
      	 if (confirm('您确定要全部删除吗？') == true){
      		document.forms[0].action = "${basePath}wms/location_deleteSelected.action";
      		document.forms[0].submit();
      		}
      	}
      	
      	var list_url = "${basePath}wms/location_listUI.action";
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
                <div class="c_crumbs"><div><b></b><strong> 地域管理</strong></div> </div>
                <div class="search_art">
                    <li>
                          地域名：<s:textfield name="wmsLocation.name" cssClass="s_text" id="wmsLocationName"  cssStyle="width:160px;"/>
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
                            <td width="140" align="center">ID</td>
                            <td width="140" align="center"> 地域名</td>
                             <td width="140" align="center">所属分类</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='id'/>" /></td>
                                <td align="center"><s:property value="id"/></td>
                                <td align="center"><s:property value="name"/></td>
                                <td align="center"><s:property value="#CATOGERY_MAP[catogery]"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property  value='id'/>')"  >编辑</a>
                                    <a href="javascript:doDelete('<s:property  value='id'/>','<s:property  value='name'/>')">删除</a>
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