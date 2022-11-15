import java.io.IOException;

import BBDD.GestionPeliculas;
import BBDD.GestionSerie;
import BBDD.GestionUsers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainWindow {

	@FXML
	public Label nickname;
	@FXML
	public Circle circulo;
	@FXML
	public Button tv;
	@FXML
	public Button cine;
	@FXML
	public HBox reciente;
	@FXML
	public HBox recienteP;
	@FXML
	public HBox viendo;
	
	public static String nick;
	
	public void initialize() {
		nickname.setText(nick);
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(nick)));
		circulo.setFill(pattern);
		reciente.setSpacing(20);
		viendo.setSpacing(20);
		recienteP.setSpacing(20);
		reciente.getChildren().clear();
		recienteP.getChildren().clear();
		viendo.getChildren().clear();
		rellenarSiguiendo();
		rellenarUltimosPeli();
		rellenarUltimosSerie();
	}
	
	public void rellenarSiguiendo()
	{
		for (int codigo : GestionUsers.listaCodSerieSeguidos(nickname.getText()))
		{
			ImageView img = new ImageView(new Image(GestionSerie.getInfoShow(codigo, "imagen"),230,330,false,false));
			viendo.getChildren().add(img);
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			     @Override
			     public void handle(MouseEvent event) {
			         System.out.println("Tile pressed ");
			         ShowWindow.idShow = codigo;
			         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			         openShow(codigo, stage);
			         event.consume();
			     }
			});
		}
	}
	
	public void rellenarUltimosSerie()
	{
		for (int codigo : GestionSerie.listaCodSerieUltimos())
		{
			ImageView img = new ImageView(new Image(GestionSerie.getInfoShow(codigo, "imagen"),230,330,false,false));
			reciente.getChildren().add(img);
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			     @Override
			     public void handle(MouseEvent event) {
			         System.out.println("Tile pressed ");
			         ShowWindow.idShow = codigo;
			         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			         openShow(codigo, stage);
			         event.consume();
			     }
			});
		}
	}
	
	public void rellenarUltimosPeli()
	{
		for (int codigo : GestionPeliculas.listaCodPelisUltimos())
		{
			ImageView img = new ImageView(new Image(GestionPeliculas.getInfoFilm(codigo, "imagen"),230,330,false,false));
			recienteP.getChildren().add(img);
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			     @Override
			     public void handle(MouseEvent event) {
			         System.out.println("Tile pressed ");
			         FilmWindow.idFilm = codigo;
			         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			         openFilm(codigo, stage);
			         event.consume();
			     }
			});
		}
	}
	
	public void addContent(ActionEvent event)
	{
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("newContent.fxml"));
    	    root = fxml.<Pane>load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Nuevo contenido");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void openShow(int idShow, Stage stage)
	{
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("showMenu.fxml"));
    	    root = fxml.<BorderPane>load();
            stage.setScene(new Scene(root, 1320, 920));
            stage.setTitle("Play Plus - " + GestionSerie.getInfoShow(idShow, "nameSerie"));
            stage.getIcons().add(new Image("file:icon.png"));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void openFilm(int idFilm, Stage stage)
	{
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("filmMenu.fxml"));
    	    root = fxml.<BorderPane>load();
            stage.setScene(new Scene(root, 1320, 920));
            stage.setTitle("Play Plus - " + GestionPeliculas.getInfoFilm(idFilm, "namePelicula"));
            stage.getIcons().add(new Image("file:icon.png"));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void OpenConfig()
	{
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("config.fxml"));
    	    root = fxml.<Pane>load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Configuracion");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void goToMain(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("main.fxml"));
    	    root = fxml.<Pane>load();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Tu diario para el cine y la tv");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void goToSeriesZone(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("Series.fxml"));
    	    root = fxml.<Pane>load();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Series");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void goToFilmZone(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("FilmZone.fxml"));
    	    root = fxml.<Pane>load();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Peliculas");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void goToSearchZone(ActionEvent event)
	{
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("search.fxml"));
    	    root = fxml.<Pane>load();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Búsqueda");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}
