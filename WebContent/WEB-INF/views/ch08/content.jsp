<%@ page contentType="text/html; charset=UTF-8"%>
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
		<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/main.css">
	</head>
	
	<body>
		<div class="wrap">
			<%--헤더 --%>
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
			<%-- 아래와 같은 방법은 복사 붙여넣기라서 동일한 내용이 있으면 에러가 난다.
			<%@ include file="/WEB-INF/views/include/header.jsp" %> --%>
			
			<%--내용 --%>
			<div class="mainCenter">
				<jsp:include page="/WEB-INF/views/include/menu.jsp"></jsp:include>
				
				<div class="content">
					<div class="sector">
						<h5>HttpSession object를 이용한 스칼라 값 전달</h5>
						<div>
							<a class = "btn btn-info btn-sm" href="method1"> 전달</a> 
						</div>
					</div>
					<div class="sector">
						<h5>HttpSession object를 이용한 객체 전달</h5>
						<div>
							<a class = "btn btn-info btn-sm" href="method2"> 전달</a> 
						</div>
					</div>
					<div class="sector">
						<h5>HttpSession object를 이용한 컬렉션 전달</h5>
						<div>
							<a class = "btn btn-info btn-sm" href="method3"> 전달</a> 
						</div>
					</div>
					
					<div class="sector">
						<h5>HttpSession 객체를 이용한 로그인</h5>
						<div>
							<c:if test="${loginStatus == null}"> <%-- loginStatus라는 데이터를 가져옴 --%>
								<div>
									<form action="login" method="post">
										<input type="text" name="uid" placeholder="ID"/>
										<input type="password" name="upw" placeholder="PW"/>
										<button class="btn btn-success btn-sm">로그인</button>
									</form>
								</div>
							</c:if>
							
							<c:if test="${loginStatus != null}">
								<div>
									<a href="logout" class="btn btn-danger btn-sm">로그아웃</a>
								</div>
							</c:if>
						</div>
						
						
					</div>
					<c:if test="${loginStatus!=null}">
						<div class="sector">
							<h5>게시물 입력</h5>
							<div>
								<form action="boardWrite" method="post">
									<input type="text" name="title" placeholder="title" /><br />
									<textarea name="content" cols="50" rows="20" placeholder="내용"></textarea><br />
									<button class="btn btn-info btn-sm">저장</button>
								</form>
							</div>
						</div>
					</c:if>
					
					
				</div>
			</div>
		</div>
	</body>
</html>