package matuszek.typer.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import matuszek.typer.dao.model.UserStanding;

@Stateful
public class UserDao {

	@PersistenceContext(unitName = "Typer")
	private EntityManager em;
	
	public List<UserStanding> getUsers() {
		TypedQuery<UserStanding> query = em.createQuery("SELECT u FROM UserStanding u", UserStanding.class);
		return query.getResultList();
	}

	
}
