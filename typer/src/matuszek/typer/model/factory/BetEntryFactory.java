package matuszek.typer.model.factory;

import java.util.ArrayList;
import java.util.List;

import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.Match;
import matuszek.typer.model.BetEntry;
import matuszek.typer.utils.BetPointsCalculator;

public class BetEntryFactory {

	private BetEntryFactory() {
	}

	public static BetEntry createBetEntry(Bet bet, Match match) {

		BetEntry entry = new BetEntry();

		entry.setBetAway(bet.getBetAway());
		entry.setBetHome(bet.getBetHome());
		entry.setUsername(bet.getUser().getUsername());
		entry.setUser(bet.getUser().getFirstName() + " " + bet.getUser().getLastName());
		entry.setPoints(BetPointsCalculator.calculateBetPoints(
				bet.getBetHome(), bet.getBetAway(), match.getScoreHome(),
				match.getScoreAway()));

		return entry;

	}

	public static List<BetEntry> createBetList(List<Bet> betList, Match match) {

		List<BetEntry> returnedList = new ArrayList<>();

		for (Bet bet : betList) {
			BetEntry entry = createBetEntry(bet, match);

			if (entry.getBetAway() != null && entry.getBetHome() != null) {
				returnedList.add(entry);
			}

		}

		return returnedList;

	}

}
