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
							<div class="alert alert-primary" role="alert">게시물 수정</div>
							<form name="boardupdateform" action="boardupdate" method="post">
								<input type="hidden" name="bno" value="${board.bno}"/> <!-- hidden: 보이지는 않지만 데이터로 넘어감. -->
							  <div class="form-group">
							    <label for="btitle" class="form-label">제목</label> <%--label의 for와 input의 id는 동일해야 함 --%>
							    <input type="text" value="${board.btitle}" class="form-control" id="btitle" name ="btitle">
							    <small class="form-text text-muted"></small>
							  </div>
							  
							  <div class="form-group">
							    <label for="bcontent" class="form-label">내용</label><br />
							    <textarea name="bcontent" id="bcontent" cols="200" rows="5">${board.bcontent}</textarea>
							  </div>
							  
							  <a href="boardlist2"  class="btn btn-success">목록</a>
							  <!-- 로그인을 했을 때만 보이고 자신이 쓴 글만 보여질 수 있도록 -->
								  <a href="boardupdate?bno=${board.bno}"  class="btn btn-warning">목록</a>
								  <button>수정</button>
							</form>
						</div>
					</div>
				</div>
			</div>
	</body>
	
</html>