package de.seideman.dams.manager;

import java.util.List;

import de.seideman.dams.helper.Connection;
import de.seideman.dams.persistence.CableInterface;
import de.seideman.dams.persistence.SapObject;


public interface ConnectionManagerLocal {

	public List<Connection> getConnection(String cableName);
	
	
}
