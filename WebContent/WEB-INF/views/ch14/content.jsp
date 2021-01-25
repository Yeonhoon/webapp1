<%@ page  contentType="text/html; charset=UTF-8"%>
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
							<h5>연결 테스트</h5>
							<div>
								<a class = "btn btn-success btn-sm" href="javascript:conntest()">연결</a>
								<script>
									function conntest(){
									    $.ajax({
									        url:"conntest",
									        method:"get",
									        success: function(data){
									            $("#conspan").html(data);
									        }
									    });
									}
								</script>
								<span id="conspan" style="color:red;"></span>
							</div>
						</div>
						
						<div class="sector">
							<h5>JSON 응답 받기</h5>
							<div>
								<a class = "btn btn-success btn-sm" href="javascript:jsonresponse1()">객체 {  } 받기</a>
								<script>
									function jsonresponse1(){
									    $.ajax({
									        url:"jsonresponse1",
									        method:"get",
									        success: function(data){
									            console.log(data);
									            $("#name").html(data.name);
									            $("#age").html(data.age);
									            $("#carkind").html(data.car.kind);
									            $("#carcolor").html(data.car.color);  <%--html: 완전히 새 내용을 넣음--%>
									            
									            for(var h of data.car.color) {
									                $("#hobby").append(h + ", ");
									            }
									        }
									    });
									}
								</script>
								<div id="result2">
									<div id="name"></div>
									<div id="age"></div>
									<div id="carkind"></div>
									<div id="carcolor"></div>
									<div id="hobby"></div>
								</div>
							</div>
							<a class = "btn btn-success btn-sm" href="javascript:jsonresponse2()">배열 [  ] 받기</a>
							<script>
								function jsonresponse2() {
								    $.ajax({
								        url: "jsonresponse2",
								        success: function(data){
								            console.log(data);
								            for(var i=0; i<data.length; i++){
								                var board = data[i];
								                $("#result3 tbody").append("<tr>");
								                $("#result3 tbody").append("<td>" + board.bno + "</td>");
								                $("#result3 tbody").append("<td>" + board.btitle + "</td>");
								                $("#result3 tbody").append("<td>" + board.bwriter + "</td>");
								                $("#result3 tbody").append("</tr>");
								            }
								        }
								    })
								}	
							</script>
							<div id="result3">
								<table class="table">
									<thead>
										<tr>
											<td>번호</td>
											<td>제목</td>
											<td>글쓴이</td>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
						
						<div class="sector">
							<h5>PK검색</h5>
							<div>
								<a class = "btn btn-success btn-sm" href="javascript:employeeInfo(100)">사번 100인 사원의 정보 가져오기</a>
								<a class = "btn btn-success btn-sm" href="javascript:employeeInfo(101)">사번 101인 사원의 정보 가져오기</a>
								<script>
								function employeeInfo(eid){
									$.ajax({
									    url:"employeeinfo",
									    data: {employee_id: eid},
									    success: function(data){ 
									        console.log(data);
									        $("#eno").html(data.employee_id);
									        $("#first").html(data.first_name);
									        $("#last").html(data.last_name);
							        	}
									})
								}
								</script>
								<div id="result4">
									사번: <span id="eno"></span> <br />
									이름: <span id="first"> </span><br />
									성: <span id="last"></span><br />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</body>
	
</html>