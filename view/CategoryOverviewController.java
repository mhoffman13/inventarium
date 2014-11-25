package inventarium.view;

/**
 * Displays an overview of Categories in the system
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
import inventarium.model.Category;

public class CategoryOverviewController {
	@FXML
	private TableView<Category> categoryTable;
	@FXML
	private TableColumn<Category, String> nameColumn;
	@FXML
	private TableColumn<Category, String> descriptionColumn;
	
	@FXML
	private Label nameLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label uniqueIdLabel;
	@FXML
	private Label statusLabel;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	/**
	 * Default Constructor The constructor is called before the initialize()
	 * method.
	 */
	public CategoryOverviewController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the category table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		// Clear category details.
		showCategoryDetails(null);

	    // Listen for selection changes and show the category details when changed.
		categoryTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showCategoryDetails(newValue));
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Add observable list data to the table
		categoryTable.setItems(mainApp.getCategoryData());
	}
	
	/**
	 * Fills all text fields to show details about the category. If the specified
	 * category is null, all text fields are cleared.
	 * 
	 * @param category the category or null
	 */
	private void showCategoryDetails(Category category) {
		if (category != null) {
			// Fill the labels with info from the category object.
			nameLabel.setText(category.getName());
			descriptionLabel.setText(category.getDescription());
			uniqueIdLabel.setText(Integer.toString(category.getUniqueId()));
			statusLabel.setText(category.getStatus().name());
		} else {
			// Category is null, remove all the text.
			nameLabel.setText("");
			descriptionLabel.setText("");
			uniqueIdLabel.setText("");
			statusLabel.setText("");
		}
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteCategory() {
		int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Action response = Dialogs.create()
				.owner(mainApp.getPrimaryStage())
				.title("Confirm Delete")
				.message("Confirm deletion of " + nameLabel.getText())
				.showConfirm();
				
			if (response == Dialog.Actions.YES) {
				categoryTable.getItems().remove(selectedIndex);
			}
			// else, do nothing
	        
	    } else {
	        // Nothing selected.
	        Dialogs.create()
	            .title("No Selection")
	            .message("Please select a category in the table.")
	            .showWarning();
	    }
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new category.
	 */
	@FXML
	private void handleNewCategory() {
	    Category tempCategory = new Category();
	    boolean okClicked = mainApp.showCategoryEditDialog(tempCategory, true);
	    if (okClicked) {
	        mainApp.getCategoryData().add(tempCategory);
	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected category.
	 */
	@FXML
	private void handleEditCategory() {
	    Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
	    if (selectedCategory != null) {
	        boolean okClicked = mainApp.showCategoryEditDialog(selectedCategory, false);
	        if (okClicked) {
	            showCategoryDetails(selectedCategory);
	        }

	    } else {
	        // Nothing selected.
	        Dialogs.create()
	            .title("No Selection")
	            .masthead("No Category Selected")
	            .message("Please select a category in the table.")
	            .showWarning();
	    }
	}
}