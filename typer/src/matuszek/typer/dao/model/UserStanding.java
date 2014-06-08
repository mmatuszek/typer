package matuszek.typer.dao.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserStanding {

	@Id
	private String username;
	@OneToMany
	@JoinColumn(name = "username", referencedColumnName = "username")
	private List<UserBet> matchBets;
	@OneToOne
	@JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
	private UserWinnerBet winnerBet;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<UserBet> getMatchBets() {
		return matchBets;
	}
	public void setMatchBets(List<UserBet> matchBets) {
		this.matchBets = matchBets;
	}
	public UserWinnerBet getWinnerBet() {
		return winnerBet;
	}
	public void setWinnerBet(UserWinnerBet winnerBet) {
		this.winnerBet = winnerBet;
	}
	@Override
	public String toString() {
		return "UserStanding [username=" + username + ", matchBets="
				+ matchBets + ", winnerBet=" + winnerBet + "]";
	}
}
