<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.jsp.member.model.vo.*" %>
<%
	Member m = (Member)session.getAttribute("member");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="/jdbc/resources/js/jquery-3.5.1.min.js"></script>
</head>
<body>

	<%@ include file="/views/common/header.jsp" %>	
	
	<h1>회원 정보 수정 : <%= m.getUserName() %></h1>
				
	<form action="/jdbc/mUpdate.do" method="post" id="mUpdate">
	
			<table>
			<tr>
				<td>ID : </td>
				<td><%= m.getUserId() %></td>
			</tr>
			<tr>
				<td>변경할 PWD : </td>
				<td><input type="password" name="userPwd" id="userPwd"/></td>
				<td></td>
			</tr>
			<tr>
				<td>변경할 PWD 확인 : </td>
				<td><input type="password" name="userPwd2" id="userPwd2"/></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3" id="result"></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td><%= m.getUserName() %></td>
			</tr>
			<tr>
				<td>성별 : </td>
				<td>
					<input type="radio" name="gender" value="M"/> 남성
					<input type="radio" name="gender" value="F"/> 여성
				</td>
				<td></td>
			</tr>
			<tr>
				<td>나이 : </td>
				<td><input type="number" name="age" 
				           min="10" max="100" value="<%= m.getAge() %>" /></td>
				<td></td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td><input type="email" name="email" value="<%=m.getEmail()%>"/></td>
				<td></td>
			</tr>
			<tr>
				<td>연락처 : </td>
				<td><input type="text" name="phone" value="<%= m.getPhone()%>"/></td>
				<td></td>
			</tr>
			<tr>
				<td>주소 : </td>
				<td><input type="text" name="address" value="<%=m.getAddress()%>"/></td>
				<td></td>
			</tr>
			<tr>
				<td>취미 : </td>
				<td>
					<input type="checkbox" name="hobby" value="JavaScript" />JavaScript
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="Java공부" />Java공부
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="SQL공부"/>SQL공부
					<br>
					<input type="checkbox" name="hobby" value="CSS3공부"/>CSS3공부
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="Oracle공부"/>Oracle공부
					&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="오카리나연주"/>오카리나연주
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan = "2">
					<input type="submit" value="회원 정보 수정"/>
					&nbsp; &nbsp;
					<input type="button" onclick="memberDel();" value="회원 탈퇴" />
				</td>
			</tr>
		</table>
	
	</form>
	
	<%@ include file="/views/common/footer.jsp" %>
	<script>
		/*
			일반적으로 script 작성 구간은 어디가 되든 상관 없으나
			html과 css가 웹 문서로 로드(읽혀지는) 되는 시점을 고려하면
			head와 body가 모두 읽혀진 다음에 script 를 실행할 수 있도록
			최하단에 적는 것을 권장한다. 이렇게 최 하단에 스크립트를 적으면
			웹 문서의 로딩 속도를 줄일 수 있다. 
		*/
		
		$(function(){
			// 성별 radio 선택하기
			$('input:radio').each(function(){
				if($(this).val() == '<%= m.getGender() %>'){
					$(this).prop('checked', true);
				} else $(this).prop('checked', false);
				
				$(this).prop('disabled', true);
			});
			
			// 취미 checkbox
			var array = '<%= m.getHobby() %>'. split(', ');
			console.log(array);
			
			$('input:checkbox').each(function(){
				if($.inArray($(this).val(), array) > -1 ){
					$(this).prop('checked', true);
				}
			});
			
			// 비밀번호 변경 및 변경 확인 유효성 체크하기
			$('#mUpdate').submit(function( event ){
				// 비밀번호가 일치하지 않을 때
				if($( '#userPwd').val() != $('#userPwd2').val() ){
					$('#result').text('비밀번호가 확인값과 일치하지 않습니다.').show().fadeOut(1000);
				} else if ($('#userPwd').val() == ""){
					$('#result').text('비밀번호를 입력하세요.')
									 .show().fadeOut(1000);
				} else {
					return; // 올바른 종료
				}
				
				event.preventDefault(); // submit 동작을 취소 !
			});
			
		});
		
		// '회원 탈퇴'를 누를 경우 mDelete.do 서블릿으로 이동시키기
		function memberDel() {
			location.href = '/jdbc/mDelete.do';
			
		}

	</script>

</body>
</html>