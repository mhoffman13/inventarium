
package inventarium.ui;

import inventarium.action.EntityActionImpl;
import inventarium.entity.Vendor;
import inventarium.helper.EntityStatus;
import inventarium.action.EntityActionImpl;
import inventarium.entity.Vendor;
import inventarium.helper.Address;
import inventarium.helper.EntityStatus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;




public class UIListener implements Initializable {
    
    @FXML
    private Pane pagePane;
    private Label vendorLabel;
    private Tab openProducts;
    private Parent view;//variable stores different views of application ex. view all
    private BorderPane contentSpace;
    private InventoryViewController InventoryViewController;
    
    @FXML private TextField vendorName;
    @FXML private TextField vendorNumber;
    @FXML private TextField vendorDescription;
    @FXML private TextField vendorContact;
    @FXML private TextField vendorPhone;
    
    public Parent getView(){
    
    return view;}
    
  
    @FXML
   private void handleVendorButton(ActionEvent event) {
    	EntityActionImpl addVendor = new EntityActionImpl(false);
    	Vendor vendor = null;
    	try{
		vendor = new Vendor(123, vendorName.getText(), vendorDescription.getText(),
					vendorContact.getText(), vendorPhone.getText(), null, null, EntityStatus.ACTIVE);
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
        if(vendor != null && addVendor.addEditEntity(vendor)){
        	System.out.println("Vendor add for "+vendorName.getText()+" successful");
        }else{
        	System.out.println("Vendor add NOT successful");
        }
       
    }
 
     @FXML
     void addProduct(ActionEvent event) {
        PageNavigator.loadPages(PageNavigator.AddProduct);
    }
     @FXML
     void addVendor(ActionEvent event) {
        PageNavigator.loadPages(PageNavigator.AddVendor);
    }
      @FXML
     void inventoryAll(ActionEvent event) {
        PageNavigator.loadPages(PageNavigator.Inventory);
    }
     public void setPages(Node n) {
        pagePane.getChildren().setAll(n);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    
    
}
