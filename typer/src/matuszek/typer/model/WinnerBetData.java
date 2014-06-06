package matuszek.typer.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WinnerBetData {

	private List<TeamEntry> teams;
	private TeamEntry bet;
	private TeamEntry winner;
	public TeamEntry getWinner() {
		return winner;
	}
	public void setWinner(TeamEntry winner) {
		this.winner = winner;
	}
	private Date deadline;
	public List<TeamEntry> getTeams() {
		return teams;
	}
	public TeamEntry getBet() {
		return bet;
	}
	public Date getDeadline() {
		return deadline;
	}
	public WinnerBetData teams(List<TeamEntry> teams) {
		this.teams = teams;
		return this;
	}
	public WinnerBetData winner(TeamEntry team) {
		this.winner = team;
		return this;
	}
	public WinnerBetData bet(TeamEntry bet) {
		this.bet = bet;
		return this;
	}
	public WinnerBetData deadline(Date deadline) {
		this.deadline = deadline;
		return this;
	}
	public void setTeams(List<TeamEntry> teams) {
		this.teams = teams;
	}
	public void setBet(TeamEntry bet) {
		this.bet = bet;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	@Override
	public String toString() {
		return "WinnerBetData [teams=" + teams + ", bet=" + bet
				+ ", deadline=" + deadline + ", winner=" + winner + "]";
	}
	
	
}
