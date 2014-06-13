package matuszek.typer.model.factory;

import matuszek.typer.dao.model.UserBet;
import matuszek.typer.dao.model.UserStanding;
import matuszek.typer.dao.model.Winner;
import matuszek.typer.model.StandingEntry;
import matuszek.typer.utils.BetPointsCalculator;
import matuszek.typer.utils.WinnerPointsCalculator;

public class StandingEntryFactory {

	private StandingEntryFactory() {
	}

	public static StandingEntry createStandingEntry(UserStanding user,
			Winner winner) {

		int exactScorePoints = 0;
		int wldScorePoints = 0;
		int winnerPoints = 0;
		int totalPoints = 0;

		for (UserBet bet : user.getMatchBets()) {

			if (bet.getMatch() == null) {
				continue;
			}

			Integer betHome = bet.getBetHome();
			Integer betAway = bet.getBetAway();
			Integer scoreHome = bet.getMatch().getScoreHome();
			Integer scoreAway = bet.getMatch().getScoreAway();

			Integer p = BetPointsCalculator.calculateBetPoints(betHome,
					betAway, scoreHome, scoreAway);

			int points = p == null ? 0 : p;

			if (points == 1) {
				wldScorePoints++;
			} else if (points == 3) {
				exactScorePoints += 3;
			}

		}

		Integer winnerP = user.getWinnerBet() == null ? null
				: WinnerPointsCalculator.calculateWinnerPoints(user
						.getWinnerBet().getTeam(), winner.getTeam());

		winnerPoints = winnerP == null ? 0 : winnerP;

		totalPoints = winnerPoints + wldScorePoints + exactScorePoints;

		return new StandingEntry().userLogin(user.getUsername())
				.exactScorePoints(exactScorePoints/3)
				.wldScorePoints(wldScorePoints).winnerPoints(winnerP != null ? 1 : 0)
				.totalPoints(totalPoints).userName(user.getFirstName() + " " + user.getLastName());

	}
}
