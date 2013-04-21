	$(document).ready(function() {
		console.log("Okay let's go...");
	});



	$(document)
			.ready(
					function() {
						$('form')
								.submit(
										function() {
   // abort any pending request
    if (request) {
        request.abort();
    }
    // setup some local variables
    var $form = $(this);
    // let's select and cache all the fields
    var $inputs = $form.find("input");
    // serialize the data in the form
    var serializedData = $form.serialize();

    // let's disable the inputs for the duration of the ajax request
    $inputs.prop("disabled", true);

    // fire off the request to /form.php
    var request = $.ajax({
        url: "http://localhost:8080/store/confirm",
        type: "post",
        data: serializedData
    });

// This is jQuery 1.8
    // callback handler that will be called on success
    request.done(function (response, textStatus, jqXHR){
        // log a message to the console
        console.log("Hooray, it worked!");
    });

    // callback handler that will be called on failure
    request.fail(function (jqXHR, textStatus, errorThrown){
        // log the error to the console
        console.error(
            "The following error occured: "+
            textStatus, errorThrown
        );
    });

    // callback handler that will be called regardless
    // if the request failed or succeeded
    request.always(function () {
        // reenable the inputs
        $inputs.prop("disabled", false);
    });

    // prevent default posting of form
    event.preventDefault();


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
