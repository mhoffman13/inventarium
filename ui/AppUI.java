package inventarium.ui;

import inventarium.data.DataRequest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppUI extends Application {
    
    @Override
    public void start(Stage canv) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        canv.setMinHeight(600.0);
        canv.setMinWidth(800.0);
        canv.setScene(scene);
        canv.show();
    }

    public static void main(String[] args) {
    	DataRequest.initialize();
        launch(args);
    }
    
}
