package matuszek.typer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import matuszek.typer.dao.MatchDao;
import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.Match;
import matuszek.typer.dao.model.factory.BetFactory;
import matuszek.typer.model.BetEntry;
import matuszek.typer.model.MatchEntry;
import matuszek.typer.model.factory.MatchEntryFactory;

@Stateless
@Path("/match")
public class MatchBetController {

	@EJB
	private MatchDao dao;	
	
	@GET
	@Produces("application/json")
	public List<MatchEntry> getMatches(@Context SecurityContext context) {
		
		List<MatchEntry> returnedList = new ArrayList<MatchEntry>();
		
		for (Match m : dao.getMatches()) {
			returnedList.add(MatchEntryFactory.createEntry(m, context.getUserPrincipal().getName()));
		}

		return returnedList;
		
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public MatchEntry getMatch(@PathParam("id") String id, @Context SecurityContext ctx) {
		return MatchEntryFactory.createEntry(dao.getMatch(Integer.valueOf(id)), ctx.getUserPrincipal().getName());
	}
	
	@PUT
	@Path("/{id}/bet")
	@Produces("application/json")
	public BetEntry updateBet(@PathParam("id") Integer matchId, @Context SecurityContext context, BetEntry betEntry) {
		
		Match match = dao.getMatch(matchId);
		
		if (match.getDateTime().before(new Date())) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Typ odrzucony. Mecz już się rozpoczął.").build());
		}
		
		Bet bet = BetFactory.createBet(betEntry, context.getUserPrincipal().getName(), matchId);		
		dao.updateBet(bet);
		
		return betEntry;
	}
}
