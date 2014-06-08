package matuszek.typer.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bets")
public class UserBet {

	@Id
	@Column(name = "bet_id")
	private int id;
	@Column(name = "score_home")
	private Integer betHome;
	@Column(name = "score_away")
	private Integer betAway;
	@OneToOne(optional = false)
	@JoinColumn(name = "match_id")
	private Match match;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getBetHome() {
		return betHome;
	}

	public void setBetHome(Integer betHome) {
		this.betHome = betHome;
	}

	public Integer getBetAway() {
		return betAway;
	}

	public void setBetAway(Integer betAway) {
		this.betAway = betAway;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "UserBet [id=" + id + ", betHome=" + betHome + ", betAway="
				+ betAway + ", match=" + match + "]";
	}

}
