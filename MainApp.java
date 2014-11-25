package inventarium;

/**
 * Handles the main system for the application
 * @author Meredith Hoffman
 * This project is based on code from the Java8 FX 
 * tutorial at http://code.makery.ch/
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import inventarium.model.*;
import inventarium.view.CategoryEditDialogController;
import inventarium.view.CategoryOverviewController;
import inventarium.view.ProductEditDialogController;
import inventarium.view.ProductOverviewController;
import inventarium.view.RootController;
import inventarium.view.VendorEditDialogController;
import inventarium.view.VendorOverviewController;
import inventarium.data.DataRequest;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<Category> categoryData = FXCollections.observableArrayList();
	private ObservableList<Product> productData = FXCollections.observableArrayList();
	private ObservableList<Vendor> vendorData = FXCollections.observableArrayList();
	
	/**
	 * Default Constructor
	 */
	public MainApp() {
		// TODO: Use DataRequest to build product, vendor, ...
		DataRequest.initialize();
		Set<Product> productList = new HashSet<>();
		Set<Vendor> vendorList = new HashSet<>();
		Set<Category> categoryList = new HashSet<>();
		try {
			productList = DataRequest.getAll(new Product());
			vendorList = DataRequest.getAll(new Vendor());
			categoryList = DataRequest.getAll(new Category());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Product p : productList){
			productData.add(p);
		}
		for(Vendor v : vendorList){
			vendorData.add(v);
		}
		for(Category c : categoryList){
			categoryData.add(c);
		}
	}
	
	/**
	 * Returns the data as an observable list of Categories 
	 * @return
	 */
	public ObservableList<Category> getCategoryData() {
		return categoryData;
	}
	
	/**
	 * Returns the data as an observable list of Products 
	 * @return
	 */
	public ObservableList<Product> getProductData() {
		return productData;
	}
	
	/**
	 * Returns the data as an observable list of Vendors 
	 * @return
	 */
	public ObservableList<Vendor> getVendorData() {
		return vendorData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Inventarium");
		
		initRootLayout();
		
		showProductOverview();
	}
	
	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class
	                .getResource("view/RootLayout.fxml"));
	        rootLayout = (BorderPane) loader.load();

	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootLayout);
	        primaryStage.setScene(scene);

	        // Give the controller access to the main app.
	        RootController controller = loader.getController();
	        controller.setMainApp(this);
	        
	        primaryStage.show();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shows the person overview
	 */
	public void showCategoryOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CategoryOverview.fxml"));
			AnchorPane categoryOverview = (AnchorPane) loader.load();
			
			// Set person overview into the center of root layout.
			rootLayout.setCenter(categoryOverview);
			
			// Give the controller access to the main app.
			CategoryOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified category. If the user
	 * clicks OK, the changes are saved into the provided category object and true
	 * is returned.
	 * 
	 * @param category the category object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showCategoryEditDialog(Category category, boolean isNew) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CategoryEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Category");
			if(isNew){
				dialogStage.setTitle("New Category");
			}
			
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the category into the controller.
			CategoryEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCategory(category);
			
			// Whether data successfully updates
			boolean dataUpdated = false;
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			if(controller.isOkClicked()){
				if(isNew){
					dataUpdated = DataRequest.insertRecord(category);
				}else{
					dataUpdated = DataRequest.updateRecord(category);
				}
			}
			
			return dataUpdated;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Shows the person overview
	 */
	public void showProductOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
			AnchorPane productOverview = (AnchorPane) loader.load();
			
			// Set person overview into the center of root layout.
			rootLayout.setCenter(productOverview);
			
			// Give the controller access to the main app.
			ProductOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified product. If the user
	 * clicks OK, the changes are saved into the provided product object and true
	 * is returned.
	 * 
	 * @param product the product object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showProductEditDialog(Product product, boolean isNew) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Product");
			if(isNew){
				dialogStage.setTitle("New Product");
			}
			
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the product into the controller.
			ProductEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProduct(product);
			
			// Whether data successfully updates
			boolean dataUpdated = false;
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			if(controller.isOkClicked()){
				if(isNew){
					dataUpdated = DataRequest.insertRecord(product);
				}else{
					dataUpdated = DataRequest.updateRecord(product);
				}
			}
			
			return dataUpdated;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Shows the vendor overview
	 */
	public void showVendorOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VendorOverview.fxml"));
			AnchorPane vendorOverview = (AnchorPane) loader.load();
			
			// Set person overview into the center of root layout.
			rootLayout.setCenter(vendorOverview);
			
			// Give the controller access to the main app.
			VendorOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified vendor. If the user
	 * clicks OK, the changes are saved into the provided vendor object and true
	 * is returned.
	 * 
	 * @param vendor the vendor object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showVendorEditDialog(Vendor vendor, boolean isNew) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/VendorEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Vendor");
			if(isNew){
				dialogStage.setTitle("New Vendor");
			}
			
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			// Set the vendor into the controller.
			VendorEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setVendor(vendor);
			
			// Whether data successfully updates
			boolean dataUpdated = false;
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			if(controller.isOkClicked()){
				if(isNew){
					dataUpdated = DataRequest.insertRecord(vendor);
				}else{
					dataUpdated = DataRequest.updateRecord(vendor);
				}
			}
			
			return dataUpdated;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
