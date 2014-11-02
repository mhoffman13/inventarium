import java.util.Date;

public class Inventory {
	Integer uniqueId;
	Integer productId;
	Integer vendorId;
	Date date;
	int adjustment;
	
	/**
	 * @param uniqueId 
	 * @param productId
	 * @param vendorId
	 * @param date
	 * @param adjustment
	 */
	public Inventory(Integer uniqueId, Integer productId, Integer vendorId, 
			Date date, int adjustment) {
		this.uniqueId = uniqueId;
		this.productId = productId;
		this.vendorId = vendorId;
		this.date = date;
		this.adjustment = adjustment;
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
	 * @return the product
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param product the product to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * @return the vendorId
	 */
	public Integer getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the adjustment
	 */
	public int getAdjustment() {
		return adjustment;
	}
	/**
	 * @param adjustment the adjustment to set
	 */
	public void setAdjustment(int adjustment) {
		this.adjustment = adjustment;
	}
}
