package matuszek.typer.model.factory;

import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.Match;
import matuszek.typer.model.MatchEntry;
import matuszek.typer.utils.BetPointsCalculator;

public class MatchEntryFactory {

	private MatchEntryFactory() {

	}

	public static MatchEntry createEntry(Match match, String username) {

		Bet relatedBet = null;

		for (Bet bet : match.getBetList()) {
			if (bet.getUser().getUsername().equals(username)) {
				relatedBet = bet;
			}
		}

		MatchEntry entry = new MatchEntry().id(match.getId())
				.city(match.getCity()).dateTime(match.getDateTime())
				.scoreAway(match.getScoreAway())
				.scoreHome(match.getScoreHome()).stadium(match.getStadium())
				.group(match.getGroup()).teamAway(match.getTeamAway())
				.teamHome(match.getTeamHome());

		if (relatedBet != null) {
			entry.betAway(relatedBet.getBetAway())
					.betHome(relatedBet.getBetHome())
					.betPoints(
							BetPointsCalculator.calculateBetPoints(
									entry.getBetHome(), entry.getBetAway(),
									entry.getScoreHome(), entry.getScoreAway()));
		}

		return entry;

	}

}
