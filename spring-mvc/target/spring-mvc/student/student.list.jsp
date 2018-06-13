<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%-- <tiles:insertDefinition name="studentTemplate">
	<tiles:putAttribute name="body">
		<h2>List of Students</h2> --%>
<%-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
<script>
	function view(int id) {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "json/" + id, true);
		xmlHttp.onload = function() {
			if (this.status != 200)
				return;
			console.log(this.responseText); 
		var student= JSON.parse(this.responseText)
			document.getElementById('content').innerHTML='Name: '+ student.name;
			var dialog = document.getElementById('viewStudent');
			dialog.show();
		};
		xmlHttp.send();
	}
</script>

</head>
<body>
	<table border="1">
		<tr>
			<td>id</td>
			<td>name</td>
			<td>age</td>
		</tr>
		<c:forEach items="${students}" var="student">
			<tr>
				<td>${student.id }</td>
				<td>${student.name }</td>
				<td>${student.age }</td>
				<td><a href="delete/${student.id }">delete</a></td>
				<td><a href="edit/${student.id }">edit</a></td>
				<td><a href="javascript:view(${student.id })">${student.name }</a></td>
			</tr>
		</c:forEach>
	</table>
	<dialog id="viewStudent" style="width:50%;border:1px dotted black;">
	<<!-- div id="content"></div> -->
	<button id="hide">Close</button>
	</dialog>

	<script>
	(function(){
		var dialog= document.getElementById('viewStudent');
		document.getElementById('hide').onclick = function(){
			dialog.close();
		};
	})();
	</script>
</body>
		</html>
	</tiles:putAttribute>
</tiles:insertDefinition> --%>
<tiles:insertDefinition name="studentTemplate">
	<tiles:putAttribute name="body">

		<h2>List of Students</h2>
		<script>
			function view(id) {
				var xmlHttp = new XMLHttpRequest();
				xmlHttp.open("GET", "json/" + id, true);
				xmlHttp.onload = function() {
					if (this.status != 200)
						return;
					console.log(this.responseText);
					var student = JSON.parse(this.responseText);
					document.getElementById('content').innerHTML = 'Name: '
							+ student.name;
					var dialog = document.getElementById('viewStudent');
					dialog.show();
				};
				xmlHttp.send();
			}
		</script>

		<table border="1">
			<form method="GET" action="search">
				<tr>
					<td colspan="4"><input type="text" name="q" size="30" /></td>
					<td><input type="submit" value="Search"></td>
				</tr>
			</form>
			<tr>
				<td>id</td>
				<td>name</td>
				<td>age</td>
			</tr>
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.id }</td>
					<td>${student.name }</td>
					<td>${student.age }</td>
					<td><a href="delete/${student.id }">delete</a></td>
					<td><a href="edit/${student.id }">edit</a></td>
					<td><a href="javascript:view(${student.id })">${student.name }</a></td>
				</tr>
			</c:forEach>

			<dialog id="viewStudent" style="width:50%;border:1px dotted black;">
			<div id="content"></div>
			<button id="hide">Close</button>
			</dialog>

			<script type="text/javascript">
				(function() {
					var dialog = document.getElementById('viewStudent');
					document.getElementById('hide').onclick = function() {
						dialog.close();
					};
				})();
			</script>
		</table>

	</tiles:putAttribute>
</tiles:insertDefinition>