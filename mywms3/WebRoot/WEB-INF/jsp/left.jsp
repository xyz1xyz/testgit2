<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("ctx", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>"/>
    <link href="${ctx}/css/nsfw/css.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/nsfw/style.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        //隐藏菜单
        $(document).ready(function () {
            $("dt a").click(function () {
                var cur = $(this);
                cur.parent().next().toggle(700);
                var cur_dl = cur.parent().parent();
                doRemoveCurClass();
                $(cur_dl).addClass("curr");
            });

            $("dd a").each(function () {
                $(this).bind("click", function () {
                    doRemoveCurClass();
                    $(this).addClass("cur");
                });
            });
           
        });

        function doRemoveCurClass() {
            $("dl").each(function () {
                $(this).removeClass("curr");
                $("dd a").each(function () {
                    $(this).removeClass("cur");
                });
            });
        }

        function closeOtherDD(id) {
            $("dd").each(function () {
                if ($(this).attr("id") != id + "dd") {
                    $(this).hide(700);
                }
            });
        }
    </script>
    <!--[if IE 6]>
    <script type="text/javascript" src="${basePath}js/DD_belatedPNG.js"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('b, s, img, span, .prev, .next, a, input,');
    </script>
    <![endif]-->
    <style>
        * {
            scrollbar-face-color: #dee3e7; /*立体滚动条的颜色（包括箭头部分的背景色）*/
            scrollbar-highlight-color: #ffffff; /*滚动条的高亮颜色（左阴影？）*/
            scrollbar-shadow-color: #dee3e7; /*立体滚动条阴影的颜色*/
            scrollbar-3dlight-color: #eceaea; /*立体滚动条亮边的颜色*/
            scrollbar-arrow-color: #006699; /*三角箭头的颜色*/
            scrollbar-track-color: #efefef; /*立体滚动条背景颜色*/
            scrollbar-darkshadow-color: #eceaea; /*滚动条的基色*/
        }
    </style>
</head>

<body>
<div class="xzfw" style="width: 210px;">
    <div class="xzfw_nav" style="width:214px;min-height:500px;">
        <div class="nBox" style="width:214px;">
            <div class="x_top" style="width:214px;"></div>
            <div class="sm">
				<dl class="">
                    <dt><a class="yh" href="${ctx }wms/employee_listUI.action" target="body"><b></b>员工管理<s class="down"></s>
                    </a></dt>
                </dl>
                <dl class="">
                    <dt><a class="fwyy" href="${ctx }wms/location_listUI.action" target="body"><b></b>地域管理<s class="down"></s>
                    </a></dt>
                </dl>
                
                
                 <dl class="">
                    <dt><a class="fwyy" style="cursor: pointer;"><b></b>仓库管理<s class="down"></s> </a></dt>
                    <dd  style="display:none;">
                        <a class="" href="${ctx }wms/storage_listUI.action" target="body"><b></b>仓库管理</a>
                        <a class="" href="${ctx }wms/storagebin_listUI.action" target="body"><b></b>仓位管理</a>
                    </dd>
                </dl>
                <dl class="">
                    <dt><a class="fwyy" href="${ctx }wms/material_listUI.action" target="body"><b></b>货物管理<s class="down"></s>
                    </a></dt>
                </dl>
                
                 <dl>
                    <dt><a class="xxfb" href="${ctx }wms/customer_listUI.action" target="body"><b></b>客户管理<s
                            class="down"></s> </a></dt>
                </dl>
                <dl>
                    <dt><a class="xxfb" href="${ctx }wms/sn_listUI.action" target="body"><b></b>二维码管理<s
                            class="down"></s> </a></dt>
                </dl>
               
                <dl class="">
                    <dt><a class="tssl" style="cursor: pointer;"><b></b>入库管理<s class="down"></s> </a></dt>
                    <dd  style="display:none;">
                        <a class="" href="${ctx }instorage/instorage_listUI.action" target="body"><b></b>入库</a>
                        <a class="" href="${ctx }instorage/form_formUI.action" target="body"><b></b>入库单据展示</a>
                    </dd>
                </dl>
               <dl class="">
                    <dt><a class="tssl" style="cursor: pointer;"><b></b>出库管理<s class="down"></s> </a></dt>
                    <dd  style="display:none;">
                        <a class="" href="${ctx }outstorage/outstorage_listUI.action" target="body"><b></b>出库</a>
                        <a class="" href="${ctx }outstorage/form_formUI.action" target="body"><b></b>出库单据展示</a>
                    </dd>
                </dl>
                <dl class="">
                    <dt><a class="tssl" style="cursor: pointer;"><b></b>库存管理<s class="down"></s> </a></dt>
                    <dd  style="display:none;">
                        <a class="" href="${ctx }inventory/inventory_listUI.action" target="body"><b></b>仓库库存查询</a>
                        <a class="" href="${ctx }inventorybin/inventorybin_listUI.action" target="body"><b></b>仓位库存查询</a>
                    </dd>
                </dl>

               
            </div>
        </div>
    </div>
</div>
</body>
</html>
