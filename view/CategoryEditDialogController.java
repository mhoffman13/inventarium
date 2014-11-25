package inventarium.view;

/**
 * Dialog to edit details of a category.
 * @author Meredith Hoffman
 */

import inventarium.model.Category;
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

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class CategoryEditDialogController {
	
	@FXML
	private TextField nameText;
	@FXML
	private TextArea descriptionText;
	@FXML
	private TextField uniqueIdText;
	@FXML
	private TextField statusText;
	@FXML
	private ComboBox<Status> statusInput;
	@FXML
	private ObservableList<Status> statusInputData = FXCollections.observableArrayList();
	
	
	private Stage dialogStage;
	private Category category;
	private boolean okClicked = false;
	
	public CategoryEditDialogController() {
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
	 * Sets the category to be edited in the dialog.
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
		nameText.setText(category.getName());
		descriptionText.setText(category.getDescription());
		uniqueIdText.setText(Integer.toString(category.getUniqueId()));
		statusInput.setValue(category.getStatus());
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
			category.setName(nameText.getText());
			category.setDescription(descriptionText.getText());
			category.setUniqueId(Integer.parseInt(uniqueIdText.getText()));
			category.setStatus(statusInput.getValue());
			
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
