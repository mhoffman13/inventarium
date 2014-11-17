package inventarium.entity;

/**
 * @author mhoffman
 * Category class represents a category
 * by which products in the system are
 * organized
 */

import inventarium.helper.EntityStatus;

public class Category {
	Integer uniqueId;
	String name;
	String description;
	EntityStatus status;
	
	/**
	 * Constructor
	 */
	public Category() {}
	/**
	 * Constructor
	 * @param uniqueId
	 * @param name
	 * @param description
	 * @param status
	 */
	public Category(Integer uniqueId, String name, String description,
			EntityStatus status) {
		this.uniqueId = uniqueId;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	/**
	 * @return the uniqueId
	 */
	public Integer getUniqueId() {
		return uniqueId;
	}
	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
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
}
