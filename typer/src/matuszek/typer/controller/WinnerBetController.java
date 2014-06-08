package matuszek.typer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import matuszek.typer.dao.WinnerDao;
import matuszek.typer.dao.model.Team;
import matuszek.typer.dao.model.Winner;
import matuszek.typer.dao.model.WinnerBet;
import matuszek.typer.dao.model.factory.WinnerBetFactory;
import matuszek.typer.model.TeamEntry;
import matuszek.typer.model.WinnerBetData;
import matuszek.typer.model.WinnerBetEntry;
import matuszek.typer.utils.WinnerPointsCalculator;

@Stateless
@Path("/winner/bet")
public class WinnerBetController {

	@EJB
	private WinnerDao dao;

	@GET
	@Produces("application/json")
	public WinnerBetData getWinnerBet(@Context SecurityContext ctx) {

		Winner winnerDao = dao.getWinner();
		Date deadline = winnerDao.getDeadline();
		TeamEntry teamBet = getTeamBet(ctx.getUserPrincipal().getName());
		TeamEntry winner = winnerDao.getTeam() != null ? new TeamEntry(
				winnerDao.getTeam().getName()) : null;

		List<TeamEntry> teams = new ArrayList<TeamEntry>();

		for (Team team : dao.getTeams()) {
			teams.add(new TeamEntry(team.getName()));
		}

		return new WinnerBetData().bet(teamBet).deadline(deadline).teams(teams)
				.winner(winner);

	}

	@GET
	@Path("/all")
	@Produces("application/json")
	public List<WinnerBetEntry> getAllBets() {

		if (dao.getWinner().getDeadline().after(new Date())) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST)
							.entity("Typy innych graczy dostępne po rozpoczęciu turnieju.")
							.build());
		}

		Winner winner = dao.getWinner();

		List<WinnerBetEntry> list = new ArrayList<>();

		for (WinnerBet bet : dao.getWinnerBets()) {
			list.add(new WinnerBetEntry(bet.getTeam().getName(), bet.getUser()
					.getUsername(), WinnerPointsCalculator
					.calculateWinnerPoints(bet.getTeam(), winner.getTeam())));
		}

		return list;

	}

	@PUT
	@Produces("application/json")
	public TeamEntry updateWinnerBet(@Context SecurityContext ctx,
			TeamEntry team) {

		if (dao.getWinner().getDeadline().before(new Date())) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST)
							.entity("Typ odrzucony. Termin przyjmowania tego zakładu minął.")
							.build());
		}

		dao.updateBet(WinnerBetFactory.create(team, ctx.getUserPrincipal()
				.getName()));
		return team;
	}

	private TeamEntry getTeamBet(String username) {

		for (WinnerBet bet : dao.getWinnerBets()) {
			if (bet.getUser().getUsername().equals(username)) {
				return new TeamEntry(bet.getTeam().getName());
			}
		}

		return null;

	}
}
