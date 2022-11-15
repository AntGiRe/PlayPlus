import BBDD.GestionUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Config {

	@FXML
	public Button save;
	@FXML
	public Button btn_default;
	@FXML
	public TextField imagen;
	
	public void newImage(ActionEvent event)
	{
		GestionUsers.setImg(MainWindow.nick, imagen.getText());
	}
	
	public void defaultImg(ActionEvent event)
	{
		GestionUsers.setImg(MainWindow.nick, "https://i.imgur.com/Muxov2f.png");
	}
}
