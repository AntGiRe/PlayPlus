
import java.io.IOException;

import BBDD.ConexionBBDD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal extends Application{
	
    public static void main(String[] args) {
    	ConexionBBDD.connect();
        launch(args);
        ConexionBBDD.close();
    }
    
    @Override
    public void start(Stage stage) throws IOException 
    {
    	var fxml = new FXMLLoader(getClass().getResource("control.fxml"));
	    var root = fxml.<Pane>load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Play Plus - Inicia sesión");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.show();
    }
}
