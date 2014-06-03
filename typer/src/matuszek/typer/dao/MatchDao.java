package matuszek.typer.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.Match;

@Stateful
public class MatchDao {

	@PersistenceContext(unitName = "Typer")
	private EntityManager em;
	
	public List<Match> getMatches() {
		TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m", Match.class);
		return query.getResultList();
	}
	
	public void updateBet(Bet bet) {
		TypedQuery<Bet> query = em.createQuery("SELECT b FROM Bet b WHERE b.user = :user AND b.matchId = :matchId", Bet.class);
		query.setParameter("user", bet.getUser());
		query.setParameter("matchId", bet.getMatchId());
		
		List<Bet> list = query.getResultList();
		if (list.size() > 1) {
			throw new IllegalStateException("Too many bet records returned: " + list);
		}
		
		if (list.size() == 1) {
			Bet existingBet = list.get(0);
			existingBet.setBetHome(bet.getBetHome());
			existingBet.setBetAway(bet.getBetAway());
			em.persist(existingBet);
		} else {
			em.persist(bet);
		}		

	}
}
