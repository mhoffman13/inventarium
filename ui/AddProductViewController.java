//Variables on AddProductView are controlled here
package inventarium.ui;

import inventarium.action.EntityActionImpl;
import inventarium.entity.Category;
import inventarium.entity.Product;
import inventarium.entity.Vendor;
import inventarium.helper.EntityStatus;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddProductViewController implements Initializable {
	@FXML
	TextField productName;
	@FXML
	TextField productQuantity;
	@FXML
	TextField productVendor;
	@FXML
	TextField productNumber;
	@FXML
	TextField productSKU;
	@FXML
	TextField productDescription;
	
	
	@FXML
	private void handleSaveButton(ActionEvent event) {
		// missing fields in AddProductView.fxml
		Product product = new Product();
		product.setName(productName.getText());
		product.setQuantity(Integer.parseInt(productQuantity.getText()));
		product.setSku(productSKU.getText());
		product.setDescription(productDescription.getText());
		product.setVendor(new Vendor());
		product.setCategory(new Category());
		product.setStatus(EntityStatus.ACTIVE);
		// prepare to add product
		EntityActionImpl addAction = new EntityActionImpl(false);
		if(addAction.addEditEntity(product)){
			// if successfully added, show product detail view
			// until the view exists, just print to screen
			System.out.println("ADDED PRODUCT DATA ...");
			System.out.println("Name:\t\t" + product.getName());
			System.out.println("Quantity:\t\t" + product.getQuantity());
			System.out.println("Sku:\t\t\t" + product.getSku());
			System.out.println("Description:\t" + product.getDescription());
			System.out.println("Vendor Id:\t" + product.getVendor().getUniqueId());
		}else{
			//something went wrong, display message
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
}
