<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Some Exceptional Exceptions  
</h1>

<P>Well <c:out value="${user.firstName}" />, there seems to have been an <c:out value="${name}" /> and 
we've lost all your important details. Hard luck, please try again. </P>
<p>Not  <c:out value="${user.firstName}" />  <c:out value="${user.surname}" />, please log out...</p>

</body>
</html>
