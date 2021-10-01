<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
		<link rel="stylesheet" 
		      type="text/css" 
		      href="<c:url value="/resources.style.css"/>" >
		
	</head>
	<body>
		<h1>Register</h1>
		
		<sf:form method="post" commandName="spitter">
		  <sf:label path="firstName" cssErrorClass="">First Name</sf:label>:
			 <sf:input path="firstName" cssErrorClass="error"/><br/>
		  <sf:label path="lastName" cssErrorClass="">Last Name</sf:label>:
			 <sf:input path="lastName" cssErrorClass="error"/><br/>
		  <sf:label path="email" cssErrorClass="">Email</sf:label>:
			 <sf:input path="email" cssErrorClass="error"/><br/>
		  <sf:label path="username" cssErrorClass="">Username</sf:label>:
			 <sf:input path="username" cssErrorClass="error"/><br/>
		  <sf:label path="password" cssErrorClass="">Password</sf:label>:
			 <sf:password path="password" cssErrorClass="error"/><br/>
		  <input type="submit" value="Register">
		</sf:form>
	</body>
</html>