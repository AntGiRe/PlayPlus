import BBDD.GestionUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SearchZone extends MainWindow {
	
	@FXML
	public Label nickname;
	@FXML
	public Circle circulo;
	@FXML
	public Button tv;
	@FXML
	public Button cine;
	@FXML
	public TextField search;
	
	public void initialize() {
		nickname.setText(nick);
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(nick)));
		circulo.setFill(pattern);
	}
}
