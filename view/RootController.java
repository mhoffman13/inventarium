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
import javafx.scene.control.Menu;
import inventarium.MainApp;

public class RootController {
	@FXML
	private MainApp mainApp;
	@FXML
	private Menu selectedMenu;

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
		selectedMenu.setText("Products");
		mainApp.showProductOverview();
	}
        
	@FXML
	void showVendorOverview(ActionEvent event) {
		selectedMenu.setText("Vendors");
		mainApp.showVendorOverview();
	}

	@FXML
	void showCategoryOverview(ActionEvent event) {
		selectedMenu.setText("Categories");
		mainApp.showCategoryOverview();
	}

	@FXML
	void showInventoryOverview(ActionEvent event) {
		selectedMenu.setText("Inventory");
		mainApp.showInventoryOverview();
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
