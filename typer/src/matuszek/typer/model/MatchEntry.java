package matuszek.typer.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MatchEntry {
	
	private int id;
	private String teamHome;
	private String teamAway;
	private Integer scoreHome;
	private Integer scoreAway;
	private Date dateTime;
	private String city;
	private String stadium;
	private String group;
	private Integer betHome;
	private Integer betAway;
	private Integer betPoints;
	
	public MatchEntry id(int id) {
		this.id = id;
		return this;
	}
	public MatchEntry teamHome(String teamHome) {
		this.teamHome = teamHome;
		return this;
	}
	public MatchEntry teamAway(String teamAway) {
		this.teamAway = teamAway;
		return this;
	}
	public MatchEntry scoreHome(Integer scoreHome) {
		this.scoreHome = scoreHome;
		return this;
	}
	public MatchEntry scoreAway(Integer scoreAway) {
		this.scoreAway = scoreAway;
		return this;
	}
	public MatchEntry dateTime(Date dateTime) {
		this.dateTime = dateTime;
		return this;
	}
	public MatchEntry city(String city) {
		this.city = city;
		return this;
	}
	public MatchEntry stadium(String stadium) {
		this.stadium = stadium;
		return this;
	}
	public MatchEntry group(String group) {
		this.group = group;
		return this;
	}
	public MatchEntry betHome(Integer betHome) {
		this.betHome = betHome;
		return this;
	}
	public MatchEntry betAway(Integer betAway) {
		this.betAway = betAway;
		return this;
	}
	public MatchEntry betPoints(Integer betPoints) {
		this.betPoints = betPoints;
		return this;
	}
	public void setTeamHome(String teamHome) {
		this.teamHome = teamHome;
	}
	public void setTeamAway(String teamAway) {
		this.teamAway = teamAway;
	}
	public void setScoreHome(Integer scoreHome) {
		this.scoreHome = scoreHome;
	}
	public void setScoreAway(Integer scoreAway) {
		this.scoreAway = scoreAway;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setBetHome(Integer betHome) {
		this.betHome = betHome;
	}
	public void setBetAway(Integer betAway) {
		this.betAway = betAway;
	}
	public void setBetPoints(Integer betPoints) {
		this.betPoints = betPoints;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getTeamHome() {
		return teamHome;
	}
	public String getTeamAway() {
		return teamAway;
	}
	public Integer getScoreHome() {
		return scoreHome;
	}
	public Integer getScoreAway() {
		return scoreAway;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public String getCity() {
		return city;
	}
	public String getStadium() {
		return stadium;
	}
	public String getGroup() {
		return group;
	}
	public Integer getBetHome() {
		return betHome;
	}
	public Integer getBetAway() {
		return betAway;
	}
	public Integer getBetPoints() {
		return betPoints;
	}
	@Override
	public String toString() {
		return "MatchEntry [teamHome=" + teamHome + ", teamAway=" + teamAway
				+ ", scoreHome=" + scoreHome + ", scoreAway=" + scoreAway
				+ ", dateTime=" + dateTime + ", city=" + city + ", stadium="
				+ stadium + ", betHome=" + betHome + ", betAway=" + betAway
				+ ", betPoints=" + betPoints + "]";
	}

}
