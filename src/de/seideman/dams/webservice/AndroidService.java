package de.seideman.dams.webservice;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import de.seideman.dams.helper.Connection;
import de.seideman.dams.manager.CableManager;
import de.seideman.dams.manager.CableManagerLocal;
import de.seideman.dams.manager.ConnectionManager;
import de.seideman.dams.manager.ConnectionManagerLocal;
import de.seideman.dams.manager.InterfaceManager;
import de.seideman.dams.manager.InterfaceManagerLocal;
import de.seideman.dams.manager.LoginManager;
import de.seideman.dams.manager.LoginManagerLocal;
import de.seideman.dams.manager.ObjectManager;
import de.seideman.dams.manager.ObjectManagerLocal;
import de.seideman.dams.persistence.Cable;
import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.SapObject;

@Path("/android")
public class AndroidService {

	private CableManagerLocal cm;
	private InterfaceManagerLocal im;
	private ObjectManagerLocal om;
	private ConnectionManagerLocal com;
	private LoginManagerLocal lm;

	
	//Browser-Tester
	@GET
	@Produces({ MediaType.TEXT_HTML })
	@Path("/hello/{cable}")
	public String hello(@PathParam("cable") String cable) {
		com = new ConnectionManager();
		om = new ObjectManager();

		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();

		try {
			List<Connection> connect = com.getConnection(cable);
			for (Connection c : connect) {
				JSONObject temp = new JSONObject();
				JSONArray intArray = new JSONArray();
				temp.put("cable", c.getCable().getName());

				for (CableInterface i : c.getInterfaces()) {
					System.out.println(i.getId());

					JSONObject inter = new JSONObject();
					inter.put("intPanel", i.getPanel());
					inter.put("intPort", i.getPort());
					inter.put("intName", i.getName());
					// Object with Interface i
					SapObject obj = om.getObjectByInterface(i);
					inter.put("intObject", formJsonSingle(obj));
					// two Interfaces with including Objects
					intArray.put(inter);
				}
				temp.put("interfaces", intArray);
				jArray.put(temp);
			}
			json.put("result", true);
			json.put("connections", jArray);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoResultException nex) {
			try {
				json.put("result", false);
				json.put("failure",
						"Es konnte kein passendes Kabel gefunden werden!");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return json.toString();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/connection")
	public String getConnection(@FormParam("cableName") String cableName) {
		com = new ConnectionManager();
		om = new ObjectManager();

		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();

		try {
			List<Connection> connect = com.getConnection(cableName);
			for (Connection c : connect) {
				JSONObject temp = new JSONObject();
				JSONArray intArray = new JSONArray();
				temp.put("cable", c.getCable().getName());

				for (CableInterface i : c.getInterfaces()) {
					System.out.println(i.getId());

					JSONObject inter = new JSONObject();
					inter.put("intPanel", i.getPanel());
					inter.put("intPort", i.getPort());
					inter.put("intName", i.getName());
					// Object with Interface i
					SapObject obj = om.getObjectByInterface(i);
					inter.put("intObject", formJsonSingle(obj));
					// two Interfaces with including Objects
					intArray.put(inter);
				}
				temp.put("interfaces", intArray);
				jArray.put(temp);
			}
			json.put("result", true);
			json.put("connections", jArray);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoResultException nex) {
			try {
				json.put("result", false);
				json.put("failure",
						"Es konnte kein passendes Kabel gefunden werden!");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return json.toString();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/login")
	public String trylogin(@FormParam("user") String user, @FormParam("pass") String passHash) {
		lm = new LoginManager();
		JSONObject json = new JSONObject();
	
		System.out.println(passHash);
		
		try {
			if(lm.login(user, passHash)){		
				json.put("login", true);
				json.put("user", user);
			}else{
				json = formJsonFailure("Login nicht erfolgreich!");
			}
		} catch (JSONException e) {
			json = formJsonFailure("JSON-Fehler");
		}
		return json.toString();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/cableinfo")
	public String getCableInfo(@FormParam("cableName") String cableName) {
		cm = new CableManager();
		Cable cable;
		JSONObject json = new JSONObject();

		try {
			cable = cm.getCableByName(cableName);
			json.put("result", true);
			json.put("length", cable.getLength());
			json.put("name", cable.getName());
			json.put("color", cable.getColor());
			json.put("type", cable.getType());

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoResultException nex) {
			try {
				json.put("result", false);
				json.put("failure",
						"Es konnte kein passendes Kabel gefunden werden!");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return json.toString();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("objectinfo")
	public String getObjectInfo(@FormParam("paramType") String paramType,
			@FormParam("searchParam") String searchParam) {
		om = new ObjectManager();
		SapObject object;
		List<SapObject> objects;
		JSONObject json = null;
		int param = Integer.valueOf(paramType);
		System.out.print("objektinfo");
		switch (param) {
		case 0: // Inventory Number
			json = new JSONObject();
			try {
				object = om.getObjectByInventory(searchParam);
				json = formJsonSingle(object);
			} catch (NoResultException nex) {
				json = formJsonFailure("Es konnte kein Objekt mit dieser Inventarnummer gefunden werden!");
			}
			break;
		case 1: // SerialNumber
			json = new JSONObject();
			try {
				object = om.getObjectBySerial(searchParam);
				json = formJsonSingle(object);
			} catch (NoResultException nex) {
				json = formJsonFailure("Es konnte kein Objekt mit dieser Seriennummer gefunden werden!");
			}
			break;
		case 2: // IP-Adresse
			json = new JSONObject();
			 try {
				 object = om.getObjectByIP(searchParam);
				 json = formJsonSingle(object);
			 } catch (NoResultException nex) {
			 json =
			 formJsonFailure("Es konnte kein Objekt mit dieser IP-Adresse gefunden werden!");
			 }
			break;
		case 3: // Hostname
			json = new JSONObject();
			try {
				objects = (List<SapObject>) om.getObjectByHostname(searchParam);
				json = formJsonMultiple(objects);
			} catch (NoResultException nex) {
				json = formJsonFailure("Es konnte kein Objekt mit diesem Hostnamen gefunden werden!");
			}

			objects = (List<SapObject>) om.getObjectBySerial(searchParam);
			json = formJsonMultiple(objects);
			break;
		case 4: // MacAdresse
			json = new JSONObject();
			try {
				object = om.getObjectByMac(searchParam);
				json = formJsonSingle(object);
			} catch (NoResultException nex) {
				json = formJsonFailure("Es konnte kein Objekt mit dieser MAC-Adresse gefunden werden!");
			}
			break;
		case 5: // freie Suche
			json = new JSONObject();
			json = formJsonFailure("default, freie Suche");
			break;
		default:
			json = new JSONObject();
			json = formJsonFailure("Falsche Parameter");
		}

		return json.toString();
	}

	private JSONObject formJsonFailure(String failure) {
		JSONObject json = new JSONObject();

		try {
			json.put("result", false);
			json.put("failure", failure);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;

	}

	private JSONObject formJsonSingle(SapObject object) {
		JSONObject json = new JSONObject();

		try {
			json.put("result", true);
			if(object.getObjectHostname()!=null){
				json.put("hostname", object.getObjectHostname());
			}else json.put("hostname", "-");
			
			if(object.getObjectHeight()!=null){
				json.put("height", object.getObjectHeight());
			}else json.put("height", "-");
			
			if(object.getObjectId()!=null){
				json.put("objectId", object.getObjectId());
			}else json.put("objectId", "-");
			
			if(object.getObjectInventory()!=null){
				json.put("inventory", object.getObjectInventory());
			}else json.put("inventory", "-");
			
			if(object.getObjectLocation()!=null){
				json.put("location", object.getObjectLocation());
			}else json.put("location", "-");
			
			if(object.getObjectLocationRack()!=null){
				json.put("locationRack", object.getObjectLocationRack());
			}else json.put("locationRack", "-");
			
			if(object.getObjectManufacturer()!=null){
				json.put("manufacturer", object.getObjectManufacturer());
			}else json.put("manufacturer", "-");
			
			if(object.getObjectModell()!=null){
				json.put("modell", object.getObjectModell());
			}else json.put("modell", "-");
			
			if( object.getObjectParentRz()!=null){
				json.put("parentRz",  object.getObjectParentRz());
			}else json.put("parentRz", "-");
			
			if( object.getObjectSerial()!=null){
				json.put("serial",  object.getObjectSerial());
			}else json.put("serial", "-");
			
			if( object.getObjectStatus()!=null){
				json.put("status",  object.getObjectStatus());
			}else json.put("status", "-");
			
			if( object.getObjectType()!=null){
				json.put("type",  object.getObjectType());
			}else json.put("type", "-");
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	private JSONObject formJsonMultiple(List<SapObject> objects) {
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
		int z = 0;

		try {
			json.put("size", objects.size());

			for (SapObject o : objects) {
				jarray.put(z++, formJsonSingle(o));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/objectlocation")
	public String getObjectLocation(String searchParam) {
		// TODO Auto-generated method stub
		return null;
	}

}
