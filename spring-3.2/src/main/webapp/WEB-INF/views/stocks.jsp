<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Matrix Variables</title>
	<link rel="stylesheet" href="<c:url value='/resources/blueprint/screen.css'/>" type="text/css" media="screen, projection"/>
  	<link rel="stylesheet" href="<c:url value='/resources/blueprint/print.css'/>" type="text/css" media="print" /> 
  	<!--[if lt IE 8]>
    <link rel="stylesheet" href="<c:url value='/resources/blueprint/ie.css' />" type="text/css" media="screen, projection" />
  	<![endif]-->    
</head>
<body>
    <div class="container showgrid">
	  <h2>Your Stock Portfolio</h2>
      <div class="span-8">
        <p>&nbsp;&nbsp;&nbsp;&nbsp;Stock&nbsp;&nbsp;&nbsp;&nbsp;Price&nbsp;&nbsp;&nbsp;&nbsp;Change&nbsp;&nbsp;&nbsp;&nbsp;Var %</p>
      </div>
      <div class="span-8">
      	<c:forEach items="${stocks}" var="stock">
   		<p><c:out value="${stock}"/><p>
	    </c:forEach>
        <p></p>
      </div>
	</div>
</body>
</html>
