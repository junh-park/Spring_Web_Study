<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<title>Spitter</title>
<link rel="stylesheet" type="text/css" th:href="@{/resources/style.css}"></link>
</head>
<body onload='document.f.username.focus();'>
	<div id="header" th:include="page :: header"></div>
	<div id="content">
		<form name='f' th:action='@{/login}' method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' value='' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Login" /></td>
				</tr>
			</table>
			<input id="remember_me" name="remember-me" type="checkbox" /> 
			<label for="remember_me" class="inline">Remember me</label>
		</form>
	</div>
	<div id="footer" th:include="page :: copy"></div>
</body>
</html>