package matuszek.typer.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "winner_bets")
public class WinnerBet {

	@Id
	@Column(name = "bet_id")
	private int id;
	@OneToOne(optional = false)
    @JoinColumn(name = "team_name") 
	private Team team;
	@OneToOne(optional = false)
    @JoinColumn(name = "username") 
	private User user;
	public int getId() {
		return id;
	}
	public Team getTeam() {
		return team;
	}
	public User getUser() {
		return user;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "WinnerBet [id=" + id + ", team=" + team + ", user=" + user
				+ "]";
	}
}
