<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.jsp.thumbnail.model.vo.*" %>
<%
	// 사진 게시글 리스트 가져오기
	ArrayList<Thumbnail> list
	 = (ArrayList<Thumbnail>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시판</title>
<style>
	.outer{
		width : 1000px;
		height : auto;
		background : black;
		color : white;
		margin-left : auto;
		margin-right : auto;
		margin-top : 50px;
	}
	.thumbnailArea {
		width:760px;
		height:auto;
		margin-left:auto;
		margin-right:auto;
	}
	.searchArea {
		width:420px;
		margin-left:auto;
		margin-right:auto;
	}
	.thumb-list {
		width:220px;
		border:1px solid white;
		display:inline-block;
		margin:10px;
		align:center;
	}
	.thumb-list:hover {
		opacity:0.8;
		cursor:pointer;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
		
		<div class="outer">
			<br>
			<h2 align="center">사진 게시판</h2>
			
			<div class="thumbnailArea">
			<% for(Thumbnail thumb : list) { %>
				<div class="thumb-list" align="center">
					<div>
						<input type="hidden" value="<%= thumb.getBno() %>">
						<img src="/myWeb/resources/thumbnailUploadFiles/<%= thumb.getBoardfile() %>" 
						     width="200px" height="150px">
					</div>
					
					<p>No. <%=thumb.getBno() + " " + thumb.getBtitle() %><br>
					조회수	: <%= thumb.getBcount() %>
					</p>
				</div>
			<% } %>
			</div>
			<script>
				$(function(){
					$('.thumb-list').click(function(){
							var bno = $(this).children().children().eq(0).val();
							location.href = "<%=request.getContextPath()%>/selectOne.tn?bno=" + bno;
					});
				});
			</script>
		
		
		<div class="searchArea">
			<select id="searchCondition" name="searchCondition">
				<option value="all">--</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search"/>
			<button type="submit">검색하기</button>
			<% if(m != null) { %>
				<button onclick="location.href='views/thumbnail/thumbnailInsertForm.jsp'">
				작성하기
				</button>
			<% } %>
			
		</div>
		<br><br>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>



















