package inventarium.view;

/**
 * Dialog to edit details of a product.
 * @author Meredith Hoffman
 */

import inventarium.model.Product;
import inventarium.utils.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

public class ProductEditDialogController {
	
	@FXML
	private TextField nameText;
	@FXML
	private TextArea descriptionText;
	@FXML
	private TextField uniqueIdText;
	@FXML
	private TextField statusText;
	@FXML
	private TextField skuText;
	@FXML
	private TextField vendorNameText;
	@FXML
	private TextField categoryNameText;
	@FXML
	private TextField quantityText;
	@FXML
	private TextField lowQuantityText;
	@FXML
	private ComboBox<Status> statusInput;
	@FXML
	private ObservableList<Status> statusInputData = FXCollections.observableArrayList();
	
	
	private Stage dialogStage;
	private Product product;
	private boolean okClicked = false;
	
	public ProductEditDialogController() {
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
	 * Sets the product to be edited in the dialog.
	 * 
	 * @param product
	 */
	public void setProduct(Product product) {
		this.product = product;
		nameText.setText(product.getName());
		descriptionText.setText(product.getDescription());
		uniqueIdText.setText(Integer.toString(product.getUniqueId()));
		statusInput.setValue(product.getStatus());
		skuText.setText(product.getSku());
		vendorNameText.setText(product.getVendorName());
		categoryNameText.setText(product.getCategoryName());
		quantityText.setText(Integer.toString(product.getQuantity()));
		lowQuantityText.setText(Integer.toString(product.getLowQuantity()));
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
			product.setName(nameText.getText());
			product.setDescription(descriptionText.getText());
			product.setUniqueId(Integer.parseInt(uniqueIdText.getText()));
			product.setStatus(statusInput.getValue());
			product.setSku(skuText.getText());
			product.setVendorName(vendorNameText.getText());
			product.setCategoryName(categoryNameText.getText());
			product.setQuantity(Integer.parseInt(quantityText.getText()));
			product.setLowQuantity(Integer.parseInt(lowQuantityText.getText()));
			
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
//		if(statusText.getText() == null || statusText.getText().length() == 0) {
//			errorMessage += "Status is required\n"; 
//		}
		if(quantityText.getText() == null || quantityText.getText().length() == 0) {
			errorMessage += "Quantity is required\n"; 
		} else {
			// try to parse the quantity
			try {
				Integer.parseInt(quantityText.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Invalid quantity (must be an integer)\n"; 
			}
		}
		if(lowQuantityText.getText() == null || lowQuantityText.getText().length() == 0) {
			errorMessage += "Low quantity is required\n"; 
		} else {
			// try to parse the quantity
			try {
				Integer.parseInt(lowQuantityText.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Invalid low quantity (must be an integer)\n"; 
			}
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
