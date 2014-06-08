package matuszek.typer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BetEntry {

	private Integer betHome;
	private Integer betAway;
	private String user;
	private Integer points;

	public Integer getBetHome() {
		return betHome;
	}

	public Integer getBetAway() {
		return betAway;
	}

	public void setBetHome(Integer betHome) {
		this.betHome = betHome;
	}

	public void setBetAway(Integer betAway) {
		this.betAway = betAway;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "BetEntry [betHome=" + betHome + ", betAway=" + betAway + ", user=" + user + ", points=" + "]";
	}

}
