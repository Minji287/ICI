<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/title.css">
<link rel="stylesheet" href="/resources/css/content.css">
<script type="text/javascript" src="/resources/js/join.js"></script>
<title>회원 정보 확인</title>
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
									<tr>
										<td colspan="2" class="content_text">${memberDto.mid }님 정보 수정이 완료되었습니다.</td>
									</tr>
									<tr>
										<td class="content_text">아 이 디 : </td>
										<td><input class="inputbox01" type="text" name="mid" value="${memberDto.mid }" readonly="readonly"></td>
									</tr>
									<tr>
										<td class="content_text">비밀번호 : </td>
										<td><input class="inputbox01" type="text" name="mpw" value="${memberDto.mpw }" readonly="readonly"></td>
									</tr>
									<tr>
										<td class="content_text">이&nbsp;&nbsp;&nbsp;&nbsp;름 : </td>
										<td><input class="inputbox01" type="text" name="mname" value="${memberDto.mname }" readonly="readonly"></td>
									</tr>
									<tr>
										<td class="content_text">이 메 일 : </td>
										<td><input class="inputbox01" type="text" name="memail" value="${memberDto.memail }" readonly="readonly"></td>
									</tr>
									<tr>
										<td class="content_text">가 입 일 : </td>
										<td class="content_text">${memberDto.mdate }</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<input class="content_btn01" type="button" value="정보수정" onclick="script:window.location.href='modify'">&nbsp;&nbsp;
											<input class="content_btn01" type="button" value="홈" onclick="script:window.location.href='index'">
										</td>
									</tr>
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