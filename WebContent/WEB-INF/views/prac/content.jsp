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
	</head>

	<body>
		<div>
			<h5>연습장</h5>
			<div>안녕하세요</div>
			<a href="myprac" class="btn btn-info btn-sm" >myprac으로 이동하기</a>
			<hr />					
			<form action="content">	content 실행하기</form>
			<hr />
			
			<h5>동기 실행하기</h5>
			<a href="sync" class="btn btn-danger btn-sm">동기 실행</a>
			<br /><br />
			<h5>비동기 실행으로 로그인하기</h5>
			<a class="btn btn-danger btn-sm" href="javascript:async()">비동기 실행</a>
			<script>
				function async(){
				    $.ajax({
				        url:"async",
				        method: "get",
				        success: function(data){
				            console.log(data);
				            $("#signinForm").html(data);
				        }
				    });
				};
			</script>
			<hr />
			<div id="signinForm" style="margin-top:50px;"></div>
			<hr />
			<br />
			<h5>비동기 실행으로 회원가입하기</h5>
			<a href="javascript:asyncJoin()" class="btn btn-primary btn-sm">회원가입</a>
			<script>
				const asyncJoin = () => {
				    $.ajax({
				        url:"asyncJoin",
				        method: "get",
				        success: function(data){
				            console.log(data);
				            $("#joinBtn").html(data);
				        }
				    });
				};
			</script>
			<div id="joinBtn"></div>
			<br /><hr />
			
			<div>
				<h5>DTO 구현하여 입력창 만들기</h5>
				<form action="myDTO" method="post" >
					이름:<input type="text" name="param1" value="이름"/>
					나이: <input type="text" name="param2" value=24 />
					직업: <input type="text" name="param3" value="백수"/>
					주소: <input type="text" name="param3" value="서울"/>

				</form>
			</div>
			<br />
			<hr />
			<div>
				<a href="header" class= "btn btn-secondary">header 정보 얻기</a>
				<br />
				<a href="servletRequest" class = "btn btn-primary">httpServletRequest로 header 정보 얻기</a>
				 <br />
				 <a href="cookie1" class="btn btn-info">cookie 보내기</a>
			</div>
			<br />
			<hr />
			<h5>forward 테스트</h5>
			<div>
				<a href="forward" class="btn btn-primary">forward로 이동하기</a>
				
			</div>
			<br />
			<div>
				<h5>redirect 테스트</h5>
					<a href="redirectLogin" class="btn btn-danger">로그인하기</a>
			</div>
			<br />
			<hr />
			<div>
				<h3>model 이용한 스칼라, 객체, 컬렉션 보내기</h3>
				<h5>스칼라 전달</h5>
				<a href="scala" class="btn btn-primary">전달</a>
				<br />
			
				<h5>객체 전달</h5>
				<a href="object" class="btn btn-primary">전달</a>
				<br />
				
				<h5>컬렉션 전달</h5>
				<a href="collection" class="btn btn-primary">전달</a>
				<br />
				
			</div>
			
			<br />
			<hr />
			<div>
				<h5>session 이용하기</h5>
				
				<c:if test="${loginStatus ==null }">
			<div>
				<form action="login" method="post">
					<input type="text" name="uid"/>
					<input type="password" name="upw"/>
				</form>
			</div>
		</c:if>
		
		<c:if test="${loginStatus != null }">
			<div>
				<a href="logout" class="btn btn-danger"></a>
			</div>
		</c:if>
				
			</div>
			
			<h5>회원가입으로 사진 업로드하기</h5>
		<div>
			<div>
			<form action="upload"  method="post" enctype = "multipart/form-data">
				<input type="text" name="id" placeholder="ID"/>
				<input type="password" name="pw" placeholder="PW" />
				<input type="text" name="fname" placeholder="First Name"/>
				<input type="text" name="lname" placeholder="Last Name" />
				<input type="file" name="photo"/>
				<input type="submit" value="upload"/>
			</form>		
		</div>
		</div>
			<script>
				$(function(){
					  $.ajax({
					      url:"photolist",
					      success: function(data){
					          $("#photolist").html(data);
					      }
					  })  
					})
			</script>	
			<div id="photolist"></div>	
			
		</div>
		
		<div >
			<h5>model 연습</h5>
			<a href="modelLogIn" class="btn btn-primary">model1</a>
			<a href="modelLogIn2" class="btn btn-secondary">model2</a>
			<a href="modelLogIn3" class="btn btn-danger">model3</a>

		</div>
		
		
	</body>
	
</html>