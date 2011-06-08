package de.seideman.dams.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sap_objects database table.
 * 
 */

@Entity
@Table(name="sap_objects")
public class SapObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="A00", length=25)
	private String objectId;

	@Column(name="A01", length=17)
	private String objectStatus;

	@Column(name="A06", length=34)
	private String objectLocation;

	@Column(name="A08", length=21)
	private String objectType;

	@Column(name="A09", length=36)
	private String objectModell;

	@Column(name="A10", length=35)
	private String objectManufacturer;

	@Column(name="A11", length=20)
	private String objectSerial;

	@Column(name="A12", length=42)
	private String objectHostname;

	@Column(name="A34", length=13)
	private String objectLocationRack;

	@Column(name="A36", length=30)
	private String objectInventory;

	@Column(name="A37", length=12)
	private String objectHeight;

	@Column(name="PARENT_RZ", length=30)
	private String objectParentRz;

    public SapObject() {
    }

	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectID) {
		this.objectId = objectID;
	}

	public String getObjectStatus() {
		return this.objectStatus;
	}

	public void setObjectStatus(String status) {
		this.objectStatus = status;
	}

	public String getObjectLocation() {
		return this.objectLocation;
	}

	public void setObjectLocation(String location) {
		this.objectLocation = location;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectModell() {
		return this.objectModell;
	}

	public void setObjectModell(String objectModell) {
		this.objectModell = objectModell;
	}

	public String getObjectManufacturer() {
		return this.objectManufacturer;
	}

	public void setObjectManufacturer(String objectManufactor) {
		this.objectManufacturer = objectManufactor;
	}

	public String getObjectSerial() {
		return this.objectSerial;
	}

	public void setObjectSerial(String objectSerial) {
		this.objectSerial = objectSerial;
	}

	public String getObjectHostname() {
		return this.objectHostname;
	}

	public void setObjectHostname(String objectHostname) {
		this.objectHostname = objectHostname;
	}

	public String getObjectLocationRack() {
		return this.objectLocationRack;
	}

	public void setObjectLocationRack(String locationRack) {
		this.objectLocationRack = locationRack;
	}

	public String getObjectInventory() {
		return this.objectInventory;
	}

	public void setObjectInventory(String objectInventory) {
		this.objectInventory = objectInventory;
	}

	public String getObjectHeight() {
		return this.objectHeight;
	}

	public void setObjectHeight(String objectHeight) {
		this.objectHeight = objectHeight;
	}

	public String getObjectParentRz() {
		return this.objectParentRz;
	}

	public void setObjectParentRz(String parentRz) {
		this.objectParentRz = parentRz;
	}

}