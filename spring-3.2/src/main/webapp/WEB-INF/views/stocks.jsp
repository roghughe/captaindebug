<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Matrix Variables</title>
<link rel="stylesheet"
	href="<c:url value='/resources/blueprint/screen.css'/>" type="text/css"
	media="screen, projection" />
<link rel="stylesheet"
	href="<c:url value='/resources/blueprint/print.css'/>" type="text/css"
	media="print" />
<!--[if lt IE 8]>
    <link rel="stylesheet" href="<c:url value='/resources/blueprint/ie.css' />" type="text/css" media="screen, projection" />
  	<![endif]-->
</head>
<body>
	<div class="container">
		<div class="span-16 append-8">
			<h2>Your Stock Portfolio</h2>
			<hr />
		</div>
		<div class="span-4 border">
			<p>Stock</p>
		</div>
		<div class="span-4 border">
			<p>Price</p>
		</div>
		<div class="span-4 border">
			<p>Change</p>
		</div>
		<div class="span-4 append-8 last">
			<p>Var %</p>
		</div>
		<div class="span-16 append-8">
			<hr />
		</div>
		<c:forEach items="${stocks}" var="stocklist">
			<c:forEach items="${stocklist}" var="item" varStatus="status">
				<div class="span-4  ${not status.last ? 'border' : 'append-8 last'}">
					<p>
						<c:out value="${item}" />
					<p>
				</div>
			</c:forEach>
		</c:forEach>

		<div class="span-16 append-8">
			<hr />
		</div>

		<c:if test="${not empty accountDetails}">
			<div class="span-16 append-8">
				<h2>Account Details</h2>
				<hr />
			</div>
			<c:forEach items="${accountDetails}" var="accountDetails">
				<c:forEach items="${accountDetails}" var="item" varStatus="status">
					<div
						class="span-4  ${not status.last ? 'border' : 'append-16 last'}">
						<p>
							<c:out value="${item}" />
						<p>
					</div>
				</c:forEach>
			</c:forEach>
			<div class="span-16 append-8">
				<hr />
			</div>
		</c:if>
	</div>
</body>
</html>
