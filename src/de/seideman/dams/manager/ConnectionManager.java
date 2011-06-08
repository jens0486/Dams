package de.seideman.dams.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.codehaus.jettison.json.JSONObject;

import de.seideman.dams.helper.Connection;
import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.PersistenceManager;
import de.seideman.dams.persistence.SapObject;

public class ConnectionManager implements ConnectionManagerLocal {

	private EntityManagerFactory emf;
	private EntityManager em;

	private InterfaceManagerLocal im;
	private CableManagerLocal cm;
	private ObjectManagerLocal om;
	private List<Connection> list;

	public ConnectionManager() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
		im = new InterfaceManager();
		cm = new CableManager();
		om = new ObjectManager();
		list = new ArrayList<Connection>();
	}

	
	
	//get List of Connections
	public List<Connection> getConnection(String cableName) throws NoResultException {
		List<CableInterface> interfaces = new ArrayList<CableInterface>();
		List<Connection> connections = new ArrayList<Connection>();
		Stack<String> stack = new Stack<String>();
		HashMap<Integer,List<CableInterface>> map = new HashMap<Integer,List<CableInterface>>();
		
		if (cm.getCableByName(cableName) != null) {
			stack.push(cableName);
		}
		
		while (!stack.isEmpty()){
			String name = stack.pop();
			int cableId = cm.getCableByName(name).getId();
			interfaces = im.getInterfaceByCableName(name);
			
			System.out.println("int: "+interfaces.size());
			
			if(!map.containsKey(cableId)){
				map.put(cableId, interfaces);
				
				for (CableInterface i:interfaces){
					SapObject obj = om.getObjectByInterface(i);
					System.out.println(obj.getObjectType());
					if (obj.getObjectType().contains("Patchpanel") && name.contains("P-")){
						List<String> list = findCable(i.getId()+1);
						for (String s:list){
							if(s!=null)stack.push(s);
						}
						System.out.println("stack: "+stack.size());
					}
					if (obj.getObjectType().contains("Patchpanel") && name.contains("I-")){
						List<String> list = findCable(i.getId()-1);
						for (String s:list){
							if(s!=null)stack.push(s);
						}
						System.out.println("stack: "+stack.size());
					}
				}
			}
		}
		
				
		
		for (int i:map.keySet()){
			Connection con = new Connection();
			con.setCable(cm.getCableById(i));
			con.setInterfaces(map.get(i));
			connections.add(con);
		}
		
		
		return connections;
	}


	private List<String> findCable(int interfaceId) throws NoResultException {
		Query q1 = em
				.createQuery("Select c.name FROM CableInterface i, Cable c Where c.id = i.cableId AND i.id = :arg1");
		q1.setParameter("arg1", interfaceId);

		List<String> list = q1.getResultList();

		return list;
	}

}
