package de.seideman.dams.manager;

import java.util.List;

import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.SapObject;

public interface ObjectManagerLocal {

	
	public List<SapObject> getAllObjects();
	public List<SapObject> getObjectByType(String type);
	public List<SapObject> getObjectBySerial(String serial);
	public SapObject getObjectById(String id);
	public List<SapObject> getObjectByInventory(String inventory);
	public List<SapObject> getObjectByHostname(String hostname, String location);
	public List<SapObject> getObjectByHostname(String hostname);
	public SapObject getObjectByInterface(CableInterface cableInterface);
	public List<SapObject> getObjectByIP(String ipAdresse);	
	public List<SapObject> getObjectByMac(String macAdresse);
}
