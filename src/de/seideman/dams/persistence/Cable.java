package de.seideman.dams.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sap_net_cables database table.
 * 
 */
@Entity
@Table(name="sap_net_cables")
public class Cable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=100)
	private String color;

	@Column(length=100)
	private String comment;

	@Column(length=100)
	private String connector1;

	@Column(length=100)
	private String connector2;

	@Column(length=20)
	private String ddi;

	@Column(length=20)
	private String ddu;

	@Id
	@Column(nullable=false)
	private int id;

	@Column(precision=53)
	private double length;

	@Column(length=100)
	private String name;

	@Column(length=100)
	private String type;

	@Column(name="virtual_id")
	private int virtualId;

    public Cable() {
    }

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getConnector1() {
		return this.connector1;
	}

	public void setConnector1(String connector1) {
		this.connector1 = connector1;
	}

	public String getConnector2() {
		return this.connector2;
	}

	public void setConnector2(String connector2) {
		this.connector2 = connector2;
	}

	public String getDdi() {
		return this.ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public String getDdu() {
		return this.ddu;
	}

	public void setDdu(String ddu) {
		this.ddu = ddu;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVirtualId() {
		return this.virtualId;
	}

	public void setVirtualId(int virtualId) {
		this.virtualId = virtualId;
	}

}