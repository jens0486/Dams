package de.seideman.dams.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.seideman.dams.persistence.Cable;
import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.PersistenceManager;

public class InterfaceManager implements InterfaceManagerLocal {

	private EntityManagerFactory emf;
	private EntityManager em;

	public InterfaceManager() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	public CableInterface getInterfaceById(int id) throws NoResultException {
		Query q = em
				.createQuery("Select i FROM CableInterface i WHERE c.id= :arg1 ");
		q.setParameter("arg1", id);

		CableInterface result = (CableInterface) q.getSingleResult();
		return result;
	}

	public List<CableInterface> getAllInterfaces() throws NoResultException {
		Query q = em.createQuery("Select i FROM CableInterface i ");

		List<CableInterface> resultList = q.getResultList();

		return resultList;
	}

	public List<CableInterface> getInterfacesByObjectId(String objectId)
			throws NoResultException {
		Query q = em
				.createQuery("Select i FROM CableInterface i WHERE objectId = :arg1");
		q.setParameter("arg1", objectId);

		List<CableInterface> resultList = q.getResultList();

		return resultList;

	}

	public List<CableInterface> getInterfaceByCableId(int cableId)
			throws NoResultException {
		Query q = em
				.createQuery("Select i FROM CableInterface i WHERE i.cableId = :arg1");
		q.setParameter("arg1", cableId);

		List<CableInterface> resultList = q.getResultList();

		return resultList;
	}

	public List<CableInterface> getInterfaceByCableName(String cableName)
			throws NoResultException {
		Query q = em
				.createQuery("Select i FROM CableInterface i, Cable c WHERE c.name= :arg1 AND c.id = i.cableId");
		q.setParameter("arg1", cableName);

		List<CableInterface> resultList = q.getResultList();

		return resultList;
	}

}
