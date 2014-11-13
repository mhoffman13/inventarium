package inventarium.action;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import inventarium.data.DataRequest;
import inventarium.entity.Category;
import inventarium.entity.Product;
import inventarium.entity.Vendor;

/**
 * @author mhoffman
 * Implementation of the EntityAction class.
 * This class validates an entity and, if valid,
 * sends the entity to the DataRequest class
 * to add or update the record
 */

public class EntityActionImpl implements EntityAction {
	public boolean userCancel = false;
	private boolean isUpdate = false;
	
	/**
	 * Constructor
	 */
	public EntityActionImpl(boolean isUpdate){
		this.isUpdate = isUpdate;
	}
	
	/**
	 * @param product
	 * @return whether new record added
	 */
	@Override
	public boolean addEditEntity(Product product){
		String entityType = "Product";
		String entityName = product.getName();
		if(validateEntity(entityType, entityName)){
			if(this.isUpdate){
				System.out.println("Updating record for " + entityType + " " + entityName);
				return DataRequest.updateRecord(product);
			}else{
				System.out.println("Inserting record for " + entityType + " " + entityName);
				return DataRequest.insertRecord(product);
			}
		}else{
			return false;
		}
	}
	
	/**
	 * @param vendor
	 * @return whether new record added
	 */
	@Override
	public boolean addEditEntity(Vendor vendor){
		String entityType = "Vendor";
		String entityName = vendor.getName();
		if(validateEntity(entityType, entityName)){
			if(this.isUpdate){
				System.out.println("Updating record for " + entityType + " " + entityName);
				return DataRequest.updateRecord(vendor);
			}else{
				System.out.println("Inserting record for " + entityType + " " + entityName);
				return DataRequest.insertRecord(vendor);
			}
		}else{
			return false;
		}
	}
	
	/**
	 * @param category
	 * @return whether new record added
	 */
	@Override
	public boolean addEditEntity(Category category){
		String entityType = "Category";
		String entityName = category.getName();
		if(validateEntity(entityType, entityName)){
			if(this.isUpdate){
				System.out.println("Updating record for " + entityType + " " + entityName);
				return DataRequest.updateRecord(category);
			}else{
				System.out.println("Inserting record for " + entityType + " " + entityName);
				return DataRequest.insertRecord(category);
			}
		}else{
			return false;
		}
	}
	
	/**
	 * @param entityType
	 * @param entityName
	 * @return whether entity is valid
	 */
	private boolean validateEntity(String entityType, String entityName){
		int reply = JOptionPane.YES_OPTION;
		boolean isValid = false;
		// check for missing required fields
		if(entityName == null || entityName.length() == 0){
			String msg = "Name is required for " + entityType;
			showMessage('E', msg);
			return isValid;
		}
		// does an entity record with same name exist?
		// if so, confirm entity add
		if(!this.isUpdate && duplicateNames(entityName, "name", entityType)){
			String msg = "A "+ entityType +" with the name " + entityName + " already exists." +
					"Are you sure you want to add a new "+ entityType +" with the same name?";
			reply = showMessage('C', msg);
		}
		if(reply == JOptionPane.NO_OPTION){
			this.userCancel = true;
			return isValid;
		}
		isValid = true;
		return isValid;
	}
	
	/**
	 * @param searchTerm
	 * @param columnName
	 * @param tableName
	 * @return whether matching record exists
	 */
	private boolean duplicateNames(String searchTerm, String columnName, String tableName){
		try {
			return (DataRequest.search(searchTerm, columnName, tableName) == null);
		} catch (SQLException e) {
			System.out.println("Error checking for duplicates of " + columnName + "=" + searchTerm);
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @param dialogType
	 * @param message
	 * @return user response to dialog
	 */
	private int showMessage(char dialogType, String message){
		switch(dialogType){
			case 'E':
				JOptionPane.showMessageDialog(null, message, "Error!", JOptionPane.ERROR_MESSAGE);
				return JOptionPane.OK_OPTION;
			default:
				return JOptionPane.showConfirmDialog(null, message, "Confirm Action", JOptionPane.YES_NO_OPTION);
		}
	}

	/**
	 * @return the userCancel
	 */
	public boolean isUserCancel() {
		return userCancel;
	}

	/**
	 * @param userCancel the userCancel to set
	 */
	public void setUserCancel(boolean userCancel) {
		this.userCancel = userCancel;
	}

	/**
	 * @return the isUpdate
	 */
	public boolean isUpdate() {
		return isUpdate;
	}

	/**
	 * @param isUpdate the isUpdate to set
	 */
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
}
