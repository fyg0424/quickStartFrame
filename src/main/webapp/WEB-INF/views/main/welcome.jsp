<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../common/btcss.jsp" %>
	
	<link rel="stylesheet" href="${ctx }/static/lib/weekdatepicker/css/default.css">
	<link rel="stylesheet" href="${ctx }/static/lib/datatables/1.10.9/css/jquery.dataTables.min.css">
	
	<title>世界玖玖供应商管理系统</title>
</head>
<body>
	<ol class="breadcrumb">
        <li><a href="${ctx }/main/welcome">首页</a></li>
    </ol>

    
	<div class="container">
		<h1>欢迎使用世界玖玖供应商管理系统!</h1>
	</div>
	
	<%@ include file="../common/btjs.jsp" %>
	
	<script src="${ctx }/static/lib/weekdatepicker/js/weekDataPicker.js"></script>
	<script src="${ctx }/static/lib/datatables/1.10.9/js/jquery.dataTables.js"></script>
	<script src="${ctx }/static/lib/datatables/1.10.9/js/dataTables.bootstrap.js"></script>
</body>
</html>