<!-- Use this for the 'form' tag   -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--  This is the JSTL tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<c:url value='/resources/blueprint/screen.css'/>" type="text/css" media="screen, projection"/>
  	<link rel="stylesheet" href="<c:url value='/resources/blueprint/print.css'/>" type="text/css" media="print" /> 
  	<!--[if lt IE 8]>
    <link rel="stylesheet" href="<c:url value='/resources/blueprint/ie.css' />" type="text/css" media="screen, projection" />
  	<![endif]-->    
  
	<link rel="stylesheet" href="<c:url value='/resources/style.css'/>" type="text/css" media="screen, projection"/>
	<script type="text/javascript" src="<c:url value='/resources/jquery-1.9.1.js' /> "></script>
	<script type="text/javascript" src="<c:url value='/resources/shopping.js' /> "></script>
</head>
<body>
	<div id="mask" style="display: none;"></div>
	<div id="popup" style="display: none;">
		<div class="container" id="insertHere">
			<div class="span-1 append-23 last">
				<p><a href="#" onclick="closePopup();">Close</a></p>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="span-17 append-7">
			<h2>Shopping Page</h2>
			<hr />
		</div>
		<div class="span-4 border">
			<p>Item</p>
		</div>
		<div class="span-8 border">
			<p>Description</p>
		</div>
		<div class="span-4 border">
			<p>Price</p>
		</div>
		<div class="span-4 append-4 last">
			<p>&nbsp;</p>
		</div>
		<div class="span-17 append-7">
			<hr />
		</div>
		<form:form modelAttribute="userSelections" action="confirm" method="post">
			<c:forEach items="${items}" var="item">
				<div class="span-4 border">
					<p><c:out value="${item.name}" /></p>
				</div>
				<div class="span-8 border">
					<p><c:out value="${item.description}" /></p>
				</div>
				<div class="span-4 border">
					<p>&#163;<c:out value="${item.price}" /></p>
				</div>
				<div class="span-4 append-4 last">
					<p><form:checkbox value="${item.id}" path="selection"/></p>
				</div>
			</c:forEach>
			<div class="prepend-12 span-4 append-12">
				<p><input class="command"
									type="submit" name="action"
									value="Confirm Purchase"
									accesskey="A" /></p>
			</div>
		</form:form>
		<div class="span-17 append-7">
			<hr />
		</div>
	</div>
</body>
</html>



