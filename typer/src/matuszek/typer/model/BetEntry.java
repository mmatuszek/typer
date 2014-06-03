package matuszek.typer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BetEntry {

	private Integer betHome;
	private Integer betAway;

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

	@Override
	public String toString() {
		return "BetEntry [betHome=" + betHome + ", betAway=" + betAway + "]";
	}

}
