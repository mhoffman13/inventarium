//Page navigator will be used to select the proper FXML document
package inventarium.ui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;


public class PageNavigator {
    public static final String MAIN  = "MainMenu.fxml";//used for the menu
    public static final String SystemContainer  = "SystemContainer.fxml";//used for the main page
    public static final String Inventory = "InventoryView.fxml";
    public static final String AddProduct = "AddProductView.fxml";
    public static final String AddVendor = "AddVendorView.fxml";
    private static UIListener mainDocumentController;

    
    
    public static void setMainDocumentController(UIListener controller) {
        PageNavigator.mainDocumentController =controller;
        
    }
       public static void loadPages(String fxml) {
        try {
            mainDocumentController.setPages(
                FXMLLoader.load(
                    PageNavigator.class.getResource(
                        fxml
                    )
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
