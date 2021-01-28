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
							<h5>게시판</h5>
							<div>
								<table class="table table-bordered">
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>글쓴이</th>
										<th>조회수</th>
										<th>날짜</th>
									</tr>
									<c:forEach var="board" items="${list}">
										<tr>
										<!-- vars: alias 값, items: 컨트롤러에서  모델에 넣은 value값 -->
											<td>${board.bno}</td>
											<td><a class="text-decoration-none" href="boardread?bno=${board.bno}">${board.btitle}</a></td> <!-- get방식은 식별자로 넘겨주는게 일반적 -->
											<td><img src="mphoto?mid=${board.bwriter}" class="rounded-circle" width="30px"></td>
											<td>${board.bhitcount}</td>
											<td><fmt:formatDate value="${board.bdate}" pattern="yyyy.MM.dd"/></td> 
										</tr>
									</c:forEach>
								</table>
							<div class="d-flex justify-content-between align-items-center" >
								<c:if test="${sessionMid != null}">
									<a class="btn btn-sm btn-primary" href = "boardwrite">글쓰기</a>
								</c:if>
								<c:if test="${sessionMid == null}">
									<span></span>
								</c:if>
								<div>
									<a class="btn btn-outline-warning btn-sm mr-1" href="boardlist2?pageNo=1">처음</a>
									<c:if test="${pager.groupNo > 1}">
										<a class="btn btn-outline-warning btn-sm mr-1" href="boardlist2?pageNo=${pager.startPageNo-1}">이전</a>
									</c:if>
									<c:forEach var="idx" begin="${pager.startPageNo}" end="${pager.endPageNo}">
										<c:if test="${idx== pager.pageNo}">
											<a class="btn btn-danger btn-sm mr-1" href="boardlist2?pageNo=${idx}">${idx}</a>
										</c:if>
										<c:if test="${idx!= pager.pageNo}">
											<a class="btn btn-outline-light btn-sm mr-1" href="boardlist2?pageNo=${idx}">${idx}</a>
										</c:if>
									</c:forEach>
									<c:if test="${pager.groupNo < pager.totalGroupNo}"> <%--전체 그룹수 보다 적을 경우 --%>
										<a class="btn btn-outline-warning btn-sm mr-1" href="boardlist2?pageNo=${pager.endPageNo+1}">다음</a>
									</c:if>
									<a class="btn btn-outline-warning btn-sm mr-1" href="boardlist2?pageNo=${pager.totalPageNo}">끝</a>
								</div>
							</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
	</body>
	
</html>