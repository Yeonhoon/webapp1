<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	</head>

	<body>
		<div>
			<h5>model 구역</h5>
				<input type="text" value="id" placeholder="ID"/>
				<input type="password" value="id" placeholder="PW"/>
				<br>
				<hr />
				<br />
				<input type="text" value="id" placeholder="ID"/>
				<input type="password" value="id" placeholder="PW"/>
				<input type="submit" value="제출"/>
				<br>
				<hr />
				<br />
				<input type="text" value="id" placeholder="ID"/>
				<input type="password" value="id" placeholder="PW"/>
				<input type="submit" value="제출"/>
				<br>
				<hr />
				<br />
			<h5>session 구역</h5>
			<c:if test="${loginStatus == null}">
				<form action="session1" method="post">
					<input type="text" name = "uid" placeholder="ID"/>
					<input type="password" name="upw" placeholder="PW"/>
					<button class="btn btn-success">로그인</button>
				</form>
			</c:if>
			<c:if test="${loginStatus!=null}">
				<a href="session2" class="btn btn-danger">로그아웃</a>
			</c:if>
		</div>
	
	
	</body>
	
</html>