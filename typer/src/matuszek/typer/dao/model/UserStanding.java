package matuszek.typer.dao.model;

import java.util.List;

import javax.persistence.Column;
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
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

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
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "UserStanding [username=" + username + ", matchBets="
				+ matchBets + ", winnerBet=" + winnerBet + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

}
