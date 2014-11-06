package inventarium;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class DocumentController implements Initializable {
    
    @FXML
    private Label label;
    private Label vendorLabel;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Clicked");
        label.setText("Active");
    }
    @FXML
    private void handleVendorButton(ActionEvent event) {
        System.out.println("Vendor added");

        label.setText("");
    }
     private void mainStock(ActionEvent event) {//show total stock and low stock
       
        label.setText(""); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
