$(function() {
	$('#matches').addMatches({
		url: 'http://localhost:8080/typer/rest/match',
	});
	$('#header').addWinnerBetDropdown({
		url: 'http://localhost:8080/typer/rest/winner/bet',
	});
});