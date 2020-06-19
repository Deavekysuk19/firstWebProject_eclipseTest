<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시글 작성하기</title>
<style>
	.outer {
		width:1000px;
		height:650px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		border:1px solid white;
	}
	.insertArea {
		width:500px;
		height:450px;
		margin-left:auto;
		margin-right:auto;
	}
	.btnArea {
		width:150px;
		margin-left:auto;
		margin-right:auto;
	}
	#titleImgArea {
		width:350px;
		height:200px;
		border:2px dashed darkgray;
		text-align:center;
		display:table-cell;
		vertical-align:middle;
	}
	#titleImgArea:hover, #contentImgArea1:hover, 
	#contentImgArea2:hover, #contentImgArea3:hover {
		cursor:pointer;
	}
	#contentImgArea1, #contentImgArea2, #contentImgArea3 {
		width:150px;
		height:100px;
		border:2px dashed darkgray;
		text-align:center;
		display:table-cell;
		vertical-align:middle;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<% if( m != null) { %>
	<!-- 회원일 경우 -->
	<div class="outer">
	
		<br>
		<h2 align="center">사진 게시판 작성 화면</h2>
		<form action="<%= request.getContextPath() %>/tInsert.tn"
		      method="post" enctype="multipart/form-data">
		      
		      <div class="insertArea">
		      	
		      	<input type="hidden" name="userId" value="<%= m.getUserId() %>">
		      	<table align="center">
		      		<tr>
						<td width="100px">제목</td>
						<td colspan="3"><input type="text" size="45" name="title"></td>
					</tr>
					<tr>
						<td>대표 이미지</td>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="350" height="200">
							</div>
						</td>
					</tr>
					<tr>
						<td>내용 사진</td>
						<td>
							<div id="contentImgArea1">
								<img id="contentImg1" width="120" height="100">
							</div>
						</td>
						<td>
							<div id="contentImgArea2">
								<img id="contentImg2" width="120" height="100">
							</div>
						</td>
						<td>
							<div id="contentImgArea3">
								<img id="contentImg3" width="120" height="100">
							</div>
						</td>
					</tr>
					<tr>
						<td width="100px">사진 메모</td>
						<td colspan="3">
							<textarea name="content" rows="5" cols="50" style="resize:none;"></textarea>
						</td>
					</tr>
		      	</table>
		      	<div class="fileArea" id="fileArea">
		      		<input type="file" id="thumbnailImg1"
		      				name="thumbnailImg1" onchange="loadImg(this, 1);" />
		      				
		      		<input type="file" id="thumbnailImg2"
		      				name="thumbnailImg2" onchange="loadImg(this, 2);" />
		      				
		      		<input type="file" id="thumbnailImg3"
		      				name="thumbnailImg3" onchange="loadImg(this, 3);" />
		      				
		      		<input type="file" id="thumbnailImg4"
		      				name="thumbnailImg4" onchange="loadImg(this, 4);" />
		      	</div>
		      </div>
		      
		      <br>
		      
		      <div class="btnArea">
		      	<button type="reset">취소하기</button>
		      	<button type="submit">작성완료</button>
		      </div>
		</form>
		
		<script>
			// 사진 게시판 미리보기 기능 지원 스크립트
			$(function(){
				$('#fileArea').hide();
				
				$('#titleImgArea').click(() => {
					$('#thumbnailImg1').click();
				});
				
				$('#contentImgArea1').click(() => {
					$('#thumbnailImg2').click();
				});
				
				$('#contentImgArea2').click(() => {
					$('#thumbnailImg3').click();
				});

				$('#contentImgArea3').click(() => {
					$('#thumbnailImg4').click();
				});
			});
			
			function loadImg(value, num){
				
				if(value.files && value.files[0])  {
					
					var reader = new FileReader();
					
					reader.onload = function(e){
						
						switch(num) {
						case 1 : $('#titleImg').attr('src', e.target.result);
							break;
						case 2 : $('#contentImg1').attr('src', e.target.result);
							break;
						case 3 : $('#contentImg2').attr('src', e.target.result);
							break;
						case 4 : $('#contentImg3').attr('src', e.target.result);
							break;
						}
					}
					reader.readAsDataURL(value.files[0]);
				}
			}
		</script>
		
	</div>
	<% } else {
		// 비회원일 경우
		request.setAttribute("msg", "회원만 가능한 서비스입니다.");
		request.getRequestDispatcher("../common/errorPage.jsp")
		.forward(request, response);
	
	} %>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>








