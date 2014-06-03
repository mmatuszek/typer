(function($) {
	
	$.ajaxSetup({
		  contentType: "application/json; charset=utf-8"
	});

	var matchHtmlTemplate = '<div class="row" id="ID_PLACEHOLDER"><div class="col-md-2"><div class="match-details"><div class="datetime">DATETIME_PLACEHOLDER</div><div class="group">GROUP_PLACEHOLDER</div><div class="stadium">STADIUM_PLACEHOLDER</div><div class="venue">VENUE_PLACEHOLDER</div></div></div><div class="col-md-3"><div class="home"><img class="flag" src="HOME_TEAM_FLAG_PLACEHOLDER"><span class="team-name">HOME_TEAM_NAME_PLACEHOLDER</span></div></div><div class="col-md-1"><div class="score"><span>SCORE_PLACEHOLDER</span></div></div><div class="col-md-3"><div class="away"><span class="team-name">AWAY_TEAM_NAME_PLACEHOLDER</span><img class="flag" src="AWAY_TEAM_FLAG_PLACEHOLDER"></div></div>BET_PLACEHOLDER';
	var betFutureTemplate = '<div class="col-md-3"><div class="bet"><form class="form-inline" role="form"><input type="text" class="form-control bet betHome" value="BET_HOME_PLACEHOLDER"/><span class="bet">:</span><input type="text" class="form-control bet betAway" value="BET_AWAY_PLACEHOLDER"/><button type="button" class="btn btn-primary submit-bet">Zapisz</button></form></div>';
	var betPastTemplate = '<div class="col-md-3"><div class="bet">BET_PLACEHOLDER</div></div></div>';
	
	var flagMap = {
			'Brazylia':'bra.png',
			'Chorwacja':'cro.png',
			'Meksyk':'mex.png',
			'Kamerun':'cmr.png'
	};
	
	var pointsTemplateMap = {
			'3' : '<span class="points label label-success">CONTENT_PLACEHOLDER</span>',
			'1'	: '<span class="points label label-info">CONTENT_PLACEHOLDER</span>',
			'0' : '<span class="points label label-default">CONTENT_PLACEHOLDER</span>',
			'undefined' : '<span class="points label label-warning">CONTENT_PLACEHOLDER</span>'
	};
	
	$.fn.addMatches = function(options) {

        var settings = $.extend({
        }, options );
        var selector = $(this);
		
		$.ajax({
			url : settings.url,
			success : function(data) {
				$.each(data['matchEntry'], function(key, value) {
					selector.append(getMatchHtml(value));
				});
				$('.submit-bet').click(function() {
					$(this).addBet({
						url: settings.url
					});
				});
			},
		});

	};
	
	$.fn.addBet = function(options) {
		
		var betSelector = $(this).parents('div.bet');
		var bet = new Object();
		bet.betHome = betSelector.find('input.betHome').val();
		bet.betAway = betSelector.find('input.betAway').val();
		var matchId = betSelector.parents("div.row").attr('id');
		
		$.ajax({
			url : options.url + "/" + matchId + "/bet",
			type : "PUT",
			data : JSON.stringify(bet),
			success : function(data) {
				alert('Bet updated');
			}
		});
		
	};
	
	function getMatchHtml(matchEntry) {
		
		var html = matchHtmlTemplate;
		
		return html.replace('ID_PLACEHOLDER', matchEntry.id)
				.replace('DATETIME_PLACEHOLDER', $.formatDateTime('yy-mm-dd hh:ii', new Date(matchEntry.dateTime)))
				.replace('GROUP_PLACEHOLDER', 'Grupa ' + matchEntry.group)
				.replace('STADIUM_PLACEHOLDER', matchEntry.stadium)
				.replace('VENUE_PLACEHOLDER', matchEntry.city)
				.replace('HOME_TEAM_FLAG_PLACEHOLDER', 'resources/images/' + flagMap[matchEntry.teamHome])
				.replace('HOME_TEAM_NAME_PLACEHOLDER', matchEntry.teamHome)
				.replace('SCORE_PLACEHOLDER', getScoreHtml(matchEntry))
				.replace('AWAY_TEAM_NAME_PLACEHOLDER', matchEntry.teamAway)
				.replace('AWAY_TEAM_FLAG_PLACEHOLDER', 'resources/images/' + flagMap[matchEntry.teamAway])
				.replace('BET_PLACEHOLDER', getBetHtml(matchEntry));
		
	}
	
	function getBetHtml(matchEntry) {
		
		var matchDate = new Date(matchEntry.dateTime);
		var currentDate = new Date();
		
		if (currentDate < matchDate) {
			var html = betFutureTemplate;
			return html.replace('BET_HOME_PLACEHOLDER', matchEntry.betHome ? matchEntry.betHome : '')
				.replace('BET_AWAY_PLACEHOLDER', matchEntry.betAway ? matchEntry.betAway : '');			
		} else {
			var html = betPastTemplate;
			
			if (!matchEntry.betHome || !matchEntry.betAway) {
				return html.replace('BET_PLACEHOLDER', '');
			}
			
			var pointsTemplate = pointsTemplateMap[matchEntry.betPoints];
			return html.replace('BET_PLACEHOLDER', pointsTemplate.replace('CONTENT_PLACEHOLDER', matchEntry.betHome + ':' + matchEntry.betAway));
		};
		
	}
	
	function getScoreHtml(matchEntry) {
		
		if (!matchEntry.scoreHome || !matchEntry.scoreAway) {
			var matchDate = new Date(matchEntry.dateTime);
			var minutes = matchDate.getMinutes();
			return matchDate.getHours() + ':' + (minutes < 10 ? '0' + minutes : minutes);
		} else {
			return matchEntry.scoreHome + ':' + matchEntry.scoreAway;
		}
		
	}

}(jQuery));