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
			<%--내용 --%>
			<div class="mainCenter">
				<jsp:include page="/WEB-INF/views/include/menu.jsp"></jsp:include>
				
				<div class="content">
					<div class="sector">
						<h5>GET 방식 데이터 전달</h5>
						<div>
							<a class="btn btn-info btn-sm" href="method1?param1=문자열&param2=5&param3=3.14&param4=true&param5=2021-01-14">데이터 전달</a>
						</div>
					</div>
					
					<div class="sector">
						<h5>POST 방식 데이터 전달</h5>
						<div>
							<form action="method1" method="post">
								<input type="text" name="param1" value="문자열"/><br />
								<input type="number" name="param2" value="5"/><br />
								<input type="number" name="param3" value="3.14"/><br />
								<input type="checkbox" name="param4" checked /><br /> <%-- 체크박스로 불리언값 주기 --%>
								<input type="date" name="param5" value="2021-01-14"/><br />
								<button class="btn btn-info btn-sm">데이터 전달</button>
							</form>
						</div>
					</div>
					
										<div class="sector">
						<h5>Default Value</h5>
						<div>
							<form action="method3" method="post">
								<input type="text" name="param1" value="Winter"/><br />
								<button class="btn btn-info btn-sm">데이터 전달</button>
							</form>
						</div>
					</div>
					</div>
				</div>
			</div>
	</body>
</html>