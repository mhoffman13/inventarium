package inventarium.view;

/**
 * Displays an overview of Inventory transactions
 * in the system and their details.
 * @author Meredith Hoffman
 */

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import inventarium.MainApp;
import inventarium.data.DataRequest;
import inventarium.model.Inventory;
import inventarium.model.Product;

public class InventoryOverviewController {
	@FXML
	private TableView<Inventory> inventoryTable;
	@FXML
	private TableColumn<Inventory, String> dateColumn;
	@FXML
	private TableColumn<Inventory, String> productColumn;
	@FXML
	private TableColumn<Inventory, String> adjColumn;
	
	@FXML
	private Label productLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label uniqueIdLabel;
	@FXML
	private Label adjLabel;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	/**
	 * Default Constructor The constructor is called before the initialize()
	 * method.
	 */
	public InventoryOverviewController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the inventory table with the three columns.
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		productColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
		adjColumn.setCellValueFactory(cellData -> cellData.getValue().adjustmentProperty());
		
		// Clear inventory details.
		showInventoryDetails(null);

	    // Listen for selection changes and show the product details when changed.
		inventoryTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showInventoryDetails(newValue));
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Add observable list data to the table
		inventoryTable.setItems(mainApp.getInventoryData());
	}
	
	/**
	 * Fills all text fields to show details about the product. If the specified
	 * product is null, all text fields are cleared.
	 * 
	 * @param product the product or null
	 */
	private void showInventoryDetails(Inventory inventory) {
		if (inventory != null) {
			// Fill the labels with info from the inventory object.
			uniqueIdLabel.setText(Integer.toString(inventory.getUniqueId()));
			productLabel.setText(inventory.getProductName());
			dateLabel.setText(inventory.getDate());
			adjLabel.setText(inventory.getAdjustment());
		} else {
			// Inventory is null, remove all the text.
			uniqueIdLabel.setText("");
			productLabel.setText("");
			dateLabel.setText("");
			adjLabel.setText("");
		}
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteProduct() {

	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new product.
	 */
	@FXML
	private void handleNewProduct() {

	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected product.
	 */
	@FXML
	private void handleEditProduct() {

	}
}