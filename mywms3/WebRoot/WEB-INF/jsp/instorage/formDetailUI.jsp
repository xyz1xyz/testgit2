<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>入库单据明细查看</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">
      	//全选、全反选
		function doSelectAll(){
			// jquery 1.6 前
			//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
			//prop jquery 1.6+建议使用
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
      	
      	
      	var list_url = "${basePath}instorage/form_formUI.action";
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
                <div class="c_crumbs"><div><b></b><strong> 入库单据明细</strong></div> </div>
           

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                         
                          <!--   <td width="130" align="center">所属单据</td> -->
                            <td width="100" align="center">货物名称</td>
                            <td width="100" align="center">入库数量</td>
                            <td width="100" align="center">入库仓位</td>
                            
                        </tr>
                        <s:iterator value="#formdtail" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >

                               
     
                              <%--   <td align="center"><s:property value="formId"/></td> --%>
                                <td align="center"><s:property value="materialName"/></td>
                                <td align="center"><s:property value="quantity"/></td>
                                <td align="center"><s:property value="instorageBinName"/></td>
                           </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
          <div class="tc mt20">
          <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
       
    </div>
</form>

</body>
</html>