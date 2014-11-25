//Page navigator will be used to select the proper FXML document
package inventarium.ui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class PageNavigator {
	public static final String MAIN_MENU = "/inventarium/ui/MainMenu.fxml";// used for the menu
	public static final String MAIN_VIEW = "/inventarium/ui/SystemContainer.fxml";// used for the main page
	public static final String INVENTORY_VIEW = "/inventarium/ui/InventoryView.fxml";
	public static final String ADD_PRODUCT_VIEW = "/inventarium/ui/AddProductView.fxml";
	public static final String ADD_VENDOR_VIEW = "/inventarium/ui/AddVendorView.fxml";
	public static final String EDIT_PRODUCT_VIEW = "/inventarium/ui/EditProductView.fxml";
	public static final String SAVED_PRODUCT_VIEW = "/inventarium/ui/SavedProductView.fxml";
        
	private static UIListener mainDocumentController;

	public static void setMainDocumentController(UIListener controller) {
		PageNavigator.mainDocumentController = controller;

	}

	public static void loadPages(String fxml) {
		try {
			mainDocumentController.setPages(FXMLLoader.load(PageNavigator.class.getResource(fxml)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
