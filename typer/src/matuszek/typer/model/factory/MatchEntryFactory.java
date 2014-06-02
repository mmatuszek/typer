package matuszek.typer.model.factory;

import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.Match;
import matuszek.typer.model.MatchEntry;

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
		
		MatchEntry entry = new MatchEntry().city(match.getCity())
				.dateTime(match.getDateTime())
				.scoreAway(match.getScoreAway())
				.scoreHome(match.getScoreHome())
				.stadium(match.getStadium())
				.group(match.getGroup())
				.teamAway(match.getTeamAway())
				.teamHome(match.getTeamHome());
		
		if (relatedBet != null) {
			entry.betAway(relatedBet.getBetAway())
				.betHome(relatedBet.getBetHome())
				.betPoints(calculateBetPoints(entry));	
		}
		
		return entry;
		
	}
	
	private static Integer calculateBetPoints(MatchEntry matchEntry) {
		
		if (matchEntry.getBetAway() == null 
			|| matchEntry.getBetHome() == null
			|| matchEntry.getScoreAway() == null
			|| matchEntry.getScoreHome() == null) {
			return null;
		}
		
		if (matchEntry.getBetAway() == matchEntry.getScoreAway() 
				&& matchEntry.getBetHome() == matchEntry.getScoreHome()) {
			return 3;
		}
		
		if ((matchEntry.getScoreHome() - matchEntry.getScoreAway() < 0
				&& matchEntry.getBetHome() - matchEntry.getBetAway() < 0)
				|| (matchEntry.getScoreHome() - matchEntry.getScoreAway() == 0
				&& matchEntry.getBetHome() - matchEntry.getBetAway() == 0)
				|| (matchEntry.getScoreHome() - matchEntry.getScoreAway() > 0
				&& matchEntry.getBetHome() - matchEntry.getBetAway() > 0)) {
			return 1;
		}
		
		return 0;
		
	}
}
