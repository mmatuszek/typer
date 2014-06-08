package matuszek.typer.model;

public class StandingEntry {

	private String user;
	private int totalPoints;
	private int exactScorePoints;
	private int wldScorePoints;
	private int winnerPoints;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public StandingEntry user(String user) {
		this.user = user;
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
	@Override
	public String toString() {
		return "StandingEntry [user=" + user + ", totalPoints=" + totalPoints
				+ ", exactScorePoints=" + exactScorePoints
				+ ", wldScorePoints=" + wldScorePoints + ", winnerPoints="
				+ winnerPoints + "]";
	}
	
	
}
