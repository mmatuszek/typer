package matuszek.typer.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import matuszek.typer.dao.model.Team;
import matuszek.typer.dao.model.Winner;
import matuszek.typer.dao.model.WinnerBet;

@Stateful
public class WinnerDao {

	@PersistenceContext(unitName = "Typer")
	private EntityManager em;
	
	public List<Team> getTeams() {
		TypedQuery<Team> query = em.createQuery("SELECT t FROM Team t", Team.class);
		return query.getResultList();
	}
	
	public List<WinnerBet> getWinnerBets() {
		TypedQuery<WinnerBet> query = em.createQuery("SELECT wb FROM WinnerBet wb", WinnerBet.class);
		return query.getResultList();
	}
	
	public Winner getWinner() {
		TypedQuery<Winner> query = em.createQuery("SELECT w FROM Winner w", Winner.class);
		return query.getSingleResult();
	}
	
	public void updateBet(WinnerBet bet) {
		
		TypedQuery<WinnerBet> query = em.createQuery("SELECT wb FROM WinnerBet wb WHERE wb.user = :user", WinnerBet.class);
		query.setParameter("user", bet.getUser());
		
		List<WinnerBet> list = query.getResultList();
		if (list.size() > 1) {
			throw new IllegalStateException("Too many bet records returned: " + list);
		}
		
		if (list.size() == 1) {
			WinnerBet existingBet = list.get(0);
			existingBet.setTeam(bet.getTeam());
			em.persist(existingBet);
		} else {
			em.persist(bet);
		}
		
	}
}
