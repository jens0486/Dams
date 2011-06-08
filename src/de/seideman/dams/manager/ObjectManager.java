package de.seideman.dams.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.PersistenceManager;
import de.seideman.dams.persistence.SapObject;

public class ObjectManager implements ObjectManagerLocal {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ObjectManager() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	@Override
	public List<SapObject> getAllObjects() throws NoResultException {
		Query q = em.createQuery("Select o FROM SapObject o ");

		List<SapObject> resultList = q.getResultList();

		return resultList;
	}

	@Override
	public List<SapObject> getObjectByType(String objectType)
			throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o WHERE objectType = :arg1");
		q.setParameter("arg1", objectType);

		List<SapObject> resultList = q.getResultList();

		return resultList;
	}

	@Override
	public SapObject getObjectBySerial(String serial) throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o WHERE o.objectSerial= :arg1 AND NOT o.objectStatus='Removed'");
		q.setParameter("arg1", serial);

		SapObject result = (SapObject) q.getSingleResult();
		return result;
	}

	@Override
	public SapObject getObjectByInventory(String inventory)
			throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o WHERE o.objectInventory = :arg1 AND o.objectLocation NOT LIKE :arg2 AND o.objectStatus NOT LIKE :arg3");
		q.setParameter("arg1", inventory);
		q.setParameter("arg2", "TSP%");
		q.setParameter("arg3", "Removed");

		SapObject result = (SapObject) q.getSingleResult();
		return result;
	}

	public List<SapObject> getObjectByHostname(String hostname, String location)
			throws NoResultException {
		Query q = em
				.createQuery("Select DISTINCT o FROM SapObject o WHERE o.objectHostname LIKE :arg1 AND o.objectLocation LIKE :arg2 AND NOT o.objectStatus='Removed'");
		q.setParameter("arg1", "%" + hostname + "%");
		q.setParameter("arg2", location + "%");

		List<SapObject> resultList = q.getResultList();
		return resultList;
	}

	public List<SapObject> getObjectByHostname(String hostname)
			throws NoResultException {
		Query q = em
				.createQuery("Select DISTINCT o FROM SapObject o WHERE o.objectHostname LIKE :arg1 AND NOT o.objectStatus='Removed'");

		q.setParameter("arg1", "%" + hostname + "%");
		List<SapObject> resultList = q.getResultList();
		return resultList;
	}

	public SapObject getObjectById(String objectId) throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o WHERE o.objectId = :arg1 ");
		q.setParameter("arg1", objectId);

		SapObject result = (SapObject) q.getSingleResult();
		return result;
	}

	@Override
	public SapObject getObjectByInterface(CableInterface cableInterface)
			throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o, CableInterface i WHERE i.id = :arg1 AND o.objectId = i.objectId AND NOT o.objectStatus='Removed'");
		q.setParameter("arg1", cableInterface.getId());
		return (SapObject) q.getSingleResult();
	}

	public SapObject getObjectByIP(String ipAdresse) throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o, CableInterface i WHERE i.ip LIKE :arg1 AND o.objectId = i.objectId AND NOT o.objectStatus='Removed'");
		q.setParameter("arg1", "%"+ipAdresse+"%");
		return (SapObject) q.getSingleResult();
	}

	public SapObject getObjectByMac(String macAdresse) throws NoResultException {
		Query q = em
				.createQuery("Select o FROM SapObject o, CableInterface i WHERE i.vlan = :arg1 AND o.objectId = i.objectId AND NOT o.objectStatus='Removed' ");
		q.setParameter("arg1", macAdresse);
		return (SapObject) q.getSingleResult();
	}

}
