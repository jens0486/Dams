package de.seideman.dams.manager;

import java.util.List;

import de.seideman.dams.persistence.Cable;

public interface CableManagerLocal {

	public Cable getCableByName(String name);
	public List<Cable> getAllCables();
	public Cable getCableById(int id);
	
		
}
