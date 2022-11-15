import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import BBDD.GestionSerie;
import BBDD.GestionUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShowWindow extends ContentWindow{
	
	@FXML
	public Label name;
	@FXML
	public Label nickname;
	@FXML
	public Label plataforma;
	@FXML
	public Circle circulo;
	@FXML
	public Label description;
	@FXML
	public Label puntuacion;
	@FXML
	public Label numeroUsers;
	@FXML
	public ImageView image;
	@FXML
	public ImageView star1;
	@FXML
	public ImageView star2;
	@FXML
	public ImageView star3;
	@FXML
	public ImageView star4;
	@FXML
	public ImageView star5;
	@FXML
	public Button seguir;
	@FXML
	public ListView<String> temporadas;
	
	public static int idShow;
	
	public static String idSeasonShown;
	
	public void initialize() {
		System.out.println(GestionSerie.getInfoShow(idShow,"nameSerie"));
		name.setText(GestionSerie.getInfoShow(idShow,"nameSerie"));
		description.setText(GestionSerie.getInfoShow(idShow, "descripcion"));
		image.setImage(new Image(GestionSerie.getInfoShow(idShow,"imagen"),230,330,false,false));
		plataforma.setText(GestionSerie.getInfoShow(idShow, "plataforma"));
		nickname.setText(MainWindow.nick);
		if (Double.toString(GestionSerie.getPuntuacionGlobalSerie(idShow)).length() - Double.toString(GestionSerie.getPuntuacionGlobalSerie(idShow)).indexOf('.') > 2)
		{
			puntuacion.setText(Double.toString(GestionSerie.getPuntuacionGlobalSerie(idShow)).substring(0, Double.toString(GestionSerie.getPuntuacionGlobalSerie(idShow)).indexOf('.') + 3));
		}
		else
		{
			puntuacion.setText(Double.toString(GestionSerie.getPuntuacionGlobalSerie(idShow)));
		}
		if(GestionUsers.checkSigueSerie(nickname.getText(), idShow))
		{
			seguir.setText("Siguiendo");
		}
		else
		{
			seguir.setText("Seguir");
		}
		numeroUsers.setText(Integer.toString(GestionSerie.getNumVotosSerie(idShow)));
		if (Integer.valueOf(GestionSerie.getPuntuacionSerie(nickname.getText(), idShow)) > 0)
		{
			star1.setImage(new Image("file:star.png"));
			if(Integer.valueOf(GestionSerie.getPuntuacionSerie(nickname.getText(), idShow)) > 1)
			{
				star2.setImage(new Image("file:star.png"));
				if(Integer.valueOf(GestionSerie.getPuntuacionSerie(nickname.getText(), idShow)) > 2)
				{
					star3.setImage(new Image("file:star.png"));
					if(Integer.valueOf(GestionSerie.getPuntuacionSerie(nickname.getText(), idShow)) > 3)
					{
						star4.setImage(new Image("file:star.png"));
						if(Integer.valueOf(GestionSerie.getPuntuacionSerie(nickname.getText(), idShow)) > 4)
						{
							star5.setImage(new Image("file:star.png"));
						}
					}
				}
			}
		}
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(MainWindow.nick)));
		circulo.setFill(pattern);
		ObservableList<String> seasons = FXCollections.observableArrayList();
		ArrayList<Integer> codTemporada = GestionSerie.listaTemporadas(idShow);
		ArrayList<Integer> numTemporada = new ArrayList<Integer>();
		for (int codTemp : codTemporada)
		{
			numTemporada.add(Integer.valueOf(GestionSerie.getInfoSeason(codTemp, "numTemporada")));
		}
		Collections.sort(numTemporada);
		for (int numTempo : numTemporada)
		{
			seasons.add(GestionSerie.getInfoShow(ShowWindow.idShow, "nameTemp") + " " + numTempo);
		}
		System.out.println(seasons.toString());
		temporadas.setItems(seasons);
	
		temporadas.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				Parent root;
			    try {
			    	idSeasonShown = GestionSerie.getSeason(numTemporada.get(temporadas.getSelectionModel().getSelectedIndex()), idShow, "codTemporada");
			        var fxml = new FXMLLoader(getClass().getResource("temporadaTV.fxml"));
			    	root = fxml.<Pane>load();
			    	Stage stage = (Stage)((Node)arg0.getSource()).getScene().getWindow();
			        stage.setScene(new Scene(root, 1320, 920));
			        stage.setTitle("Play Plus - " + GestionSerie.getInfoShow(ShowWindow.idShow, "nameSerie") + ": " + GestionSerie.getInfoShow(ShowWindow.idShow, "nameTemp") + " " + GestionSerie.getInfoSeason(Integer.valueOf(ShowWindow.idSeasonShown), "numTemporada"));
			        stage.getIcons().add(new Image("file:icon.png"));
			        stage.setResizable(false);
			        stage.show();
			    }
			    catch (IOException e) {
			        e.printStackTrace();
			    }
			    catch (IndexOutOfBoundsException e)
			    {
			    	
			    }
			}
		});
	}
	
	public void changePts(int stars)
	{
		if(GestionSerie.checkPuntuacionSerie(nickname.getText(),idShow))
		{
			GestionSerie.changePtsSerie(stars, nickname.getText(), idShow);
		}
		else
		{
			GestionSerie.newPtsSerie(stars, nickname.getText(), idShow);
		}
	}
	
	public void buttonFollow(ActionEvent event)
	{
		if(GestionUsers.checkSigueSerie(nickname.getText(), idShow))
		{
			seguir.setText("Seguir");
			GestionUsers.dejarSerie(nickname.getText(), idShow);
		}
		else
		{
			seguir.setText("Siguiendo");
			GestionUsers.seguirSerie(nickname.getText(), idShow);
		}
	}
	
	public void addSeason(ActionEvent event)
	{
		Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("newSeason.fxml"));
    	    root = fxml.<Pane>load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Play Plus - Añadiendo nueva temporada");
            TemporadaTV.codSerie = idShow;
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}
