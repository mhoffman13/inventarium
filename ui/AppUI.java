package inventarium.ui;

import inventarium.data.DataRequest;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppUI extends Application {
    
    @Override
    public void start(Stage canv) throws Exception {
           canv.setScene(
            createScene(
                loadPane()
            )
        );

        canv.show();
    }
      private Pane loadPane() throws IOException {
        FXMLLoader load = new FXMLLoader();//loads page

        Pane main = (Pane) load.load(//main pane
            getClass().getResourceAsStream(
                PageNavigator.MAIN           
            )
        );
        UIListener mainDocumentController = load.getController();
        PageNavigator.setMainDocumentController(mainDocumentController);
        inventarium.ui.PageNavigator.loadPages(inventarium.ui.PageNavigator.SystemContainer);//Main Page Under Menu      
        return main;
    }
   
        private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );


        return scene;
    }

    public static void main(String[] args) {
//    	DataRequest.initialize();
        launch(args);
    }
    
}
