package matuszek.typer.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import matuszek.typer.dao.model.Match;

@Stateless
public class MatchDao {

	@PersistenceContext(unitName = "Typer")
	private EntityManager em;
	
	public List<Match> getMatches() {
		TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m", Match.class);
		return query.getResultList();
	}
}
