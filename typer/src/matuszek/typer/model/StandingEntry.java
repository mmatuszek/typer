package matuszek.typer.model;

public class StandingEntry {

	private String userLogin;
	private String userName;
	private int totalPoints;
	private int exactScorePoints;
	private int wldScorePoints;
	private int winnerPoints;

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String login) {
		this.userLogin = login;
	}

	public StandingEntry userLogin(String login) {
		this.userLogin = login;
		return this;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public StandingEntry totalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
		return this;
	}

	public int getExactScorePoints() {
		return exactScorePoints;
	}

	public StandingEntry exactScorePoints(int exactScorePoints) {
		this.exactScorePoints = exactScorePoints;
		return this;
	}

	public void setExactScorePoints(int exactScorePoints) {
		this.exactScorePoints = exactScorePoints;
	}

	public int getWldScorePoints() {
		return wldScorePoints;
	}

	public StandingEntry wldScorePoints(int wldScorePoints) {
		this.wldScorePoints = wldScorePoints;
		return this;
	}

	public void setWldScorePoints(int wldScorePoints) {
		this.wldScorePoints = wldScorePoints;
	}

	public int getWinnerPoints() {
		return winnerPoints;
	}

	public void setWinnerPoints(int winnerPoints) {
		this.winnerPoints = winnerPoints;
	}

	public StandingEntry winnerPoints(int winnerPoints) {
		this.winnerPoints = winnerPoints;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public StandingEntry userName(String userName) {
		this.userName = userName;
		return this;
	}

	@Override
	public String toString() {
		return "StandingEntry [userLogin=" + userLogin + ", userName="
				+ userName + ", totalPoints=" + totalPoints
				+ ", exactScorePoints=" + exactScorePoints
				+ ", wldScorePoints=" + wldScorePoints + ", winnerPoints="
				+ winnerPoints + "]";
	}

}
