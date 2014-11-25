package inventarium.view;

/**
 * Displays an overview of Products in the system
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
import inventarium.model.Product;

public class ProductOverviewController {
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, String> descriptionColumn;
	
	@FXML
	private Label nameLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label uniqueIdLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private Label skuLabel;
	@FXML
	private Label vendorNameLabel;
	@FXML
	private Label categoryNameLabel;
	@FXML
	private Label quantityLabel;
	@FXML
	private Label lowQuantityLabel;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	/**
	 * Default Constructor The constructor is called before the initialize()
	 * method.
	 */
	public ProductOverviewController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the product table with the two columns.
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		
		// Clear product details.
		showProductDetails(null);

	    // Listen for selection changes and show the product details when changed.
		productTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showProductDetails(newValue));
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Add observable list data to the table
		productTable.setItems(mainApp.getProductData());
	}
	
	/**
	 * Fills all text fields to show details about the product. If the specified
	 * product is null, all text fields are cleared.
	 * 
	 * @param product the product or null
	 */
	private void showProductDetails(Product product) {
		if (product != null) {
			// Fill the labels with info from the product object.
			nameLabel.setText(product.getName());
			descriptionLabel.setText(product.getDescription());
			uniqueIdLabel.setText(Integer.toString(product.getUniqueId()));
			statusLabel.setText(product.getStatus().name());
			skuLabel.setText(product.getSku());
			vendorNameLabel.setText(product.getVendorName());
			categoryNameLabel.setText(product.getCategoryName());
			quantityLabel.setText(Integer.toString(product.getQuantity()));
			lowQuantityLabel.setText(Integer.toString(product.getLowQuantity()));
			
			// TODO: We need a way to convert the birthday into a String!
			// birthdayLabel.setText(...);
		} else {
			// Product is null, remove all the text.
			nameLabel.setText("");
			descriptionLabel.setText("");
			uniqueIdLabel.setText("");
			statusLabel.setText("");
			skuLabel.setText("");
			vendorNameLabel.setText("");
			categoryNameLabel.setText("");
			quantityLabel.setText("");
			lowQuantityLabel.setText("");
		}
	}
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Action response = Dialogs.create()
				.owner(mainApp.getPrimaryStage())
				.title("Confirm Delete")
				.message("Confirm deletion of " + nameLabel.getText())
				.showConfirm();
				
			if (response == Dialog.Actions.YES) {
				productTable.getItems().remove(selectedIndex);
			}
			// else, do nothing
	        
	    } else {
	        // Nothing selected.
	        Dialogs.create()
	            .title("No Selection")
	            .message("Please select a product in the table.")
	            .showWarning();
	    }
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new product.
	 */
	@FXML
	private void handleNewProduct() {
	    Product tempProduct = new Product();
	    boolean okClicked = mainApp.showProductEditDialog(tempProduct, true);
	    if (okClicked) {
	        mainApp.getProductData().add(tempProduct);
	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected product.
	 */
	@FXML
	private void handleEditProduct() {
	    Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
	    if (selectedProduct != null) {
	        boolean okClicked = mainApp.showProductEditDialog(selectedProduct, false);
	        if (okClicked) {
	            showProductDetails(selectedProduct);
	        }

	    } else {
	        // Nothing selected.
	        Dialogs.create()
	            .title("No Selection")
	            .message("Please select a product in the table.")
	            .showWarning();
	    }
	}
}