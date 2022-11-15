import BBDD.GestionSerie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TemporadaTV {

	@FXML
	public Button subir;
	@FXML
	public TextField numero;
	@FXML
	public TextField anyo;
	@FXML
	public Label nombre;
	@FXML
	public TextArea descrp;
	
	static int codSerie;
	
	public void addSeason(ActionEvent event)
	{
		GestionSerie.addSeason(codSerie, Integer.valueOf(numero.getText()), descrp.getText(), Integer.valueOf(anyo.getText()));
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
}
