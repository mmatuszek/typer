package matuszek.typer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WinnerBetEntry {

	private String teamName;
	private String user;
	private String username;
	private Integer points;

	public WinnerBetEntry() {

	}

	public WinnerBetEntry(String teamName, String user, Integer points,
			String username) {
		this.teamName = teamName;
		this.user = user;
		this.points = points;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "WinnerBetEntry [teamName=" + teamName + ", user=" + user
				+ ", points=" + points + ", username=" + username + "]";
	}

}
