<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<style type="text/css">
		#savewriteForm{
			padding:100px;
		}
		.tblCat{
			width: 20%;
			text-align: center;
		}
		
		.tblInput{
			width: 40%;
		}
		
	</style>
	</head>
	

	
	
	<body>
		<div>
			<h5>글쓰기</h5>
		</div>
	
		<form action="" method="post" id="savewriteForm">
			<table class="table table-bordered table-small">
				<tr>
					<th class="tblCat">목록</th>
					<th class="tblInput">입력란</th>
				</tr>
				<tr>
					<td class="tblCat">제목</td>
					<td class="tblInput"><input type="text" name="utitle" id="title"/><span id="titlespn"></span></td>
				</tr>
				<tr>
					<td class="tblCat">글쓴이</td>
					<td class="tblInput"><input type="text" name="uwriter" id="writer"/><span id="writerspn"></span></td>
				</tr>
				<tr>
					<td class="tblCat">내용</td>
					<td class="tblInput"><input type="text" name="ucontent" id="content"/><span id="contentspn"></span></td>
				</tr>
				<tr>
					<td class="tblCat">일자</td>
					<td class="tblInput"><input type="date" name="udate" id="date"/><span id="datespn"></span></td>
				</tr>
				<tr>
					<td class="tblCat">사진첨부</td>
					<td class="tblInput"><input type="file" name="ufile" id="ufile"/><span id="photospn"></span></td>
				</tr>
			</table>
				<a href="javascript: savewrite();"id="savewrite" type="submit" class="btn btn-success btn-sm">저장</a>  
				<button class="btn btn-secondary btn-sm" onclick="javascript: history.back();">취소</button>  
		</form>
		
	</body>
	
	<script>
		function savewrite(){
		    if($("#title").val()==""){$("#titlespn").html("<p style='color:red;'>입력 필수</p>");
		        return;
		    }
		    else if($("#content").val()==""){$("#contentspn").html("<p style='color:red;'>입력 필수</p>");
		    	return;
		        }
		    else {
		       const confirm = window.confirm("게시물을 저장하시겠습니까?");
		       if(confirm){
		           $("#savewriteForm").attr("action","savewrite").submit();
		           
		           /* 	$.ajax({
		               method:"post",
		               url:"savewrite",
		               success: function(data){
		                   alert("게시물이 저장되었습니다.");
		               },
		               error: function(data){
		                   alert("에러 발생");
		               }
		           }) */
/* 		           
		         	$("#saveform").attr("action","${root}/prac/savewrite");
		         	$("#saveform").submit(); */
		       }
		    }
	    }
	</script>
	
	
</html>