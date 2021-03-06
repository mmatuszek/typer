package matuszek.typer.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import matuszek.typer.dao.model.Bet;
import matuszek.typer.dao.model.Match;

@Stateless
public class MatchDao {

	@PersistenceContext(unitName = "Typer")
	private EntityManager em;

	public List<Match> getMatches(String status) {

		em.clear();
		
		em.getEntityManagerFactory().getCache().evictAll();
		TypedQuery<Match> query;
		
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());

		if (status.equals("past")) {
			String strQuery = "SELECT m FROM Match m WHERE m.dateTime < '%s' AND m.scoreHome IS NOT NULL AND m.scoreAway IS NOT NULL ORDER BY m.dateTime DESC";
			query = em.createQuery(String.format(strQuery, date), Match.class);
		} else if (status.equals("current")) {
			String strQuery = "SELECT m FROM Match m WHERE m.dateTime < '%s' AND m.scoreHome IS NULL AND m.scoreAway IS NULL ORDER BY m.dateTime ASC";
			query = em.createQuery(String.format(strQuery, date), Match.class);
		} else if (status.equals("future")) {
			String strQuery = "SELECT m FROM Match m WHERE m.dateTime >= '%s' ORDER BY m.dateTime ASC";
			query = em.createQuery(String.format(strQuery, date), Match.class);
		} else {
			query = em.createQuery("SELECT m FROM Match m", Match.class);
		}

		return query.getResultList();

	}
	
	public Match getMatch(Integer matchId) {

		em.clear();
		
		em.getEntityManagerFactory().getCache().evictAll();
		
		TypedQuery<Match> query = em.createQuery(
				"SELECT m FROM Match m WHERE m.id = :matchId", Match.class);
		query.setParameter("matchId", matchId);

		return query.getSingleResult();

	}

	public void updateBet(Bet bet) {

		em.clear();
		
		TypedQuery<Bet> query = em
				.createQuery(
						"SELECT b FROM Bet b WHERE b.user = :user AND b.matchId = :matchId",
						Bet.class);
		query.setParameter("user", bet.getUser());
		query.setParameter("matchId", bet.getMatchId());

		List<Bet> list = query.getResultList();
		if (list.size() > 1) {
			throw new IllegalStateException("Too many bet records returned: "
					+ list);
		}

		if (list.size() == 1) {
			Bet existingBet = list.get(0);
			existingBet.setBetHome(bet.getBetHome());
			existingBet.setBetAway(bet.getBetAway());
			em.merge(existingBet);
		} else {
			em.merge(bet);
		}
		
		em.flush();

	}
}
