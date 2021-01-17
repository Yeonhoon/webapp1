<%@ page contentType="text/html; charset=UTF-8"%>
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
						<h5>스칼라 값(숫자, 문자열, 논리값 등) 읽기</h5>
						<div>
							<div>이름: ${name}</div>
							<div>나이: ${age}</div>
							<div>직업: ${job}</div>
						</div>
					</div>
					
					<div class="sector">
						<h5>객체의 데이터 읽기</h5>
						<div>
							<div>번호: ${board1.num}</div> <%--단순한 필드가 아니라 객체를 호출하는 코드 --%>
							<div>제목: ${board1.title}</div>
							<div>내용: ${board1.content}</div>
							<div>작성자: ${board1.writer}</div>
							<div>작성일자: ${board1.date}</div>
						</div>
					</div>
					
					<div class="sector">
						<h5>컬렉션 이용</h5>
						<div>
							<table style="width:auto" class="table table-small table-bordered">
								<tr style="boackground-color:orange; color:black;">
									<th>번호</th>
									<th>제목</th>
									<th>내용</th>
									<th>글쓴이</th>
									<th>날짜</th>
								</tr>
								<%--jstl에서 제공하는 태그 사용하기 --%>
								<c:forEach var="board" items = "${boardList}"> <%-- items: 반복하는 횟수, var: 반복하고자 하는 객체 --%>
									<tr>
										<td>${board.num}</td>
										<td>${board.title}</td>
										<td>${board.content}</td>
										<td>${board.writer}</td>
										<td><fmt:formatDate value="${board.date}" pattern="yy-MM-dd"/></td>
									</tr>
								</c:forEach> 
							</table>
						
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</body>
</html>