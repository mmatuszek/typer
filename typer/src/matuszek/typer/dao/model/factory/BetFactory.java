package matuszek.typer.dao.model.factory;

import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.User;
import matuszek.typer.model.BetEntry;

public class BetFactory {

	private BetFactory() { }
	
	public static Bet createBet(BetEntry betEntry, String username, Integer matchId) {
		
		User user = new User();
		user.setUsername(username);
		
		Bet bet = new Bet();
		bet.setBetAway(betEntry.getBetAway());
		bet.setBetHome(betEntry.getBetHome());
		bet.setMatchId(matchId);
		bet.setUser(user);
		
		return bet;
	}
}
