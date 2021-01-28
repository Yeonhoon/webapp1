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
							<div class="alert alert-primary" role="alert" style="width: 200px;">로그인</div>	
							<form name="loginForm" onsubmit="login()" style="width:200px;"> <%-- 로그인 버튼 누를떄 발생 --%>
							<div class="form-group">
							    <label for="mid" class="form-label">아이디</label> <%--label의 for와 input의 id는 동일해야 함 --%>
							    <input type="text" class="form-control" id="mid" name ="mid">
							    <small id="errorMid" class="form-text text-danger"></small>
							  </div>
							  <div class="form-group">
							    <label for="mpassword" class="form-label">비밀번호</label><br />
							    <input type="password" class="form-control" id="mpassword" name="mpassword" />
							     <small id="errorMpassword" class="form-text text-danger"></small>
							  </div>
							  
							  <button class="btn btn-primary">로그인</button>
							  <a href="boardlist2"  class="btn btn-secondary">취소</a>
							</form>
							
							<script>
								function login() {
									event.preventDefault();
									
									//event.preventDefault();
									//에러 초기화
									$("#errorMid").html("");
									$("#errorMpassword").html("");
									
									var validation = true;
									
								    const mid = $("#mid").val();
								    if(mid==="") {
								        $("#errorMid").html("필수입력사항");
								        var validation = false;
								    }
								    
								    const mpassword = $("#mpassword").val();
								    if(mpassword===""){
								        $("#errorMpassword").html("필수입력사항");
								        var validation = false;
								    }
								    
								    if(!validation){
								        return;
								    }
								    //통신테스트
								    $.ajax({
								        url: "login",
								        method:"post",
								        data: {mid, mpassword}, 
									    //"이름" : "변수" 형태로 넘기기. 이름이 같은 경우에만 mid, mpassword 형태로 넘길 수 있음.//
								        success: function(data){
								            console.log(data);
								            //{"result":"success | wroingMid | wroingMpw"}
								            if(data.result === "success"){
								                alert("로그인 성공");
								                location.href="boardlist2";
							                }
							                else if(data.result ==="wrongMid"){
								                $("#errorMid").text("아이디가 존재하지 않습니다.");
								                
								            } else {
								                $("#errorMpassword").html("비밀번호가 올바르지 않습니다.");
								        	}
								        }
								    });
								}
							</script>
							
						</div>
					</div>
				</div>
			</div>
	</body>
	
</html>