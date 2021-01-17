<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<h5>스칼라값</h5>
			<div>
				<div>이름: ${name}</div>
				<div>나이: ${age}</div>
				<div>주소: ${address}</div>
			</div>
			
		<h5>객체값</h5>
			<div>
				<div>이름: ${myBoard.name}</div>
				<div>나이: ${myBoard.age}</div>
				<div>주소: ${myBoard.address}</div>
			</div>
		
		<h5>컬렉션</h5>	
		<table class="table table-bordered table-small">
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
			</tr>							
			<c:forEach var="dtoBoard" items="${mydtoList}">
				<tr>
					<td>${dtBoard.name}</td>
					<td>${dtBoard.age}</td>
					<td>${dtBoard.address}</td>
				</tr>
			</c:forEach>
		</table>
		
	</body>
	
</html>