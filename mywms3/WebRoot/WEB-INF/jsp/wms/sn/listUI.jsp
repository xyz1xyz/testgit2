<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>二维码管理</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">
      	//全选、全反选
		function doSelectAll(){
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
      	//新增
      	function doAdd(){
      		document.forms[0].action = "${basePath}wms/sn_addUI.action";
      		document.forms[0].submit();
      	}
      	//编辑
      	function doEdit(id){
      		document.forms[0].action = "${basePath}wms/sn_editUI.action?sn.id=" + id;
      		document.forms[0].submit();
      	}
      	//删除
      	function doDelete(id){
      		document.forms[0].action = "${basePath}wms/sn_delete.action?sn.id=" + id;
      		document.forms[0].submit();
      	}
      	//批量删除
      	function doDeleteAll(){
      		document.forms[0].action = "${basePath}wms/sn_deleteSelected.action";
      		document.forms[0].submit();
      	}
      	
      	var list_url = "${basePath}wms/sn_listUI.action";
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
                <div class="c_crumbs"><div><b></b><strong> 二维码管理</strong></div> </div>
                <div class="search_art">
                  <li>
                        地域名：<s:textfield name="sn.addressName" cssClass="s_text"   cssStyle="width:120px;"/>
                    </li>
          
                    <li>
                          货物名：<s:textfield name="sn.materialName"  cssClass="s_text"  cssStyle="width:120px;"/>
                    </li>
                  
                       <li>
                        二维码：<s:textfield name="sn.sn" cssClass="s_text"  cssStyle="width:120px;"/>
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
                           <td width="140" align="center">地域名</td>
                           
                             <td width="140" align="center">货物名</td>
                             <td width="140" align="center">二维码</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='id'/>" /></td>
                                <td align="center"><s:property value="addressName"/></td>
                              
                                <td align="center"><s:property value="materialName"/></td>
                                <td align="center"><s:property value="sn"/></td>
                               
                               
                               
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