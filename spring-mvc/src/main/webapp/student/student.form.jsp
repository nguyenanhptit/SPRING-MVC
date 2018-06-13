<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2>Please Input Student Information</h2>
	<form:form method="POST" action="add" modelAttribute="command">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" type="text" /> <form:errors
						path="name" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><form:input path="age" type="number" /> <form:errors
						path="age" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<c:if test="${id!=null }">
		<h1>Please upload a image</h1>
		<form method="post" action="../avatar/save"
			enctype="multipart/form-data">
			<input type="hidden" name="id" value="${id }" /> 
			<input type="file" name="file" /> 
			<input type="submit" value="Upload" />
		</form>
	</c:if>
</body>
</html>
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2>Please Input Student Information</h2>
	<form method="POST" action="save">
		<table>
			<tr>
				<td>Name:</td>
				<td><input name="name" type="text" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input name="age" type="text" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

</body>
</html> -->