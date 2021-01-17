<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mainHeader">
	<h3><a href="<%=application.getContextPath()%>"> Spring traningCamp</a></h3>
	
	<div class="loginBox" style="box-sizing:border-box;">
		<c:if test="${loginStatus == null}">
			<a id="logIn" class="btn btn-info btn-sm" href="<%=application.getContextPath()%>/ch08/content">로그인</a>
		</c:if>
		
		<c:if test="${loginStatus!=null}">
			<img src="<%=application.getContextPath()%>/resources/img/my.png" class="rounded-circle" width="40px">
			<a id="logOut" class="btn btn-danger btn-sm" href="<%=application.getContextPath()%>/ch08/logout">로그아웃</a>
		</c:if>
	</div>
</div>