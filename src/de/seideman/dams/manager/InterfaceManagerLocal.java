package de.seideman.dams.manager;

import java.util.List;

import javax.persistence.NoResultException;

import de.seideman.dams.persistence.CableInterface;

public interface InterfaceManagerLocal {
	
	public CableInterface getInterfaceById(int id);
	public List<CableInterface> getAllInterfaces();
	public List<CableInterface> getInterfacesByObjectId(String objectId);
	public List<CableInterface> getInterfaceByCableId (int cableId);
	public List<CableInterface> getInterfaceByCableName(String cableName);
	
	
}
