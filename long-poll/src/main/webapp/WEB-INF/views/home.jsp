<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Live Match Updates
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="simplematchupdate">Stoke City vs Arsenal - Using the a simple (don't do this) technique</a><br/>
<a href="deferredmatchupdate">Stoke City vs Arsenal - Using the DeferredRequest technique</a>

</body>
</html>
