package de.seideman.dams.stuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.seideman.dams.helper.Connection;
import de.seideman.dams.manager.CableManager;
import de.seideman.dams.manager.CableManagerLocal;
import de.seideman.dams.manager.ConnectionManager;
import de.seideman.dams.manager.ConnectionManagerLocal;
import de.seideman.dams.manager.LoginManager;
import de.seideman.dams.manager.LoginManagerLocal;
import de.seideman.dams.manager.ObjectManager;
import de.seideman.dams.manager.ObjectManagerLocal;
import de.seideman.dams.persistence.Cable;
import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.PersistenceManager;
import de.seideman.dams.persistence.SapObject;

@Path("/test")
public class DBTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	public DBTest() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	@GET
	@Produces({ MediaType.TEXT_HTML })
	@Path("/hello")
	public String getHello() {
		
		LoginManagerLocal lg = new LoginManager();
		
		
		
		return lg.login("j_seidemann", "test").toString();
		
		
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/cables/{hostname}/{location}")
	public String getAllCables(@PathParam("hostname") String hostname,
			@PathParam("location") String location) {

		ObjectManagerLocal om = new ObjectManager();
		JSONObject j = new JSONObject();
		JSONArray ja = new JSONArray();

		int z = 0;

		try {
			List<SapObject> c = om.getObjectByHostname(hostname, location);

			j.put("size", c.size());

			for (SapObject i : c) {
				JSONObject temp = new JSONObject();

				temp.put("result", true);
				temp.put("hostname", i.getObjectHostname());
				temp.put("objectId", i.getObjectId());
				ja.put(z++, temp);

			}

			j.put("objects", ja);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoResultException ex) {
			try {
				j.put("result", false);
				j.put("failure", ex);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return j.toString();

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/objects/{cableNumber}")
	public String getObjects(@PathParam("cableNumber") String cableNumber) {

		ConnectionManagerLocal com = new ConnectionManager();
		JSONObject j = new JSONObject();
		JSONArray ja = new JSONArray();

		int z = 0;

		try {

			List<Connection> c = com.getConnection(cableNumber);

			j.put("size", c.size());

			for (Connection i : c) {
				JSONObject temp = new JSONObject();

				
				for(CableInterface ci:i.getInterfaces()){
					temp.put("interface"+z, ci.getName());
					temp.put("cable", i.getCable().getName());
				}
				
				ja.put(z++, temp);

			}

			j.put("connections", ja);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoResultException ex) {
			try {
				j.put("result", false);
				j.put("failure", ex);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return j.toString();

	}
}
