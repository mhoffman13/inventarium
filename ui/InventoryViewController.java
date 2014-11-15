package inventarium.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class InventoryViewController {
	private Label productName;

	void changePane(ActionEvent event) {
		PageNavigator.loadPages(PageNavigator.MAIN_MENU);
	}

	private Node view;

	public Node getView() {
		return view;
	}

	private void handleButtonAction(ActionEvent event) {

	}

	public void initialize(URL url, ResourceBundle rb) {
		// TODO

	}

}
