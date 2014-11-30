package inventarium.model;

/**
 * Inventory model represents an inventory
 * transaction in the inventory system
 * @author Meredith Hoffman
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventory {
	private final IntegerProperty uniqueId;
	private final IntegerProperty productId;
	private final StringProperty adjustment;
	private final StringProperty productName;
	private final StringProperty date;
	
	/** 
	 * Default Constructor
	 */
	public Inventory(){
		this(null,null,null);
	}
	
	/**
	 * Constructor with adjustment and product id
	 * @param adjustment
	 * @param productId
	 */
	public Inventory(Integer adjustment, Integer productId, String productName) {
		this.uniqueId = new SimpleIntegerProperty();
		this.adjustment = new SimpleStringProperty(adjustment == null ? null : adjustment.toString());
		this.productId = new SimpleIntegerProperty(productId == null ? 0 : productId);
		Calendar today = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
		this.date = new SimpleStringProperty(dateFormat.format(today.getTime()));
		this.productName = new SimpleStringProperty(productName);
	}

	/**
	 * Constructor with adjustment, product id, unique id, and date
	 * @param uniqueId
	 * @param adjustment
	 * @param productId
	 * @param date
	 */
	public Inventory(Integer uniqueId, Integer productId, Date date, Integer adjustment) {
		this.uniqueId = new SimpleIntegerProperty(uniqueId);
		this.adjustment = new SimpleStringProperty(adjustment == null ? null : adjustment.toString());
		this.productId = new SimpleIntegerProperty(productId == null ? 0 : productId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
		this.date = new SimpleStringProperty(dateFormat.format(date));
		this.productName = new SimpleStringProperty();
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

	public StringProperty adjustmentProperty() {
		return this.adjustment;
	}

	public String getAdjustment() {
		return this.adjustmentProperty().get();
	}

	public void setAdjustment(int adjustment) {
		this.adjustmentProperty().set(Integer.toString(adjustment));
	}

	public IntegerProperty productIdProperty() {
		return this.productId;
	}

	public int getProductId() {
		return this.productIdProperty().get();
	}

	public void setProductId(final int productId) {
		this.productIdProperty().set(productId);
	}
	
	public StringProperty productNameProperty() {
		return this.productName;
	}

	public String getProductName() {
		return this.productNameProperty().get();
	}

	public void setProductName(String name) {
		this.productNameProperty().set(name);
	}
	
	public StringProperty dateProperty() {
		return this.date;
	}

	public String getDate() {
		return this.dateProperty().get();
	}

	public void setDate(String date) {
		this.dateProperty().set(date);
	}
}
