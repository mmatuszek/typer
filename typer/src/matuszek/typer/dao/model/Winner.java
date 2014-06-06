package matuszek.typer.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "winner")
public class Winner {

	@Id
	@Column(name = "id")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deadline")
	private Date deadline;
	@OneToOne(optional = false)
    @JoinColumn(name = "team_name") 
	private Team team;
	public int getId() {
		return id;
	}
	public Date getDeadline() {
		return deadline;
	}
	public Team getTeam() {
		return team;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "WinnerInfo [id=" + id + ", deadline=" + deadline + ", team="
				+ team + "]";
	}
}
