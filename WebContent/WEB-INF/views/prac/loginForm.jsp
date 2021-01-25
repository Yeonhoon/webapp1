<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="${root}/resources/css/login.css">
<style type="text/css">
	form {
		margin: 0 auto;
		border: 1px dashed black;
		background-color:#d0ebff;
		width: 50%;
		heigth: 50%;
		text-align: center;
		padding: 0 auto;
		box-sizing: border-box;
	}
</style>

<script>
	function login(){
	    event.preventDefault();
	    if($("#uid").val() == ""){
	        $("#uiderror").html("<p style='color:red;'>아이디를 입력하세요</p>")
	        return;
	    } 
	    else if($("#upw").val() == ""){
	        $("#upwerror").html("<p style='color:red;'>비밀번호를 입력하세요</p>")
	        return;
	    } else{
	        $("#loginForm").attr("action","loginAccess");
	        $("#loginForm").submit();
	    }
	}

</script>
<form action="" method="post" id="loginForm">
	<h2>SignIn</h2>
		<label>아이디</label><br />
		<input type="text" name="uid" id="uid" placeholder="ID"/><br />
		<span id="uiderror"></span>
		<label for="">패스워드</label><br />
		<input type="password" name="upw" id="upw" placeholder="PW"/><br/>
		<span id="upwerror"></span>
		<input type="button" class = "btn btn-success" onclick= "javascript:login();" value="로그인"/>
</form>
