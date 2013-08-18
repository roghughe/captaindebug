<!-- Use this for the 'form' tag   -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--  This is the JSTL tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="false"%>
<html lang="en">
<head>
<title>Match Report</title>
<link rel="stylesheet"
	href="<c:url value='/resources/blueprint/screen.css'/>" type="text/css"
	media="screen, projection" />
<link rel="stylesheet"
	href="<c:url value='/resources/blueprint/print.css'/>" type="text/css"
	media="print" />
<!--[if lt IE 8]>
    <link rel="stylesheet" href="<c:url value='/resources/blueprint/ie.css' />" type="text/css" media="screen, projection" />
  	<![endif]-->

<link rel="stylesheet"
	href="<c:url value='/resources/mystyle.css'/>" type="text/css"
	media="screen, projection" />

<script type="text/javascript"
	src="<c:url value='/resources/jquery-1.9.1.js' /> "></script>
<script type="text/javascript"
	src="<c:url value='/resources/polling.js' /> "></script>
	
<script type="text/javascript">

	$(document).ready(function() {
	
		var startUrl = "matchupdate/subscribe";
		var pollUrl = "matchupdate/simple";
		
		var poll = new Poll();
		poll.start(startUrl,pollUrl);
	});
	
</script>

</head>

<%@include file="body.jsp" %>


</html>
