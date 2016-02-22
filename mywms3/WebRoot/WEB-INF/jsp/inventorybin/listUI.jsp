<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>仓位库存查询</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">
      
      	var list_url = "${basePath}inventorybin/inventorybin_listUI.action";
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
                <div class="c_crumbs"><div><b></b><strong>仓位库存查询</strong></div> </div>
                <div class="search_art">
                    <li>
                          仓库名：<s:textfield name="inventoryBin.storageName" cssClass="s_text"   cssStyle="width:160px;"/>
                           仓位名：<s:textfield name="inventoryBin.storageBinName" cssClass="s_text"   cssStyle="width:160px;"/>
                         货物名：<s:textfield name="inventoryBin.materialName" cssClass="s_text"   cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                   
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                           
                            <td width="140" align="center">仓库名</td>
                            <td width="140" align="center">仓位名</td>
                            <td width="140" align="center">货物名</td>
                            <td width="140" align="center">库存数量</td>
                           
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                              
                                <td align="center"><s:property value="storageName"/></td>
                                <td align="center"><s:property value="storageBinName"/></td>
                                <td align="center"><s:property value="materialName"/></td>
                              
                                <td align="center"><s:property value="quantity"/></td>
                               
                                
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