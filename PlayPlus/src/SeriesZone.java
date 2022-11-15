
import BBDD.GestionUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SeriesZone extends MainWindow{
	
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
	public HBox viendo;

	public void initialize() {
		nickname.setText(MainWindow.nick);
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(MainWindow.nick)));
		circulo.setFill(pattern);
		reciente.setSpacing(20);
		viendo.setSpacing(20);
		reciente.getChildren().clear();
		viendo.getChildren().clear();
		rellenarSiguiendo();
		rellenarUltimosSerie();
	}

}
