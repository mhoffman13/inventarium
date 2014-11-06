package inventarium.entity;

import inventarium.helper.EntityStatus;
import java.util.List;

public class Product {
	Integer uniqueId;
	String name;
	EntityStatus status;
	String description;
	String sku;
	int quantity;
	int lowQuantity;
	Category category;
	Vendor vendor;
	List <Inventory> inventories; // do we want this on the product object?
	boolean isLow = false;
	
	/**
	 * Constructor
	 * @param uniqueId
	 * @param name
	 * @param status
	 * @param quantity
	 */
	public Product(Integer uniqueId, String name, EntityStatus status,
			int quantity) {
		this.uniqueId = uniqueId;
		this.name = name;
		this.status = status;
		this.quantity = quantity;
	}
	/**
	 * Constructor
	 * @param uniqueId
	 * @param name
	 * @param status
	 * @param description
	 * @param sku
	 * @param quantity
	 * @param lowQuantity
	 * @param categories
	 * @param vendors
	 * @param inventories
	 */
	public Product(Integer uniqueId, String name, EntityStatus status,
			String description, String sku, int quantity, int lowQuantity,
			Category category, Vendor vendor, List<Inventory> inventories) {
		this.uniqueId = uniqueId;
		this.name = name;
		this.status = status;
		this.description = description;
		this.sku = sku;
		this.quantity = quantity;
		this.lowQuantity = lowQuantity;
		this.category = category;
		this.vendor = vendor;
		this.inventories = inventories;
		this.isLow = this.isLow();
	}

	/**
	 * @return the iD
	 */
	public Integer getUniqueId() {
		return uniqueId;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setUniqueId(Integer id) {
		this.uniqueId = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the lowQuantity
	 */
	public int getlowQuantity() {
		return lowQuantity;
	}
	/**
	 * @param lowQuantity the lowQuantity to set
	 */
	public void setlowQuantity(int lowQuantity) {
		this.lowQuantity = lowQuantity;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to add
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}
	/**
	 * @param vendor the vendor to add
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	/**
	 * @return the status
	 */
	public EntityStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(EntityStatus status) {
		this.status = status;
	}
	/**
	 * @return and set the isLow
	 */
	public boolean isLow() {
		return isLow = quantity <= lowQuantity;
	}
}
