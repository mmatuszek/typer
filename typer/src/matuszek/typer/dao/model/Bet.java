package matuszek.typer.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bets")
public class Bet {

	@Id
	@Column(name = "bet_id")
	private int id;
	@OneToOne(optional = false)
    @JoinColumn(name = "username") 
	private User user;
	@Column(name = "score_home")
	private Integer betHome;
	@Column(name = "score_away")
	private Integer betAway;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBetHome() {
		return betHome;
	}
	public void setBetHome(int betHome) {
		this.betHome = betHome;
	}
	public int getBetAway() {
		return betAway;
	}
	public void setBetAway(int betAway) {
		this.betAway = betAway;
	}
	
	@Override
	public String toString() {
		return "Bet [id=" + id + ", user=" + user
				+ ", betHome=" + betHome + ", betAway=" + betAway + "]";
	}
	
}
