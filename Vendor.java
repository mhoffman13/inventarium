
public class Vendor {
	Integer uniqueId;
	String name;
	String contactName;
	String phone;
	Address address;
	String email;
	EntityStatus status;

	public Vendor(Integer id, String name, String contactName, String phone,
			Address address, String email, EntityStatus status) {
		uniqueId = id;
		this.name = name;
		this.contactName = contactName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.status = status;
	}
	
	/**
	 * @return the uniqueId
	 */
	public Integer getUniqueId() {
		return uniqueId;
	}
	/**
	 * @param id the unqiueId to set
	 */
	public void setUniqueId(Integer id) {
		uniqueId = id;
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
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
