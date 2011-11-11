<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>Simple Address App</h1>
<h2>These are the Results of your Search</h2>
<p>
Address id: <c:out value="${address.id}" /><br>
Street: <c:out value="${address.street}" /><br>
Town: <c:out value="${address.town}" /><br>
Post Code: <c:out value="${address.postCode}" /><br>
Country: <c:out value="${address.country}" /><br>
</p>
<br>
<a href="find?id=101">Search Again with Address with ID 9</a>
</body>
</html>
