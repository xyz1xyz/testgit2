<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>出库单据查看</title>
    <%@include file="/common/header.jsp" %>
     <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
      	//全选、全反选
		function doSelectAll(){
			// jquery 1.6 前
			//$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
			//prop jquery 1.6+建议使用
			$("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));		
		}
      	
      	var list_url = "${basePath}outstorage/form_formUI.action";
    	//查看明细
      	function doCheck(id){
      		
      		var list_detailurl="${basePath}outstorage/form_formDetailUI.action?formId="+id;
      		document.forms[0].action = list_detailurl;
      		document.forms[0].submit();
      	}
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
                <div class="c_crumbs"><div><b></b><strong> 出库单据查看</strong></div> </div>
                <div class="search_art">
                  <li>
                          单据编号：<s:textfield name="form.id" cssClass="s_text"   cssStyle="width:200px;"/>
                    </li>
                    <li>
                         出库仓库：<s:textfield name="form.outStorage" cssClass="s_text"   cssStyle="width:80px;"/>
                    </li>
                     <li>
                          操作人：<s:textfield name="form.operator" cssClass="s_text"   cssStyle="width:80px;"/>
                    </li>
                 <li>
                       	创建时间：<s:textfield name="startTime" cssClass="s_text"  cssStyle="width:100px;"
                       	 readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/>
                              - 
                             <s:textfield  name="endTime" cssClass="s_text"  cssStyle="width:100px;"
                              readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/>
                    </li>
                    
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                   
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                           <td width="140" align="center">单据编号</td>
                            <td width="140" align="center">单据类型</td>
                            
                            <td width="140" align="center">创建时间</td>
                            <td width="140" align="center">业务发生时间</td>
                            <td width="80" align="center">出库仓库</td>
                            <td width="80" align="center">操作人</td>
                            
                            <td width="80" align="center">查看明细</td>
                            
                        </tr>
                        <s:iterator value="pageResult.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                
                               
                               <td align="center"><s:property value="id"/></td>
                                <td align="center"><s:property value="#FORM_TYPE_MAP[type]"/></td>
                               
                                <td align="center"><s:date name="createDate" format="yyyy-MM-dd HH:mm"/></td>
                                <td align="center"><s:date name="optime" format="yyyy-MM-dd HH:mm"/></td>
                                <td align="center"><s:property value="outStorage"/></td>
                                <td align="center"><s:property value="operator"/></td>
                                
                                <td align="center">
                                  
                                    <a href="javascript:doCheck('<s:property value='id'/>')">查看明细</a>
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