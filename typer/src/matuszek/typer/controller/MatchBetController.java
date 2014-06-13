package matuszek.typer.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import matuszek.typer.model.factory.BetEntryFactory;
import matuszek.typer.model.factory.MatchEntryFactory;

@Stateless
@Path("/match")
public class MatchBetController {

	@EJB
	private MatchDao dao;

	@GET
	@Path("/all")
	@Produces("application/json")
	public List<MatchEntry> getMatches(@Context SecurityContext context,
			@QueryParam("status") String status) {

		try {

			List<MatchEntry> returnedList = new ArrayList<MatchEntry>();

			for (Match m : dao.getMatches(status)) {
				returnedList.add(MatchEntryFactory.createEntry(m, context
						.getUserPrincipal().getName()));
			}

			return returnedList;

		} catch (WebApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new WebApplicationException(Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Błąd: " + e.getLocalizedMessage()).build());
		}

	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public MatchEntry getMatch(@PathParam("id") String id,
			@Context SecurityContext ctx) {

		try {
			return MatchEntryFactory.createEntry(dao.getMatch(Integer
					.valueOf(id)), ctx.getUserPrincipal().getName());
		} catch (WebApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new WebApplicationException(Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Błąd: " + e.getLocalizedMessage()).build());
		}

	}

	@GET
	@Path("/{id}/bet/all")
	@Produces("application/json")
	public List<BetEntry> getAllBets(@PathParam("id") Integer matchId,
			@Context SecurityContext context) {

		try {

			Match match = dao.getMatch(matchId);

			if (match.getDateTime().after(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime())) {
				throw new WebApplicationException(
						Response.status(Response.Status.BAD_REQUEST)
								.entity("Typy innych graczy są dostępne dopiero po rozpoczęciu meczu")
								.build());
			}

			return BetEntryFactory.createBetList(match.getBetList(), match);

		} catch (WebApplicationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Błąd: " + e.getLocalizedMessage()).build());
		}

	}

	@PUT
	@Path("/{id}/bet")
	@Produces("application/json")
	public MatchEntry updateBet(@PathParam("id") Integer matchId,
			@Context SecurityContext context, BetEntry betEntry) {

		try {

			Match match = dao.getMatch(matchId);

			if (match.getDateTime().before(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime())) {
				throw new WebApplicationException(Response
						.status(Response.Status.BAD_REQUEST)
						.entity("Typ odrzucony. Mecz już się rozpoczął.")
						.build());
			}

			Bet bet = BetFactory.createBet(betEntry, context.getUserPrincipal()
					.getName(), matchId);

			dao.updateBet(bet);

			return MatchEntryFactory.createEntry(dao.getMatch(matchId), context.getUserPrincipal().getName());

		} catch (WebApplicationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Błąd: " + e.getLocalizedMessage()).build());
		}
		
	}
}
