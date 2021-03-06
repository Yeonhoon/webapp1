<%@ page contentType="text/html; charset=UTF-8"%>

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
						<h5>헤더값 읽기</h5>
						<div>
							GET 방식: <a class = "btn btn-info btn-sm" href="method2">브라우저 정보 얻기</a> 
						</div>
					</div>
					
					
					<div class="sector">
						<h5>HttpServletResponse를 이용</h5>
						<div>
							<a class = "btn btn-info btn-sm" href="method3">쿠키생성 및 보내기</a> 
						</div>
					</div>
					
					<div class="sector">
						<h5>HttpServletRequset를 이용</h5>
						<div>
							<a class = "btn btn-info btn-sm" href="method5">쿠키 읽기</a> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>