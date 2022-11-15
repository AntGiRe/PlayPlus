
import BBDD.GestionPeliculas;
import BBDD.GestionUsers;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FilmZone extends MainWindow{

	@FXML
	public Label nickname;
	@FXML
	public Circle circulo;
	@FXML
	public Button tv;
	@FXML
	public Button cine;
	@FXML
	public HBox recienteP;
	@FXML
	public HBox visto;
	
	public void initialize() {
		nickname.setText(MainWindow.nick);
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(MainWindow.nick)));
		circulo.setFill(pattern);
		visto.setSpacing(20);
		recienteP.setSpacing(20);
		recienteP.getChildren().clear();
		visto.getChildren().clear();
		rellenarUltimosPeli();
		rellenarVistos();
	}
	
	public void rellenarVistos()
	{
		for (int codigo : GestionPeliculas.listaPelisVistas(nickname.getText()))
		{
			ImageView img = new ImageView(new Image(GestionPeliculas.getInfoFilm(codigo, "imagen"),230,330,false,false));
			visto.getChildren().add(img);
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
}
