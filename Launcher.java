package inventarium;

import inventarium.data.DataRequest;
import inventarium.ui.PageNavigator;
import inventarium.ui.UIListener;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application {

	@Override
	public void start(Stage canv) throws Exception {
		canv.setScene(createScene(loadPane()));

		canv.show();
	}

	private Pane loadPane() throws IOException {
		FXMLLoader load = new FXMLLoader();// loads page
		// main menu
		Pane main = (Pane) load.load(getClass().getResourceAsStream(PageNavigator.MAIN_MENU));
		UIListener mainDocumentController = load.getController();
		PageNavigator.setMainDocumentController(mainDocumentController);
		// load main page under menu
		inventarium.ui.PageNavigator.loadPages(inventarium.ui.PageNavigator.MAIN_VIEW);	
		return main;
	}

	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);

		return scene;
	}

	public static void main(String[] args) {
		DataRequest.initialize();
		launch(args);
	}

}
