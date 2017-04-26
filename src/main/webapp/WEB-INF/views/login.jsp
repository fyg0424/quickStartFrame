<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="common/btcss.jsp" %>
	
	<title>DEMO</title>

	<link rel="stylesheet" href="${ctx }/static/css/login.css">

</head>

<body>
	<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1>
					<small>DEMO</small>
				</h1>
			</div>
			<div class="login-content ">
				<form id="loginform" action="${ctx }/userInfoContorller/login" method="post">
				<div class="form">
					<div style="text-align: center; color: red;">
						<label id="message">${error }</label>
					</div>
					<div class="form-group">
						<div class="col-xs-12  ">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</span>
								<input type="text" id="name" name="name" class="form-control" placeholder="请输入用户名">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-12  ">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
							</div>
						</div>
					</div>
					<div class="form-group form-actions">
						<div class="col-xs-4"></div>
						<div class="col-xs-4 ">
							<button id="login" type="submit" class="btn btn-sm btn-info">
								<span class="glyphicon glyphicon-off"></span> 登录
							</button>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="common/btjs.jsp" %>
	
</body>
</html>