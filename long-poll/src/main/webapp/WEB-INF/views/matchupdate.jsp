<!-- Use this for the 'form' tag   -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--  This is the JSTL tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html lang="en">
  <head>
    <title>Match Report</title>
	<link rel="stylesheet" href="<c:url value='/resources/blueprint/screen.css'/>" type="text/css" media="screen, projection"/>
  	<link rel="stylesheet" href="<c:url value='/resources/blueprint/print.css'/>" type="text/css" media="print" /> 
  	<!--[if lt IE 8]>
    <link rel="stylesheet" href="<c:url value='/resources/blueprint/ie.css' />" type="text/css" media="screen, projection" />
  	<![endif]-->    
  
	<script type="text/javascript" src="<c:url value='/resources/jquery-1.9.1.js' /> "></script>
	<style>
		body
		{
			background-image:url("<c:url value='/resources/images/Stoke_City_1972.jpg'/>");
			background-repeat:no-repeat;
			background-color:#cccccc;	
		}	
		.update	{ color:#BD6464; }
	</style>
	
	<script type="text/javascript">
	$(document).ready(function() {
		console.log("Okay let's go...");
							
		$('.tmp').remove();		// Remove any divs added by the last request
										
							if (request) {
								request.abort();	// abort any pending request
							}
							
							// fire off the request to OrderController
							var request = $.ajax({
								url : "http://localhost:8080/store/confirm",
								type : "post",
								data : serializedData
							});

							// This is jQuery 1.8+
							// callback handler that will be called on success
							request.done(function(data) {

								console.log("Resulting UUID: " + data.uuid);
								$("<div class='span-16 append-8 tmp'><p>You have confirmed the following purchases:</p></div>")
								.appendTo('#insertHere');

								var items = data.items;
								// Add the data from the request as <div>s to the pop up <div>
								for ( var i = 0; i < items.length; i++) {
									var item = items[i];

									var newDiv = "<div class='span-4  tmp'><p>" + item.name + "</p></div>";
									$(newDiv).appendTo('#insertHere');

									newDiv = "<div class='span-6 tmp'><p>" + item.description + "</p></div>";
									$(newDiv).appendTo('#insertHere');

									newDiv = "<div class='span-4 append-10 last tmp'><p>&#163;" + item.price + "</p></div>";
									$(newDiv).appendTo('#insertHere');

									console.log("Item: " + item.name + "  Description: " + item.description + " Price: "
											+ item.price);
								}

							});

							// callback handler that will be called on failure
							request.fail(function(jqXHR, textStatus, errorThrown) {
								// log the error to the console
								alert("The following error occured: " + textStatus, errorThrown);
							});

							// callback handler that will be called regardless if the request failed or succeeded
							request.always(function() {
								$inputs.prop("disabled", false);	// re-enable the inputs
							});

							event.preventDefault(); 	// prevent default posting of form

							showPopup();
						});
			});	
	
	
	</script>
  </head>
<body>
	<div class="container">
		<div class="span-14 prepend-10">
			<h2 class="update">Match Updates</h2>
			<hr />
		</div>
		<div class="span-4  prepend-10">
			<p class="update">Latest Update:</p>
		</div>
		<div class="span-3 border">
			<p id='time'  class="tmp update">00:00</p>
		</div>
		<div class="span-7 last" id='update-div'>
			<p id='message'  class="tmp update">The game has not started yet...</p>
		</div>
	</div>	
</body>
</html>
