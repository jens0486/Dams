package de.seideman.dams.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sap_net_interfaces database table.
 * 
 */
@Entity
@Table(name="sap_net_interfaces")
public class CableInterface implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=100)
	private String bus;

	@Column(name="cable_connector")
	private int cableConnector;

	@Column(name="cable_id")
	private int cableId;

	@Column(length=100)
	private String connector;

	@Id
	@Column(nullable=false)
	private int id;

	@Column(length=180)
	private String ip;

	@Column(name="logical_name", length=100)
	private String logicalName;

	@Column(length=180)
	private String mask;

	@Column(length=100)
	private String name;

	@Column(name="object_id", length=100)
	private String objectId;

	@Column(length=100)
	private String panel;

	@Column(length=100)
	private String port;

	@Column(length=100)
	private String slot;

	@Column(length=180)
	private String vlan;

    public CableInterface() {
    }

	public String getBus() {
		return this.bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public int getCableConnector() {
		return this.cableConnector;
	}

	public void setCableConnector(int cableConnector) {
		this.cableConnector = cableConnector;
	}

	public int getCableId() {
		return this.cableId;
	}

	public void setCableId(int cableId) {
		this.cableId = cableId;
	}

	public String getConnector() {
		return this.connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogicalName() {
		return this.logicalName;
	}

	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	public String getMask() {
		return this.mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getPanel() {
		return this.panel;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSlot() {
		return this.slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getVlan() {
		return this.vlan;
	}

	public void setVlan(String vlan) {
		this.vlan = vlan;
	}

}