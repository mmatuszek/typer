package matuszek.typer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WinnerBetEntry {

	private String teamName;
	private String user;
	private Integer points;

	public WinnerBetEntry() {
		
	}
	
	public WinnerBetEntry(String teamName, String user, Integer points) {
		this.teamName = teamName;
		this.user = user;
		this.points = points;
	}

	public Integer getPoints() {
		return points;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getUser() {
		return user;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "WinnerBetEntry [teamName=" + teamName + ", user=" + user + ", points=" + points + "]";
	}

}
