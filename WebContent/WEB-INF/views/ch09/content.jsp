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
			
			<%--내용 --%>
			<div class="mainCenter">
				<jsp:include page="/WEB-INF/views/include/menu.jsp"></jsp:include>
				
				<div class="content">
					<div class="sector">
						<h5>파일 업로드</h5>
						<div>
							<form action="photoupload" method="post" enctype="multipart/form-data">
								<input type="text" name="uid" placeholder="ID"/><br /><br />
								<input type="text"  name="uname" placeholder="NAME"/><br /><br />
								<input type="password"  name="upw" placeholder="PW"/><br /><br />
								<input type="file" name="uphoto" /><br /><br /> <%--multiple="multiple" 로하고자 한다면 dto에서 필드값을 배열로 주어야 함 --%>
								<input type="submit" class="btn btn-primary btn-sm" value="upload"/>
							</form>
						</div>
					</div>
					
						<h5>파일 리스트</h5>
					<div class="sector">
						<div>
							<script>
							<%--자동실행--%>
								$(function() {
								    $.ajax({
								        url:"photolist",
								        success:function(data){
								        	 $("#photolist").html(data); 
								        }
								    })
								});
							</script>
							<div id="photolist"></div>
						</div>
					</div>
					
					
					
				</div>
			</div>
		</div>
	</body>
</html>