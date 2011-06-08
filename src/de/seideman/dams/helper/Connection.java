package de.seideman.dams.helper;

import java.util.List;

import de.seideman.dams.persistence.Cable;
import de.seideman.dams.persistence.CableInterface;


public class Connection {

	private List<CableInterface> interfaces;
	private Cable cable;
	public List<CableInterface> getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(List<CableInterface> interfaces) {
		this.interfaces = interfaces;
	}
	public Cable getCable() {
		return cable;
	}
	public void setCable(Cable cable) {
		this.cable = cable;
	}
	
	
	
	
}
