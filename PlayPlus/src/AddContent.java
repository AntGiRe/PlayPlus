import java.io.IOException;

import BBDD.GestionPeliculas;
import BBDD.GestionSerie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddContent {
	
	@FXML
	public TextField nombre;
	@FXML
	public TextField entrega;
	@FXML
	public TextField plataforma;
	@FXML
	public TextField portada;
	@FXML
	public TextArea descrp;
	@FXML
	public TextField productora;
	@FXML
	public TextField director;
	@FXML
	public TextField anyo;
	@FXML
	public TextField duracion;

	public void addTV(ActionEvent event)
	{
		((Node)(event.getSource())).getScene().getWindow().hide();
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("addTV.fxml"));
    	    root = fxml.<Pane>load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Añadiendo Programa de TV");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void addFilm(ActionEvent event)
	{
		((Node)(event.getSource())).getScene().getWindow().hide();
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("addFilm.fxml"));
    	    root = fxml.<Pane>load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Añadiendo pelicula");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void uploadTV(ActionEvent event)
	{
		GestionSerie.addShow(nombre.getText(), entrega.getText(), plataforma.getText(), portada.getText(), descrp.getText(), MainWindow.nick);
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public void uploadFilm(ActionEvent event)
	{
		GestionPeliculas.addFilm(nombre.getText(), director.getText(), anyo.getText(), duracion.getText(), productora.getText(), portada.getText(), descrp.getText(), MainWindow.nick);
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}
