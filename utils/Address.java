package inventarium.utils;

/**
 * Address represents an address of a vendor
 * in the inventory system
 * @author Meredith Hoffman
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {
	private final StringProperty line1;
	private final StringProperty line2;
	private final StringProperty city;
	private final StringProperty state;
	private final StringProperty zip;
	
	public Address(){
		this(null,null,null,null,null);
	}
	
	/**
	 * @param line1
	 * @param line2
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Address(String line1, String line2, String city, String state, String zip) {
		this.line1 = new SimpleStringProperty(line1);
		this.line2 = new SimpleStringProperty(line2);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.zip = new SimpleStringProperty(zip);
	}

	public StringProperty line1Property() {
		return this.line1;
	}
	public String getLine1() {
		return this.line1Property().get();
	}
	public void setLine1(String line1) {
		this.line1Property().set(line1);
	}
	public StringProperty line2Property() {
		return this.line2;
	}
	public String getLine2() {
		return this.line2Property().get();
	}
	public void setLine2(String line2) {
		this.line2Property().set(line2);
	}
	public StringProperty cityProperty() {
		return this.city;
	}
	public String getCity() {
		return this.cityProperty().get();
	}
	public void setCity(String city) {
		this.cityProperty().set(city);
	}
	public StringProperty stateProperty() {
		return this.state;
	}
	public String getState() {
		return this.stateProperty().get();
	}
	public void setState(String state) {
		this.stateProperty().set(state);
	}
	public StringProperty zipProperty() {
		return this.zip;
	}
	public String getZip() {
		return this.zipProperty().get();
	}
	public void setZip(String zip) {
		this.zipProperty().set(zip);
	}

	
}
