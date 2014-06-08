package matuszek.typer.utils;

public class BetPointsCalculator {

	private BetPointsCalculator() {
	}

	public static Integer calculateBetPoints(Integer betHome, Integer betAway,
			Integer actualHome, Integer actualAway) {

		if (betAway == null || betHome == null || actualAway == null
				|| actualHome == null) {
			return null;
		}

		if (betAway == actualAway && betHome == actualHome) {
			return 3;
		}

		if ((actualHome - actualAway < 0 && betHome - betAway < 0)
				|| (actualHome - actualAway == 0 && betHome - betAway == 0)
				|| (actualHome - actualAway > 0 && betHome - betAway > 0)) {
			return 1;
		}

		return 0;

	}
}
