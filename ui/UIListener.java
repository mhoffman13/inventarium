package inventarium.ui;

import inventarium.action.EntityActionImpl;
import inventarium.entity.Vendor;
import inventarium.helper.Address;
import inventarium.helper.EntityStatus;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class UIListener implements Initializable {
    
    @FXML private Label label;
    @FXML private Label vendorLabel;
    @FXML private TextField vendorName;
    @FXML private TextField vendorNumber;
    @FXML private TextField vendorDescription;
    @FXML private TextField vendorContact;
    @FXML private TextField vendorPhone;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Clicked");
        label.setText("Active");
    }
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
        label.setText("");
    }
     private void mainStock(ActionEvent event) {//show total stock and low stock
       
        label.setText(""); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
