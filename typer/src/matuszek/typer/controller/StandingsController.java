package matuszek.typer.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import matuszek.typer.dao.MatchDao;
import matuszek.typer.dao.UserDao;
import matuszek.typer.dao.WinnerDao;
import matuszek.typer.dao.model.UserStanding;
import matuszek.typer.dao.model.Winner;
import matuszek.typer.model.StandingEntry;
import matuszek.typer.model.factory.StandingEntryFactory;

@Stateless
@Path("/standings")
public class StandingsController {

	@EJB
	private MatchDao matchDao;
	@EJB
	private WinnerDao winnerDao;
	@EJB
	private UserDao userDao;

	@GET
	@Produces("application/json")
	public List<StandingEntry> getStandings() {
		
		List<StandingEntry> list = new ArrayList<>();
		
		List<UserStanding> userList = userDao.getUsers();
		Winner winner = winnerDao.getWinner();
		
		for (UserStanding u : userList) {			
			list.add(StandingEntryFactory.createStandingEntry(u, winner));			
		}
		
		Collections.sort(list, new Comparator<StandingEntry>() {

			@Override
			public int compare(StandingEntry o1, StandingEntry o2) {
				return o2.getTotalPoints() - o1.getTotalPoints();
			}
			
		});
		
		return list;
		
	}
}
