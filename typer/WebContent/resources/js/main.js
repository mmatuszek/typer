$(document).ready(function () {

	var busyHtml = '<tr><td style="text-align: center"><img src="resources/images/busy_80.gif"></td></tr>';

	$('#future-tab').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
		$('#future-matches').addMatches({
			url : 'http://54.187.99.245:8080/typer/rest/match',
			status : 'future'
		});
	});
	$('#current-tab').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
		$('#current-matches').addMatches({
			url : 'http://54.187.99.245:8080/typer/rest/match',
			status : 'current'
		});
	});
	$('#finished-tab').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
		$('#finished-matches').addMatches({
			url : 'http://54.187.99.245:8080/typer/rest/match',
			status : 'past'
		});
	});
	$('#winner-tab').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
		$('#winner-bet').addWinnerBetDropdown({
			url : 'http://54.187.99.245:8080/typer/rest/winner/bet',
		});
	});
	$('#bet-tabs li.active a').click();
	$('#logout').click(function() {
		$.ajax({
			url: 'http://54.187.99.245:8080/typer/rest/logout',
			type: "POST",
			success: function() {
				window.location.reload();
			}
		});
	});
});