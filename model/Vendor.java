package inventarium.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import inventarium.utils.Address;
import inventarium.utils.Status;

/**
 * Vendor model represents a vendor in the
 * inventory system who provides products
 * @author Meredith Hoffman
 */

public class Vendor {
	private final IntegerProperty uniqueId;
	private final StringProperty name;
	private final StringProperty status;
	private final StringProperty description;
	private final StringProperty contactName;
	private final StringProperty email;
	private final StringProperty phone;
	
	Address address;

	/**
	 * @param name
	 * @param status
	 */
	public Vendor() {
		this(null,null);
	}
	
	/**
	 * @param name
	 * @param description
	 */
	public Vendor(String name, String description) {
		this.uniqueId = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(Status.ACTIVE.name());
		this.description = new SimpleStringProperty(description);
		this.contactName = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.phone = new SimpleStringProperty();
		this.address = new Address("","","","","");
	}

	/**
	 * @param uniqueId
	 * @param name
	 * @param status
	 * @param description
	 * @param contactName
	 * @param email
	 * @param phone
	 * @param address
	 */
	public Vendor(int uniqueId, String name, String description, String contactName, String phone, Address address, String email, Status status) {
		this.uniqueId = new SimpleIntegerProperty(uniqueId);
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(status.name());
		this.description = new SimpleStringProperty(description);
		this.contactName = new SimpleStringProperty(contactName);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.address = address;
	}

	public IntegerProperty uniqueIdProperty() {
		return this.uniqueId;
	}

	public int getUniqueId() {
		return this.uniqueIdProperty().get();
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueIdProperty().set(uniqueId);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}

	public void setName(String name) {
		this.nameProperty().set(name);
	}

	public StringProperty statusProperty() {
		return this.status;
	}

	public Status getStatus() {
		return Status.valueOf(this.statusProperty().get());
	}

	public void setStatus(Status status) {
		this.statusProperty().set(status.name());
	}

	public StringProperty descriptionProperty() {
		return this.description;
	}

	public String getDescription() {
		return this.descriptionProperty().get();
	}

	public void setDescription(String description) {
		this.descriptionProperty().set(description);
	}

	public StringProperty contactNameProperty() {
		return this.contactName;
	}

	public String getContactName() {
		return this.contactNameProperty().get();
	}

	public void setContactName(String contactName) {
		this.contactNameProperty().set(contactName);
	}

	public StringProperty emailProperty() {
		return this.email;
	}

	public String getEmail() {
		return this.emailProperty().get();
	}

	public void setEmail(String email) {
		this.emailProperty().set(email);
	}

	public StringProperty phoneProperty() {
		return this.phone;
	}

	public String getPhone() {
		return this.phoneProperty().get();
	}

	public void setPhone(String phone) {
		this.phoneProperty().set(phone);
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
