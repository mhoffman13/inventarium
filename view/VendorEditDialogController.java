package inventarium.view;

/**
 * Dialog to edit details of a vendor.
 * @author Meredith Hoffman
 */

import inventarium.model.Vendor;
import inventarium.utils.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

public class VendorEditDialogController {
	
	@FXML
	private TextField nameText;
	@FXML
	private TextField descriptionText;
	@FXML
	private TextField uniqueIdText;
	@FXML
	private TextField statusText;
	@FXML
	private TextField contactNameText;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField emailText;
	@FXML
	private TextField line1Text;
	@FXML
	private TextField line2Text;
	@FXML
	private TextField cityText;
	@FXML
	private TextField stateText;
	@FXML
	private TextField zipText;
	@FXML
	private ComboBox<Status> statusInput;
	@FXML
	private ObservableList<Status> statusInputData = FXCollections.observableArrayList();
	
	
	private Stage dialogStage;
	private Vendor vendor;
	private boolean okClicked = false;
	
	public VendorEditDialogController() {
		statusInputData.add(Status.ACTIVE);
		statusInputData.add(Status.ARCHIVED);
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Init ComboBox items.
		statusInput.setItems(statusInputData);
		
		// Define rendering of the list of values in ComboBox drop down. 
		statusInput.setCellFactory((comboBox) -> {
			return new ListCell<Status>() {
				@Override
				protected void updateItem(Status status, boolean empty) {
					super.updateItem(status, empty);
					
					if (status == null || empty) {
						setText(null);
					} else {
						setText(status.name());
					}
				}
			};
		});
		
		// Handle ComboBox event.
		statusInput.setOnAction((event) -> {
			statusInput.getSelectionModel().getSelectedItem();
		});
	}
	
	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**
	 * Sets the vendor to be edited in the dialog.
	 * 
	 * @param vendor
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
		nameText.setText(vendor.getName());
		descriptionText.setText(vendor.getDescription());
		uniqueIdText.setText(Integer.toString(vendor.getUniqueId()));
		statusInput.setValue(vendor.getStatus());
		contactNameText.setText(vendor.getContactName());
		phoneText.setText(vendor.getPhone());
		emailText.setText(vendor.getEmail());
		line1Text.setText(vendor.getAddress().getLine1());
		line2Text.setText(vendor.getAddress().getLine2());
		cityText.setText(vendor.getAddress().getCity());
		stateText.setText(vendor.getAddress().getState());
		zipText.setText(vendor.getAddress().getZip());
	}
	
	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			vendor.setName(nameText.getText());
			vendor.setDescription(descriptionText.getText());
			vendor.setUniqueId(Integer.parseInt(uniqueIdText.getText()));
			vendor.setStatus(statusInput.getValue());
			vendor.setContactName(contactNameText.getText());
			vendor.setPhone(phoneText.getText());
			vendor.setEmail(emailText.getText());
			vendor.getAddress().setLine1(line1Text.getText());
			vendor.getAddress().setLine2(line2Text.getText());
			vendor.getAddress().setCity(cityText.getText());
			vendor.getAddress().setState(stateText.getText());
			vendor.getAddress().setZip(zipText.getText());
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		if(nameText.getText() == null || nameText.getText().length() == 0) {
			errorMessage += "Name is required\n"; 
		}
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Dialogs.create()
			.title("Invalid Fields")
			.message(errorMessage)
			.showError();
			return false;
		}
	}
}
