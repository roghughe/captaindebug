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

<script type="text/javascript"
	src="<c:url value='/resources/jquery-1.9.1.js' /> "></script>
<style>
body {
	background-color: #cccccc;
}

.update {
	color: #BD6464;
}
</style>

<script type="text/javascript">

	var allow = true;

	$(document).ready(function() {
	
		var url = "matchupdate/begin";
		
		if (request) {
			request.abort();	// abort any pending request
		}
		
		// fire off the request to MatchUpdateController
		var request = $.ajax({
			url : url,
			type : "get",
		});
		
		// This is jQuery 1.8+
		// callback handler that will be called on success
		request.done(function(reply) {
			
			console.log("Game on..." + reply);
			setInterval(function(){
				if(allow === true) {
					allow = false;
					poll();
				}
			},500);
		});
	});

	
	function poll() {
		
		var url = "matchupdate/deferred";
		
		console.log("Okay let's go...");
																	
		if (request) {
			request.abort();	// abort any pending request
		}
		
		// fire off the request to MatchUpdateController
		var request = $.ajax({
			url : url,
			type : "get",
		});

		// This is jQuery 1.8+
		// callback handler that will be called on success
		request.done(function(message) {
			console.log("Received a message");
			
			var update = getUpdate(message);
			$(update).insertAfter('#first_row');
		});
		
		function getUpdate(message) {

			var update = "<div class='span-4  prepend-2'>"
						+ "<p class='update'>Time:</p>"
						+ "</div>"
						+ "<div class='span-3 border'>"
						+ "<p id='time' class='update'>"
						+ message.matchTime
						+ "</p>"
						+ "</div>"
						+ "<div class='span-13 append-2 last' id='update-div'>"
						+ "<p id='message' class='update'>"
						+ message.messageText
						+ "</p>"
						+ "</div>";
			return update;
		};
		

		// callback handler that will be called on failure
		request.fail(function(jqXHR, textStatus, errorThrown) {
			// log the error to the console
			console.log("The following error occured: " + textStatus, errorThrown);
		});

		// callback handler that will be called regardless if the request failed or succeeded
		request.always(function() {
			allow = true;
		});
	};

	
</script>

</head>
<body>
	<div class="container">
		<div id='first_row' class="span-22 prepend-2">
			<div style='float: left;'><img src="<c:url value='/resources/images/classic.png'/>"/></div>
			<div>
			<h2 class="update">Match Updates</h2>
			</div>
			<hr />
		</div>
		<%-- Place updates in here --%>
		<div class="span-4  prepend-2">
			<p class="update">Time:</p>
		</div>
		<div class="span-3 border">
			<p id='time' class="update"></p>
		</div>
		<div class="span-13 append-2 last" id='update-div'>
			<p id='message' class="update">The game has not yet started</p>
		</div>
	</div>
</body>
</html>
