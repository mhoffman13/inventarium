package inventarium.model;

/**
 * Product model represents a product in the
 * inventory system
 * @author Meredith Hoffman
 */

import inventarium.utils.Status;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
	private final IntegerProperty uniqueId;
	private final StringProperty name;
	private final StringProperty status;
	private final StringProperty description;
	private final StringProperty sku;
	private final StringProperty vendorName;
	private final StringProperty categoryName;
	private final IntegerProperty quantity;
	private final IntegerProperty lowQuantity;
	
	private Vendor vendor;
	private Category category;
	
	/**
	 * Default Constructor
	 */
	public Product() {
		this(null,null);
	}

	/**
	 * Constructor with name and description
	 * @param name
	 * @param description
	 */
	public Product(String name, String description) {
		this.uniqueId = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(Status.ACTIVE.name());
		this.description = new SimpleStringProperty(description);
		this.sku = new SimpleStringProperty();
		this.vendorName = new SimpleStringProperty();
		this.categoryName = new SimpleStringProperty();
		this.quantity = new SimpleIntegerProperty(0);
		this.lowQuantity = new SimpleIntegerProperty(-1);
		this.category = new Category();
		this.vendor = new Vendor();
	}

	/**
	 * @param uniqueId
	 * @param name
	 * @param status
	 * @param description
	 * @param sku
	 * @param vendorName
	 * @param categoryName
	 * @param quantity
	 * @param lowQuantity
	 */
	public Product(int uniqueId, String name, Status status, String description, String sku, int quantity, int lowQuantity, Category category, Vendor vendor, Inventory inv) {
		this.uniqueId = new SimpleIntegerProperty(uniqueId);
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(status.name());
		this.description = new SimpleStringProperty(description);
		this.sku = new SimpleStringProperty(sku);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.lowQuantity = new SimpleIntegerProperty(lowQuantity);
		this.vendorName = new SimpleStringProperty();
		this.categoryName = new SimpleStringProperty();
		this.vendor = vendor;
		this.category = category;
		
		if(vendor != null && vendor.getName() != null){
			setVendorName(vendor.getName());
		}
		if(category != null && category.getName() != null){
			setCategoryName(category.getName());
		}
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

	public StringProperty skuProperty() {
		return this.sku;
	}

	public String getSku() {
		return this.skuProperty().get();
	}

	public void setSku(String sku) {
		this.skuProperty().set(sku);
	}

	public StringProperty vendorNameProperty() {
		return this.vendorName;
	}

	public String getVendorName() {
		return this.vendorNameProperty().get();
	}

	public void setVendorName(String vendorName) {
		this.vendorNameProperty().set(vendorName);
	}

	public StringProperty categoryNameProperty() {
		return this.categoryName;
	}

	public String getCategoryName() {
		return this.categoryNameProperty().get();
	}

	public void setCategoryName(String categoryName) {
		this.categoryNameProperty().set(categoryName);
	}

	public IntegerProperty quantityProperty() {
		return this.quantity;
	}

	public int getQuantity() {
		return this.quantityProperty().get();
	}

	public void setQuantity(int quantity) {
		this.quantityProperty().set(quantity);
	}

	public IntegerProperty lowQuantityProperty() {
		return this.lowQuantity;
	}

	public int getLowQuantity() {
		return this.lowQuantityProperty().get();
	}

	public void setLowQuantity(int lowQuantity) {
		this.lowQuantityProperty().set(lowQuantity);
	}
	/**
	 * @return the vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isLow(){
		return this.quantityProperty().get() <= this.lowQuantityProperty().get(); 
	}
	
}
