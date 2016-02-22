<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <title>入库管理</title>
   <%--  <%@include file="/common/header.jsp" %> --%>
      <link href="${basePath}css/skin1.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="../themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="../themes/icon.css" type="text/css"></link>
    <script type="text/javascript" src="../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../jeasyui/easyui-lang-zh_CN.js"></script>
   
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
     <script type="text/javascript">
      
      	
      	
      	var list_url = "${basePath}instorage/instorage_inStorage.action";
    	//搜索
      	function doSubmit(){
      		var storage = $("#storage");
    		if(storage.val() == "选择仓库"){
    			alert("请选择仓库！");
    			
    			return false;
    		}
    		var storagebin = $("#storagebin");
    		if(storagebin.val() == "选择仓位"){
    			alert("请选择仓位！");
    			
    			return false;
    		}
    		var time = $("#timeID");
    		if(time.val() == ""){
    			alert("业务发生时间不能为空！");
    			
    			return false;
    		}
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
                <div class="c_crumbs"><div><b></b><strong> 入库管理</strong></div> </div>
                
                
          <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
       
         <tr>
          
          <td class="tdBg" width="200px">地域：</td>
          <td> <!-- <select id="location">
		         <option>选择地域</option>
		         <option>北京</option>
		         <option>上海</option>
		          <option>广东</option>
	          </select> -->
	          <s:select id="location" list="#request.locationlist" name="location.name" listKey="name" listValue="name" headerKey="''" headerValue="选择地域"></s:select>
	     </td>
         </tr>
         <tr>
          <td class="tdBg" width="200px">仓库：</td>
          <td> <select id="storage" name="form.inStorage">
		      <option>选择仓库</option>
	         </select>
	        

	     </td>
        </tr>
        <tr>
           <td class="tdBg" width="200px">仓位：</td>
          <td> <select id="storagebin" name="formdetail.instorageBinName" >
		      <option >选择仓位</option>
	         </select>
	        
	     </td>
         </tr>
          <tr>
            <td class="tdBg" width="200px">产品：</td>
            <td><s:select  list="#request.materiallist" name="formdetail.materialId" listKey="id" listValue="name" ></s:select> </td>
         </tr>
         <tr>
            <td class="tdBg" width="200px">数量：</td>
            <td><s:textfield id="quantity" name="formdetail.quantity"/> </td>
         </tr>
         <tr>
         
       
            
    
          <td class="tdBg" width="200px">业务发生时间：</td>
           <td><s:textfield id="timeID" name="time" cssClass="s_text"  cssStyle="width:160px;"
                       	 onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd HH:mm'});"/></td>
          
         </tr>
         <tr>
            <td class="tdBg" width="200px">操作人：</td>
            <td><s:textfield  name="form.operator" value="%{#session.USER.name}" readonly="true"/> </td>
         </tr>
         
       
        
    </table>
    <div class="tc mt20">
        <input type="button" id="button" class="btnB2" value="入库" onclick="doSubmit()" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
     
    </div>
    </div></div></div>
                
    <script type="text/javascript">
    //alert("jfjfjfjg");
    //定位"区域"下拉框，同时提交change事件
		$("#location").change(function(){
			//清空仓库下拉框中的内容，除第一项外
			$("#storage option:gt(0)").remove();
			//清空仓位下拉框中的内容，除第一项外
			$("#storagebin option:gt(0)").remove();
			//获取选中的区域
			var location = $("#location option:selected").text();
			//alert(location);
			//如果不是"选择区域"的话
			if("选择区域" != location){
				//异步发送请求到服务器
				//参数一：url表示请求的路径
				var url = "${pageContext.request.contextPath}/instorage/findStorageByLocation.action?time="+new Date().getTime();
				//alert(url);
				//参数二：sendData表示以JSON格式发送的数据
				var sendData = {
					"location.name" : location
				};
			
				//参数三：function(){}处理或回调函数
				$.post(url,sendData,function(backData,textStatus,ajax){
					//测试 
					//alert( ajax.responseText );
					//测试，backData是一个js对象，cityList是属性
					var array = backData.storagelist;
					//数组的长度
					var size = array.length;
					//迭代数组
					for(var i=0;i<size;i++){
						//获取数组中的每个元素
						var storages = array[i];
						
						//创建option元素
						var $option = $("<option >"+storages+"</option>");
						//将option元素添加到城市下拉框中
						$("#storage").append( $option );
					}
				});
			}
		});			
    
    </script>            
         
     <script type="text/javascript">
    //定位"仓库"下拉框，同时提交change事件
		$("#storage").change(function(){
			
			//清空仓位下拉框中的内容，除第一项外
			$("#storagebin option:gt(0)").remove();
			//获取选中的仓库
			var storeName = $("#storage option:selected").text();
			
			//如果不是"选择区域"的话
			if("选择仓库" != storeName){
				//异步发送请求到服务器
				//参数一：url表示请求的路径
				var url = "${pageContext.request.contextPath}/instorage/findBinByStorage.action?time="+new Date().getTime();
				//alert(url);
				//参数二：sendData表示以JSON格式发送的数据
				var sendData = {
					"storageBin.storeName" : storeName
				};
			
				//参数三：function(){}处理或回调函数
				$.post(url,sendData,function(backData,textStatus,ajax){
					//测试 
					//alert( ajax.responseText );
					//测试，backData是一个js对象，cityList是属性
					var array = backData.storagebinlist;
					//数组的长度
					var size = array.length;
					//迭代数组
					for(var i=0;i<size;i++){
						//获取数组中的每个元素
						var storageBins = array[i];
						//alert(storageBin);
						//创建option元素
						var $option = $("<option >"+storageBins+"</option>");
						//将option元素添加到城市下拉框中
						$("#storagebin").append( $option );
					}
				});
			}
		});			
    
    </script> 
     <script type="text/javascript">
		$("#quantity").numberspinner({
			min : 1,
			max : 1000,
			value : 1
		});
	</script> 
</form>

</body>
</html>