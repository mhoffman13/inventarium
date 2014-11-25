package inventarium.model;

/**
 * Category model represents user-defined similarities
 * between products in the inventory system
 * @author Meredith Hoffman
 */

import inventarium.utils.Status;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
	private final IntegerProperty uniqueId;
	private final StringProperty name;
	private final StringProperty status;
	private final StringProperty description;
	
	AtomicInteger seq = new AtomicInteger();
	
	/**
	 * Default Constructor
	 */
	public Category() {
		this(null,null);
	}

	/**
	 * Constructor with name and description
	 * @param name
	 * @param description
	 */
	public Category(String name, String description) {
		this.uniqueId = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(Status.ACTIVE.name());
		this.description = new SimpleStringProperty(description);
	}

	/**
	 * Constructor with all id, name, description, status
	 * @param uniqueId
	 * @param name
	 * @param description
	 * @param status
	 */
	public Category(int uniqueId, String name, String description, Status status) {
		this.uniqueId = new SimpleIntegerProperty(uniqueId);
		this.name = new SimpleStringProperty(name);;
		this.status = new SimpleStringProperty(status.name());
		this.description = new SimpleStringProperty(description);
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
}
