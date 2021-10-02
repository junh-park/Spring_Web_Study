<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<html>
<style>
label.error {
	color: red;
}

input.error {
	background-color: #ffcccc;
}

div.errors {
	background-color: #ffcccc;
	border: 2px solid red;
}
</style>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources.style.css"/>">

</head>
<body>
	<h1>Register</h1>

	<form method="POST" th:object="${spitter}">
		<div class="errors" th:if="${#fields.hasErrors('*')}">
			<ul>
				<li th:each="err : ${#fields.errors('*')}" th:text="${err}">Input
					is incorrect</li>
			</ul>
		</div>
		<label th:class="${#fields.hasErrors('firstName')}? 'error'">
			First Name</label>: 
			<input type="text" th:field="*{firstName}"	th:class="${#fields.hasErrors('firstName')}? 'error'" /><br /> 
		<label	th:class="${#fields.hasErrors('lastName')}? 'error'"> 
			Last Name</label>: 
			<input type="text" th:field="*{lastName}" th:class="${#fields.hasErrors('lastName')}? 'error'" /><br /> 
		<label th:class="${#fields.hasErrors('email')}? 'error'"> 
			Email</label>: 
			<input type="text" th:field="*{email}" th:class="${#fields.hasErrors('email')}? 'error'" /><br /> 
		<label th:class="${#fields.hasErrors('username')}? 'error'">
			Username</label>: 
			<input type="text" th:field="*{username}" th:class="${#fields.hasErrors('username')}? 'error'" /><br /> 
		<label th:class="${#fields.hasErrors('password')}? 'error'">
			Password</label>: 
			<input type="password" th:field="*{password}" th:class="${#fields.hasErrors('password')}? 'error'" /><br /> 
			<input type="submit" value="Register" />
	</form>


	<sf:form method="post" commandName="spitter">
		<sf:label path="firstName" cssErrorClass="">First Name</sf:label>:
			 <sf:input path="firstName" cssErrorClass="error" />
		<br />
		<sf:label path="lastName" cssErrorClass="">Last Name</sf:label>:
			 <sf:input path="lastName" cssErrorClass="error" />
		<br />
		<sf:label path="email" cssErrorClass="">Email</sf:label>:
			 <sf:input path="email" cssErrorClass="error" />
		<br />
		<sf:label path="username" cssErrorClass="">Username</sf:label>:
			 <sf:input path="username" cssErrorClass="error" />
		<br />
		<sf:label path="password" cssErrorClass="">Password</sf:label>:
			 <sf:password path="password" cssErrorClass="error" />
		<br />
		<input type="submit" value="Register">
	</sf:form>
</body>
</html>