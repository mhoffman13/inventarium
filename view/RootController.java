package inventarium.view;

/**
 * Controls the switching from overview to overview.
 * @author Meredith Hoffman
 */

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import inventarium.MainApp;
import inventarium.data.DataRequest;

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
	
	public boolean shutdown() {
		Action response = Dialogs.create()
				.owner(mainApp.getPrimaryStage())
				.title("Confirm Exit")
				.message("Are you sure you want to exit?")
				.showConfirm();
				
		return response == Dialog.Actions.YES;
	}
	
	public void initialize(){
		
	}
}
