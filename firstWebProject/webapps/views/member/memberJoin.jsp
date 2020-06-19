<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 폼</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<h1>회원 가입 폼</h1>
	
	<form action="/jdbc/mInsert.do" method="post">
		<table>			
			<tr>
				<td>ID : </td>
				<td><input type="text" name="userId"/></td>
				<td><button type="button" id="dupBtn">아이디 중복 확인</button></td>
			</tr>
			<tr>
				<td>PWD : </td>
				<td><input type="password" name="userPwd" id="userPwd"/></td>
				<td></td>
			</tr>
			<tr>
				<td>PWD 확인 : </td>
				<td><input type="password" name="userPwd2" id="userPwd2"/></td>
				<td></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td><input type="text" name="userName"/></td>
				<td></td>
			</tr>
			<tr>
				<td>성별 : </td>
				<td>
					<input type="radio" name="gender" value="M" /> 남성
					<input type="radio" name="gender" value="F" /> 여성
				</td>
				<td></td>
			</tr>
			<tr>
				<td>나이 : </td>
				<td><input type="number" name="age" min="10" max="100" /></td>
				<td></td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td><input type="email" name="email"/></td>
				<td></td>
			</tr>
			<tr>
				<td>연락처 : </td>
				<td><input type="tel" name="phone"/></td>
				<td></td>
			</tr>
			<tr>
				<td>주소 : </td>
				<td><input type="text" name="address"/></td>
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
					<input type="submit" value="회원 가입"/>
					&nbsp; &nbsp;
					<input type="reset" value="가입 취소" />
				</td>
			</tr>
		</table>
	</form>
	
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>