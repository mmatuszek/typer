package matuszek.typer.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import matuszek.typer.model.UserScore;

@Path("/standings")
public class StandingsService {

	@GET
	public List<UserScore> getStandings() {
		UserScore us = new UserScore();
		us.firstName = "Michal";
		us.lastName = "Matuszek";
		us.points = 15;
		us.scoreMatches = 3;
		us.scoreTypeMatches = 1;
		List<UserScore> list = new ArrayList<UserScore>();
		list.add(us);
		return list;
	}
}
