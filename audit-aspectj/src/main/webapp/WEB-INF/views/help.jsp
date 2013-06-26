<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Help</title>
</head>
<body>
<h1>
	HELP!
</h1>

<P>  This is the help you requested: ${helpText} </P>
<p>
<img src="<c:url value='/resources/images/the_beatles_help__movie_image__4_.jpg'/>" />
</p>
</body>
</html>
