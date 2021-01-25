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
		<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/main.css">
	<style type="text/css">
		.section {
			margin-bottom:50px;
		}
	</style>
	
	<script>
		function logout(){
		    confirm = window.confirm("로그아웃하시겠습니까?");
		        if(confirm){
		           $.ajax({
		               method:"get",
		               url:"logout",
		               success: function(data){
		                   alert("로그아웃되었습니다");
		                   location.reload();
		               }
		           })
		        }
		    }
		
	</script>
	</head>

	<div class="section">
		<h3>1. 로그인 로그아웃</h3>
		<c:if test="${loginStatus eq null }">
			<a href="login"  class="btn btn-success btn-sm">로그인</a>
		</c:if>
		
		<c:if test="${loginStatus ne null}">
			<input type= "button" onclick="logout();" id="logout" class="btn btn-danger btn-sm" value="로그아웃">
		</c:if>
		
		<c:choose>
			<c:when test="${loginStatus.uid ne null}">
				<a><b>${loginStatus.uid}님</b> 반갑습니다!</a>
			</c:when>			
		</c:choose>
	</div>
	<div class="section">
		<c:choose>
			<c:when test="${loginStatus.uid ne null}">
			<h3>2. 글쓰기 페이지</h3>
			<a href="write" class="btn btn-secondary btm-sm">글쓰기</a>
			</c:when>
		</c:choose>
	</div>
	
	
	<div class="section">
		<h3>3. 게시판 불러오기</h3>
		<a href="<%=application.getContextPath()%>/prac/boardlist" class="btn btn-primary btn-sm">게시판 호출</a>
		<br />
		<br />
		<table class="table table-bordered table-small">
			<tr>
				<th>아이디</th>
				<th>패스워드</th>
			</tr>
			<c:forEach var="user" items="${myList}">
					<tr>
						<td>${user.uid}</td>
						<td>${user.upw}</td>
					</tr>		
			</c:forEach>
		</table>
	</div>	
	<div class="section">
		<h5>DB와의 연결 확인하기</h5>
		<a href="javascript:conntest()" class="btn btn-small btn-danger">연결시도</a>		
		<script>
			const conntest = function() {
			    $.ajax({
			        url:"conntest",
			        method: "get",
			        success: function(data){
			            $("#connspan").html(data)
			        }
			    });
			}
		</script>
		<span id="connspan" style="color:red; font-weight: bold;"></span>
	</div>
	<div class="section">
		<h5>json으로 파일 받기</h5>
		<a href="javascript:json1()" class="btn btn-primary">javascript 객체{  } 받기</a><br />
		<a href="javasript:json2()" class="btn btn-primary">javascript 배열[ ] 받기</a><br />
	<script>
		function json1(){
		    $.ajax({
		        url:"jsonobject",
		    	method: "get",
		    	success: function(data){
		    	    console.log(data);
		    	 	$("#name").html(data.name);
		    	 	$("#hobby").html(data.hobby);
		    	 	$("#dreamcar").html(data.dreamcar.brand);
		    	 	<%-- 배열 출력 --%>
		    	 	for(var i=0; i<data.OverWatch.length; i++){
			    	 	$("#hero").append(data.OverWatch[i]);
		    	 	    
		    	 	}
		    	}
		    });
		}		
	</script>
	<div>
		<div id="name"></div>
		<div id="hobby"></div>
		<div id="dreamcar"></div>
		<div id="hero"></div>
	</div>
	
		
	</div>
	
	<div class="section">
		<h5>DB에서 데이터 가져오기</h5>
		<a href=""></a>
		
		
	</div>
	
		
	</body>
	
</html>

