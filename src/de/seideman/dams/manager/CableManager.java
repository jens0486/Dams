package de.seideman.dams.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.seideman.dams.persistence.Cable;
import de.seideman.dams.persistence.PersistenceManager;

public class CableManager implements CableManagerLocal {

	private EntityManagerFactory emf;
	private EntityManager em;

	public CableManager() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	public Cable getCableByName(String name) throws NoResultException {
		Query q = em.createQuery("Select c FROM Cable c WHERE c.name= :arg1 ");
		q.setParameter("arg1", name);

		Cable result = (Cable) q.getSingleResult();

		return result;
	}

	@Override
	public List<Cable> getAllCables() throws NoResultException {
		Query q = em.createQuery("Select c FROM Cable c ");

		List<Cable> resultList = q.getResultList();

		return resultList;
	}

	@Override
	public Cable getCableById(int id) throws NoResultException {
		Query q = em.createQuery("Select c FROM Cable c WHERE c.id= :arg1 ");
		q.setParameter("arg1", id);

		Cable result = (Cable) q.getSingleResult();

		return result;
	}

}
