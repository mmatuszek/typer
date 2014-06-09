(function($) {

	$.ajaxSetup({
		contentType : "application/json; charset=utf-8"
	});

	var busyHtml = '<tr><td style="text-align: center"><img src="resources/images/busy_80.gif"></td></tr>';
	var winnerBusyHtml = '<div style="text-align: center"><img src="resources/images/busy_80.gif"></div>';
	var matchHtmlTemplate = '<tr id="ID_PLACEHOLDER"><td><div class="match-details"><div class="datetime" data-datetime="ORIGINAL_DATETIME_PLACEHOLDER">DATETIME_PLACEHOLDER</div><div class="group">GROUP_PLACEHOLDER</div><div class="stadium">STADIUM_PLACEHOLDER</div><div class="venue">VENUE_PLACEHOLDER</div></div></td><td><div class="home"><img class="flag" src="HOME_TEAM_FLAG_PLACEHOLDER"><span class="team-name">HOME_TEAM_NAME_PLACEHOLDER</span></div></td><td><div class="score"><span>SCORE_PLACEHOLDER</span></div></td><td><div class="away"><span class="team-name">AWAY_TEAM_NAME_PLACEHOLDER</span><img class="flag" src="AWAY_TEAM_FLAG_PLACEHOLDER"></div></td><td style="width: 250px">BET_PLACEHOLDER</td></tr>';
	var betFutureTemplate = '<div class="bet"><form class="form-inline" role="form"><input type="text" class="form-control bet-input betHome numbers-only" value="BET_HOME_PLACEHOLDER"/><span class="bet">:</span><input type="text" class="form-control bet-input betAway numbers-only" value="BET_AWAY_PLACEHOLDER"/><button type="button" class="btn btn-primary submit-bet">Zapisz</button></form></div>';
	var betPastTemplate = '<div class="bet">BET_PLACEHOLDER<a href="javascript:void(0);" class="other-bets" data-toggle="modal" data-target="#match-bets">Wszystkie typy</a></div>';
	var winnerBetFutureTemplate = '<div><div class="col-md-3"><div class="input-group"><input type="text" disabled class="form-control" value="ORIGINAL_BET_PLACEHOLDER"><div class="input-group-btn"><button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button><ul id="team-list" class="dropdown-menu pull-right scrollable-menu">WINNER_LIST_PLACEHOLDER</ul></div></div></div><div class="col-md-2"><button type="button" class="btn btn-primary submit-winner-bet">Zapisz</button></div></div><div class="col-md-8" id="winner-bet-deadline" data-datetime="ORIGINAL_DEADLINE_PLACEHOLDER" style="font-size: 12px">Typ przyjmowany do: DEADLINE_PLACEHOLDER</div>';
	var winnerBetPastTemplate = 'WINNER_PLACEHOLDER&nbsp;<span><a href="javascript:void(0);" class="other-bets" data-toggle="modal" data-target="#winner-bets">Wszystkie typy</a><span></div>';
	var flagMap = {
		'Chorwacja' : 'cro.png',
		'Meksyk' : 'mex.png',
		'Algieria' : 'alg.png',
		'Argentyna' : 'arg.png',
		'Australia' : 'aus.png',
		'Belgia' : 'bel.png',
		'Bośnia i Herc.' : 'bih.png',
		'Brazylia' : 'bra.png',
		'Chile' : 'chi.png',
		'WKS' : 'civ.png',
		'Kamerun' : 'cmr.png',
		'Kolumbia' : 'col.png',
		'Kostaryka' : 'crc.png',
		'Ekwador' : 'ecu.png',
		'Anglia' : 'eng.png',
		'Hiszpania' : 'esp.png',
		'Francja' : 'fra.png',
		'Niemcy' : 'ger.png',
		'Ghana' : 'gha.png',
		'Grecja' : 'gre.png',
		'Honduras' : 'hon.png',
		'Iran' : 'irn.png',
		'Włochy' : 'ita.png',
		'Japonia' : 'jpn.png',
		'Korea Płd.' : 'kor.png',
		'Holandia' : 'ned.png',
		'Nigeria' : 'nga.png',
		'Portugalia' : 'por.png',
		'Rosja' : 'rus.png',
		'Szwajcaria' : 'sui.png',
		'Urugwaj' : 'uru.png',
		'USA' : 'usa.png'
	};

	var pointsTemplateMap = {
		'10' : '<span class="points label label-success">CONTENT_PLACEHOLDER</span>',
		'3' : '<span class="points label label-success">CONTENT_PLACEHOLDER</span>',
		'1' : '<span class="points label label-info">CONTENT_PLACEHOLDER</span>',
		'0' : '<span class="points label label-default">CONTENT_PLACEHOLDER</span>',
		'undefined' : '<span class="points label label-warning">CONTENT_PLACEHOLDER</span>'
	};

	var showErrorMessage = function(message) {

		var alertTemplate = '<div class="alert alert-danger fade in"><button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button><p>MESSAGE_PLACEHOLDER</p></div>';

		$('#messages').append(
				alertTemplate.replace('MESSAGE_PLACEHOLDER', message));
		$('.alert').alert();

	};

	$.fn.addWinnerBetDropdown = function(options) {

		var settings = $.extend({}, options);

		var selector = $(this);

		$.fn.addSelectAction = function() {
			$(this).click(function() {
				$('#winner-bet input').val($(this).text());
			});
		};

		var getBetHtml = function(data) {

			var html = '';
			var deadlineDate = new Date(data.deadline);
			var currentDate = new Date();

			if (data.winner) {

				var pointsTemplate = data.winner.name == data.bet.name ? pointsTemplateMap['3']
						: pointsTemplateMap['0'];
				html = winnerBetPastTemplate.replace('WINNER_PLACEHOLDER',
						pointsTemplate.replace('CONTENT_PLACEHOLDER',
								data.bet.name));

			} else if (currentDate > deadlineDate) {

				html = winnerBetPastTemplate.replace('WINNER_PLACEHOLDER',
						pointsTemplateMap['undefined'].replace(
								'CONTENT_PLACEHOLDER', data.bet.name));

			} else {

				html = winnerBetFutureTemplate;

				var teamListHtml = "";

				$.each(data.teams, function(key, value) {
					teamListHtml += '<li class="team"><a href="#">'
							+ value.name + '</a></li>';
				});

				html = html.replace('WINNER_LIST_PLACEHOLDER', teamListHtml)
						.replace('ORIGINAL_BET_PLACEHOLDER',
								data.bet ? data.bet.name : '').replace(
								'ORIGINAL_DEADLINE_PLACEHOLDER',
								new Date(data['deadline'])).replace(
								'DEADLINE_PLACEHOLDER',
								$.formatDateTime('yy-mm-dd hh:ii', new Date(
										data.deadline)));

			}

			return html;

		};

		$.fn.displayWinnerBets = function() {

			var selector = $(this).find('table');
			selector.empty();
			selector.html(winnerBusyHtml);

			$.ajax({
				url : settings.url + "/all",
				type : "GET",
				success : function(data) {
					selector.empty();
					$.each(data, function(key, value) {
						selector.append('<tr><td>' + pointsTemplateMap[value.points].replace('CONTENT_PLACEHOLDER', (value.teamName ? value.teamName : '') + '</td><td><div class="user">' + value.username
								+ '</div></td><td><div class="user">' + value.user + '</div></tr>'));
					});
				},
				error : function(data) {
					showErrorMessage(data.responseText);
				}
			});

		};

		$.fn.saveWinnerBet = function() {

			var bet = new Object();
			bet.name = $('#winner-bet input').val();

			$.ajax({
				url : settings.url,
				type : "PUT",
				data : JSON.stringify(bet),
				success : function(data) {
					$('#winner-bet .busy').remove();
				},
				error : function(data) {
					$('#winner-bet .busy').remove();
					showErrorMessage(data.responseText);
				}
			});
		};

		$.fn.setBetTimeout = function() {

			var deadline = new Date($('#winner-bet-deadline').attr(
					'data-datetime'));
			var milliseconds = deadline.getTime() - new Date().getTime();

			var stopBet = function() {

				var betSelector = $('#winner-bet');
				betSelector
						.html('<img src="resources/images/busy_30.gif">&nbsp;');

				$.ajax({
					url : settings.url,
					type : "GET",
					success : function(data) {
						betSelector.parent().html(getBetHtml(data));
					}
				});
			};

			if (milliseconds > 0) {
				setTimeout(function() {
					stopBet();
				}, milliseconds);
			}
			;

		};

		selector.empty();
		selector.html(winnerBusyHtml);
		
		$.ajax({
			url : settings.url,
			success : function(data) {
				selector.empty();
				var html = getBetHtml(data);
				selector.append(html);
				$('li.team').addSelectAction();
				$('.submit-winner-bet').click(function() {
					$(this).parent().find('.busy').remove();
					$(this).parent().append('<img class="busy" src="resources/images/busy_30.gif">');
					$(this).saveWinnerBet();
				});
				$('.submit-winner-bet').setBetTimeout();
				$('#winner-bet .other-bets').click(function() {
					$('#winner-bets').displayWinnerBets();
				});
			},
			error : function(data) {
				showErrorMessage(data.responseText);
			}
		});
	};

	$.fn.addMatches = function(options) {

		var settings = $.extend({}, options);

		var selector = $(this);

		var getMatchHtml = function(matchEntry) {

			var html = matchHtmlTemplate;

			return html.replace('ID_PLACEHOLDER', matchEntry.id).replace(
					'ORIGINAL_DATETIME_PLACEHOLDER',
					new Date(matchEntry.dateTime)).replace(
					'DATETIME_PLACEHOLDER',
					$.formatDateTime('yy-mm-dd hh:ii', new Date(
							matchEntry.dateTime))).replace('GROUP_PLACEHOLDER',
					matchEntry.group).replace('STADIUM_PLACEHOLDER',
					matchEntry.stadium).replace('VENUE_PLACEHOLDER',
					matchEntry.city).replace('HOME_TEAM_FLAG_PLACEHOLDER',
					'resources/images/' + flagMap[matchEntry.teamHome])
					.replace('HOME_TEAM_NAME_PLACEHOLDER', matchEntry.teamHome)
					.replace('SCORE_PLACEHOLDER', getScoreHtml(matchEntry))
					.replace('AWAY_TEAM_NAME_PLACEHOLDER', matchEntry.teamAway)
					.replace('AWAY_TEAM_FLAG_PLACEHOLDER',
							'resources/images/' + flagMap[matchEntry.teamAway])
					.replace('BET_PLACEHOLDER', getBetHtml(matchEntry));

		};

		var getBetHtml = function(matchEntry) {

			var matchDate = new Date(matchEntry.dateTime);
			var currentDate = new Date();

			if (currentDate < matchDate) {
				var html = betFutureTemplate;
				return html.replace('BET_HOME_PLACEHOLDER',
						matchEntry.betHome ? matchEntry.betHome : '').replace(
						'BET_AWAY_PLACEHOLDER',
						matchEntry.betAway ? matchEntry.betAway : '');
			} else {
				var html = betPastTemplate;

				if (!matchEntry.betHome || !matchEntry.betAway) {
					return html.replace('BET_PLACEHOLDER', '');
				}

				var pointsTemplate = pointsTemplateMap[matchEntry.betPoints];
				return html.replace('BET_PLACEHOLDER', pointsTemplate.replace(
						'CONTENT_PLACEHOLDER', matchEntry.betHome + ':'
								+ matchEntry.betAway));
			}
			;

		};

		var getScoreHtml = function(matchEntry) {

			if (!matchEntry.scoreHome || !matchEntry.scoreAway) {
				var matchDate = new Date(matchEntry.dateTime);
				var minutes = matchDate.getMinutes();
				return matchDate.getHours() + ':'
						+ (minutes < 10 ? '0' + minutes : minutes);
			} else {
				return matchEntry.scoreHome + ':' + matchEntry.scoreAway;
			}

		};

		$.fn.displayMatchBets = function(matchId) {

			var selector = $(this).find('table');
			selector.empty();
			selector.html(busyHtml);

			$.ajax({
				url : settings.url + "/" + matchId + "/bet/all",
				type : "GET",
				success : function(data) {
					selector.empty();
					if (data) {
						$.each(data['betEntry'], function(key, value) {
							selector
									.append('<tr><td>' + pointsTemplateMap[value.points].replace('CONTENT_PLACEHOLDER', ((value.betHome && value.betAway) ? value.betHome + ':'
											+ value.betAway : '')) + '</td><td><div class="user">' + value.username
											+ '</div></td><td><div class="user">' + value.user + '</div></tr>');
						});
					}
				},
				error : function(data) {
					showErrorMessage(data.responseText);
				}
			});

		};

		$.fn.addBet = function() {

			var betSelector = $(this).parents('div.bet');
			var bet = new Object();
			bet.betHome = betSelector.find('input.betHome').val();
			bet.betAway = betSelector.find('input.betAway').val();
			var matchId = betSelector.parents('tr').attr('id');
			
			if (bet.betHome == "" || bet.betAway == "") {
				betSelector.find('img.busy').remove();
				showErrorMessage("Nie można wysłać typu. Nie wypełniono wszystkich pól.");
				return false;
			}

			$.ajax({
				url : settings.url + "/" + matchId + "/bet",
				type : "PUT",
				data : JSON.stringify(bet),
				success : function(data) {
					betSelector.find('img.busy').remove();
					
				},
				error : function(data) {
					showErrorMessage(data.responseText);
					betSelector.find('img.busy').remove();
				}
			});

		};

		$.fn.setBetTimeout = function() {

			var matchDate = new Date($(this).parents('tr').find(
					'.datetime').attr('data-datetime'));
			var milliseconds = matchDate.getTime() - new Date().getTime();
			var matchId = $(this).parents('tr').attr('id');

			var stopBet = function() {

				var betSelector = $('#' + matchId).find('div.bet');
				betSelector.html('<img src="resources/images/busy_30.gif">&nbsp;');

				$.ajax({
					url : settings.url + "/" + matchId,
					type : "GET",
					success : function(data) {
						betSelector.parent().html(getBetHtml(data));
					}
				});
			};

			if (milliseconds > 0) {
				setTimeout(function() {
					stopBet();
				}, milliseconds);
			}
			;

		};

		selector.empty();
		selector.html(busyHtml);
		
		$.ajax({
			url : settings.url + '/all?status=' + settings.status,
			success : function(data) {
				selector.empty();
				if (data) {
					$.each(data['matchEntry'], function(key, value) {
						selector.append(getMatchHtml(value));
					});
					$('div.bet form').parent().each(function() {
						$(this).setBetTimeout();
					});
					$('.other-bets').click(function() {
						var id = $(this).parents('tr').attr('id');
						$('#match-bets').displayMatchBets(id);
					});
					$('.submit-bet').click(function() {
						$(this).parent().find('.busy').remove();
						$(this).parent().append('<img class="busy" src="resources/images/busy_30.gif">');
						$(this).addBet();
					});
					$('.numbers-only').keypress(function(e) {
						if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
							return false;
						}
					});
				}
			},
			error : function(data) {
				showErrorMessage(data.responseText);
			}
		});

	};

}(jQuery));