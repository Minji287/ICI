<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/title.css">
<link rel="stylesheet" href="/resources/css/content.css">
<title>로그인</title>
</head>
<body>
	<%@ include file="include/header.jsp" %>
	<center>
	<table border="0" cellspacing="0" cellpadding="20" width="75%">
		<tr>
			<td class="titlebox">
				<span class="title01">DEVELOPER MINJI'S PROFILE</span>
			</td>
		</tr>
		<tr>
			<td class="titlebox">
				<span class="title02">I'm Minji Kim, a developer who wants a development job. Please call me back.</span>
			</td>
		</tr>
		<tr>
			<td>
			<center>
				<table border="0" cellspacing="0" cellpadding="10" width="80%">
					<tr>
						<td class="contentbox">
							<center>
								<table border="0" cellspacing="0" cellpadding="10">
									<form accept="joinOk" method="post">
										<tr>
											<td class="content_text">아 이 디 : </td>
											<td><input class="inputbox01" type="text" name="mid"></td>
										</tr>
										<tr>
											<td class="content_text">비밀번호 : </td>
											<td><input class="inputbox01" type="text" name="mpw"></td>
										</tr>
										<tr>
											<td class="content_text">비밀번호확인 : </td>
											<td><input class="inputbox01" type="text" name="mpw_check"></td>
										</tr>
										<tr>
											<td class="content_text">이&nbsp;&nbsp;&nbsp;&nbsp;름 : </td>
											<td><input class="inputbox01" type="text" name="mname"></td>
										</tr>
										<tr>
											<td class="content_text">이 메 일 : </td>
											<td><input class="inputbox01" type="text" name="memail"></td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<input class="content_btn01" type="submit" value="가입완료">&nbsp;&nbsp;
												<input class="content_btn01" type="button" value="로그인" onclick="script:window.location.href='login'">
											</td>
										</tr>
									</form>
								</table>
							</center>
						</td>
					</tr>
				</table>
				</center>
			</td>
		</tr>
	</table>
	</center>
	<%@ include file="include/footer.jsp" %>
</body>
</html>