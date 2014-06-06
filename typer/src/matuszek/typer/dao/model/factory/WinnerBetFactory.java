package matuszek.typer.dao.model.factory;

import matuszek.typer.dao.model.Team;
import matuszek.typer.dao.model.User;
import matuszek.typer.dao.model.WinnerBet;
import matuszek.typer.model.TeamEntry;

public class WinnerBetFactory {

	private WinnerBetFactory() {
	}

	public static WinnerBet create(TeamEntry teamEntry, String username) {
		
		User user = new User();
		user.setUsername(username);
		
		Team team = new Team();
		team.setName(teamEntry.getName());
		
		WinnerBet returnedBet = new WinnerBet();
		returnedBet.setTeam(team);
		returnedBet.setUser(user);
		
		return returnedBet;
	}
}
