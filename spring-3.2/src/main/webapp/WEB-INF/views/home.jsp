<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Spring 3.2 Demo Home Page 
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h2>The Rudimentary Menu</h2>
<ul>
<li><a href="userdetails">Get User Name (and throw an IOException)</a></li>
<li><a href="useraddress">Get User Address (and throw an IOException)</a></li>
<li></li>
</ul>

</body>
</html>
