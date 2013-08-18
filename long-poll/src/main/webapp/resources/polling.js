
var allow = true;
var startUrl;
var pollUrl;


function Poll() {

	this.start = function start(start, poll) {

		startUrl = start;
		pollUrl = poll;
		
		if (request) {
			request.abort(); // abort any pending request
		}

		// fire off the request to MatchUpdateController
		var request = $.ajax({
			url : startUrl,
			type : "get",
		});

		// This is jQuery 1.8+
		// callback handler that will be called on success
		request.done(function(reply) {

			console.log("Game on..." + reply);
			setInterval(function() {
				if (allow === true) {
					allow = false;
					getUpdate();
				}
			}, 500);
		});
		
		// callback handler that will be called on failure
		request.fail(function(jqXHR, textStatus, errorThrown) {
			// log the error to the console
			console.log("Start - the following error occured: " + textStatus, errorThrown);
		});
		
		
	};
	
	function getUpdate() {
		
		console.log("Okay let's go...");
																	
		if (request) {
			request.abort();	// abort any pending request
		}
		
		// fire off the request to MatchUpdateController
		var request = $.ajax({
			url : pollUrl,
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
			console.log("Polling - the following error occured: " + textStatus, errorThrown);
		});

		// callback handler that will be called regardless if the request failed or succeeded
		request.always(function() {
			allow = true;
		});
	};	
};