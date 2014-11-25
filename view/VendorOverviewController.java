package inventarium.view;

/**
 * Displays an overview of Vendors in the system
 * and their details.
 * @author Meredith Hoffman
 */

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import inventarium.MainApp;
import inventarium.model.Vendor;

public class VendorOverviewController {
	@FXML
	private TableView<Vendor> vendorTable;
	@FXML
	private TableColumn<Vendor, String> nameColumn;
	@FXML
	private TableColumn<Vendor, String> descriptionColumn;
	
	@FXML
	private Label nameLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label uniqueIdLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private Label contactNameLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label line1Label;
	@FXML
	private Label line2Label;
	@FXML
	private Label cityLabel;
	@FXML
	private Label stateLabel;
	@FXML
	private Label zipLabel;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	/**
	 * Default Constructor The constructor is called before the initialize()
	 * method.
	 */
	public VendorOverviewController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		// Clear person details.
		showVendorDetails(null);

	    // Listen for selection changes and show the person details when changed.
		vendorTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showVendorDetails(newValue));
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Add observable list data to the table
		vendorTable.setItems(mainApp.getVendorData());
	}
	
	/**
	 * Fills all text fields to show details about the vendor. If the specified
	 * vendor is null, all text fields are cleared.
	 * 
	 * @param vendor the vendor or null
	 */
	private void showVendorDetails(Vendor vendor) {
		if (vendor != null) {
			// Fill the labels with info from the vendor object.
			nameLabel.setText(vendor.getName());
			//descriptionLabel.setText(vendor.getDescription());
			uniqueIdLabel.setText(Integer.toString(vendor.getUniqueId()));
			statusLabel.setText(vendor.getStatus().name());
			contactNameLabel.setText(vendor.getContactName());
			phoneLabel.setText(vendor.getPhone());
			emailLabel.setText(vendor.getEmail());
			line1Label.setText(vendor.getAddress().getLine1());
			line2Label.setText(vendor.getAddress().getLine2());
			cityLabel.setText(vendor.getAddress().getCity());
			stateLabel.setText(vendor.getAddress().getState());
			zipLabel.setText(vendor.getAddress().getZip());
		} else {
			// Vendor is null, remove all the text.
			nameLabel.setText("");
			//descriptionLabel.setText("");
			uniqueIdLabel.setText("");
			statusLabel.setText("");
			contactNameLabel.setText("");
			phoneLabel.setText("");
			emailLabel.setText("");
			line1Label.setText("");
			line2Label.setText("");
			cityLabel.setText("");
			stateLabel.setText("");
			zipLabel.setText("");
		}
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteVendor() {
		int selectedIndex = vendorTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Action response = Dialogs.create()
				.owner(mainApp.getPrimaryStage())
				.title("Confirm Delete")
				.message("Confirm deletion of " + nameLabel.getText())
				.showConfirm();
				
			if (response == Dialog.Actions.YES) {
				vendorTable.getItems().remove(selectedIndex);
			}
			// else, do nothing
	        
	    } else {
	        // Nothing selected.
	        Dialogs.create()
	            .title("No Selection")
	            .message("Please select a vendor in the table.")
	            .showWarning();
	    }
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new vendor.
	 */
	@FXML
	private void handleNewVendor() {
	    Vendor tempVendor = new Vendor();
	    boolean okClicked = mainApp.showVendorEditDialog(tempVendor, true);
	    if (okClicked) {
	        mainApp.getVendorData().add(tempVendor);
	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected vendor.
	 */
	@FXML
	private void handleEditVendor() {
	    Vendor selectedVendor = vendorTable.getSelectionModel().getSelectedItem();
	    if (selectedVendor != null) {
	        boolean okClicked = mainApp.showVendorEditDialog(selectedVendor, false);
	        if (okClicked) {
	            showVendorDetails(selectedVendor);
	        }

	    } else {
	        // Nothing selected.
	        Dialogs.create()
	            .title("No Selection")
	            .message("Please select a vendor in the table.")
	            .showWarning();
	    }
	}
}