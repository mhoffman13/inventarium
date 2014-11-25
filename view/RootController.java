package inventarium.view;

/**
 * Controls the switching from overview to overview.
 * @author Meredith Hoffman
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import inventarium.MainApp;

public class RootController {
	@FXML
	private MainApp mainApp;

	/**
	 * Default Constructor
	 */
	public RootController() {
	}
	
	/**
	 * Called by mainApp to give a reference back to
	 * itself
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	void showProductOverview(ActionEvent event) {
		mainApp.showProductOverview();
	}
        
	@FXML
	void showVendorOverview(ActionEvent event) {
		mainApp.showVendorOverview();
	}

	@FXML
	void showCategoryOverview(ActionEvent event) {
		mainApp.showCategoryOverview();
	}

	@FXML
	void showInventoryOverview(ActionEvent event) {
		
	}
	
	public void initialize(){
		
	}
}
