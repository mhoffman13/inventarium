package inventarium.view;

/**
 * Displays an overview of Inventory transactions
 * in the system and their details.
 * @author Meredith Hoffman
 */

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import inventarium.MainApp;
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
	
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
    
	private ObservableList<String> monthNames = FXCollections.observableArrayList();
	
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
		
		/** 
		 * Setup bar chart 
		 * */
		// Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
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
	
	// get list of products here too, to cycle through product list
	// and sum inventory
	public void setInventoryData(List<Inventory> inventories, List<Product> products) {
		mainApp.updateInventoryData();
		// Sum outbound inventory by month for each product
		int[] monthCounter = new int[12];
		Calendar cal = Calendar.getInstance();
		for(Product p : products){
			for(Inventory i : inventories){
				if(i.getProductId() == p.getUniqueId() && i.getAdj() < 0){
					cal.setTime(i.getDateDate());
		            monthCounter[cal.get(Calendar.MONTH)] += -1*i.getAdj();
				}
			}
		}

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }
}