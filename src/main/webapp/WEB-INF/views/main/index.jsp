<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../common/btcss.jsp" %>
	
	<link href="${ctx}/static/css/menu.css" rel="stylesheet">
	<link href="${ctx}/static/lib/layout/1.4.0/css/layout-default-latest.css" rel="stylesheet">
	
	<title>DEMO</title>
</head>
<body>
	<div class="ui-layout-north">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><span style="font-weight:bolder;">DEMO</span>
                    </a>
                </div>
                <div class="collapse navbar-collapse" style="margin-right: 40px;">
                    <p class="navbar-text navbar-right" style="font-size:14px; ">欢迎您，<c:out value="${sessionScope.user.showname}"></c:out>
                        <a href="${ctx}/logout" class="navbar-link">&nbsp;[退出]</a>
                    </p>
                </div>
            </div>
        </nav>
    </div>
    <div class="ui-layout-west">
        <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
        	<c:forEach items="${menus }" var="menu">
        		<li class="active">
                <a class="btn btn-primary" role="button" data-toggle="collapse" href="#${menu.id }"
                   aria-expanded="false" aria-controls="collapseExample">
                   ${menu.resourcename }
                </a>

                <div class="collapse" id="${menu.id }">
                	<c:forEach items="${menu.subResources }" var="childmenu">
                		<ul class="nav nav-list collapse secondmenu">
                        <li><a href="${ctx }${childmenu.resourceurl}" target="main">${childmenu.resourcename }</a></li>
                    </ul>
                	</c:forEach>
                </div>
             </li>
        	</c:forEach>
        </ul>
    </div>


    <div class="ui-layout-center" style="top:60px;">
        <iframe name="main" 
                style="height: 100%; width: 100%; border: 0px solid"
                src="${ctx }/main/welcome"></iframe>
    </div>
	
	
	<%@ include file="../common/btjs.jsp" %>
	<script src="${ctx}/static/lib/layout/1.4.0/js/jquery.layout-latest.js"></script>
	<script src="${ctx}/static/lib/layer/layer.js"></script>
	
	<script>
		$(document).ready(function() {
			$('body').layout({
				applyDemoStyles : false,
				north__size: 54,
				north__closable:false,//可以被关闭  
			    north__resizable:false//可以改变大小
			});
	
			//菜单点击改变背景色
			$("#main-nav li").click(function() {
				$("#main-nav li").removeClass("active");
				$(this).addClass("active");
			});

			$('.collapse').collapse();
		});
	</script>
</body>
</html>