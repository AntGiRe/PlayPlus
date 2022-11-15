
import BBDD.GestionSerie;
import BBDD.GestionUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class WindowSeason extends ContentWindow {
	
	@FXML
	public Label nickname;
	@FXML
	public Label name;
	@FXML
	public Circle circulo;
	
	public void initialize() {
		nickname.setText(MainWindow.nick);
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(MainWindow.nick)));
		circulo.setFill(pattern);
		name.setText(GestionSerie.getInfoShow(ShowWindow.idShow, "nameSerie") + ": " + GestionSerie.getInfoShow(ShowWindow.idShow, "nameTemp") + " " + GestionSerie.getInfoSeason(Integer.valueOf(ShowWindow.idSeasonShown), "numTemporada"));
	}
	
}
