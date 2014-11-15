//Variables on AddProductView are controlled here
package inventarium.ui;

import inventarium.entity.Product;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class AddProductViewController implements Initializable {
    @FXML TextField productName;
    @FXML TextField productQuantity;
    @FXML TextField productVendor;
    @FXML TextField productNumber;
    @FXML TextField productSKU;
    @FXML TextField productDescription;
    
    
   @FXML
   private void handleSaveButton(ActionEvent event) {//method is used when 'Save Item' button is used. It gets text from the Textfields
        Product product = new Product();
        product.setName(productName.getText());
        System.out.println(product.getName());
        productQuantity.getText();
        productVendor.getText();
        productNumber.getText();
        productSKU.getText();
        productDescription.getText();
   
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
