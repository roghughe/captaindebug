$(document).ready(function() {
	console.log("Okay let's go...");
});

$(document).ready(
		function() {
			$('form').submit(
					function() {
						
						$('.tmp').remove();		// Remove any divs added by the last request
									
						if (request) {
							request.abort();	// abort any pending request
						}
						
						var $form = $(this);
						var $inputs = $form.find("input");
						// serialize the data in the form ready for output
						var serializedData = $form.serialize();

						// disable the inputs for the duration of the AJAX	 request
						$inputs.prop("disabled", true);

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

function showPopup() {
	$('body').css('overflow', 'hidden');
	$('#popup').fadeIn('fast');
	$('#mask').fadeIn('fast');
}

function closePopup() {
	$('#popup').fadeOut('fast');
	$('#mask').fadeOut('fast');
	$('body').css('overflow', 'auto');
}
