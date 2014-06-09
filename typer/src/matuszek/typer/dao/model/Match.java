package matuszek.typer.dao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "matches")
public class Match {

	@Id
	@Column(name = "match_id")
	private int id;
	@Column(name = "score_home")
	private Integer scoreHome;
	@Column(name = "score_away")
	private Integer scoreAway;
	@Column(name = "team_home")
	private String teamHome;
	@Column(name = "team_away")
	private String teamAway;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datetime")
	private Date dateTime;
	@Column(name = "city")
	private String city;
	@Column(name = "stadium")
	private String stadium;
	@Column(name = "type")
	private String group;
	@OneToMany
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private List<Bet> betList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getScoreHome() {
		return scoreHome;
	}
	public void setScoreHome(Integer scoreHome) {
		this.scoreHome = scoreHome;
	}
	public Integer getScoreAway() {
		return scoreAway;
	}
	public void setScoreAway(Integer scoreAway) {
		this.scoreAway = scoreAway;
	}
	public String getTeamHome() {
		return teamHome;
	}
	public void setTeamHome(String teamHome) {
		this.teamHome = teamHome;
	}
	public String getTeamAway() {
		return teamAway;
	}
	public void setTeamAway(String teamAway) {
		this.teamAway = teamAway;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStadium() {
		return stadium;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public List<Bet> getBetList() {
		return betList;
	}
	public void setBetList(List<Bet> betList) {
		this.betList = betList;
	}
	@Override
	public String toString() {
		return "Match [id=" + id + ", scoreHome=" + scoreHome + ", scoreAway="
				+ scoreAway + ", teamHome=" + teamHome + ", teamAway="
				+ teamAway + ", dateTime=" + dateTime + ", city=" + city
				+ ", stadium=" + stadium + ", betList=" + betList + "]";
	}

}
