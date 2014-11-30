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
	private final IntegerProperty adjustment;
	private final IntegerProperty productId;
	private final StringProperty date;
	
	/**
	 * Constructor with adjustment and product id
	 * @param adjustment
	 * @param productId
	 */
	public Inventory(int adjustment, int productId) {
		this.uniqueId = new SimpleIntegerProperty();
		this.adjustment = new SimpleIntegerProperty(adjustment);
		this.productId = new SimpleIntegerProperty(productId);
		Calendar today = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
		this.date = new SimpleStringProperty(dateFormat.format(today.getTime()));
	}

	/**
	 * Constructor with adjustment, product id, unique id, and date
	 * @param uniqueId
	 * @param adjustment
	 * @param productId
	 * @param date
	 */
	public Inventory(int uniqueId, int productId, Date date, int adjustment) {
		this.uniqueId = new SimpleIntegerProperty(uniqueId);
		this.adjustment = new SimpleIntegerProperty(adjustment);
		this.productId = new SimpleIntegerProperty(productId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
		this.date = new SimpleStringProperty(dateFormat.format(date));
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

	public IntegerProperty adjustmentProperty() {
		return this.adjustment;
	}

	public int getAdjustment() {
		return this.adjustmentProperty().get();
	}

	public void setAdjustment(int adjustment) {
		this.adjustmentProperty().set(adjustment);
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
