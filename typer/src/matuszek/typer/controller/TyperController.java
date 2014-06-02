package matuszek.typer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import matuszek.typer.dao.MatchDao;
import matuszek.typer.dao.model.Match;
import matuszek.typer.model.MatchEntry;
import matuszek.typer.model.factory.MatchEntryFactory;

@Stateless
@Path("/match")
public class TyperController {

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
}
