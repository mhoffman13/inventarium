
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AppUI extends Application {
    
    @Override
    public void start(Stage canv) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        canv.setScene(scene);
        canv.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
