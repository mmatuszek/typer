package matuszek.typer.utils;

import matuszek.typer.dao.model.Team;

public class WinnerPointsCalculator {

	private WinnerPointsCalculator() { }
	
	public static Integer calculateWinnerPoints(Team betTeam, Team winnerTeam) {
				
		if (betTeam == null || winnerTeam == null) {
			return null;
		}
		
		if (betTeam.getName().equals(winnerTeam.getName())) {
			return 10;
		} else {
			return 0;
		}
		
	}
}
