(function($) {

	$.ajaxSetup({
		contentType : "application/json; charset=utf-8"
	});


	var showErrorMessage = function(message) {

		var alertTemplate = '<div class="alert alert-danger fade in"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button><p>MESSAGE_PLACEHOLDER</p></div>';

		$('#messages').append(
				alertTemplate.replace('MESSAGE_PLACEHOLDER', message));
		$('.alert').alert();

	};

	$.fn.addStandings = function(options) {

		var settings = $.extend({}, options);

		var selector = $(this);

		$.ajax({
			url : settings.url,
			success : function(data) {
				selector.empty();
				var counter = 0;
				var prevTotal = undefined;
				$.each(data, function(key, value) {
					counter++;
					var place = counter + '. ';
					if (prevTotal != undefined && prevTotal == value.totalPoints) {
						place = ' ';
					};
					selector.append('<div>' + place + value.user + '-' + value.exactScorePoints + '-' + value.wldScorePoints + '-' + value.winnerPoints + '-' + value.totalPoints + '<div>');
					prevTotal = value.totalPoints;
				});
			},
			error : function(data) {
				showErrorMessage(data.responseText);
			}
		});

	};

}(jQuery));