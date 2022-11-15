import java.io.IOException;

import BBDD.GestionUsers;
import Others.EncryptPass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Register {
	@FXML
	private TextField user;
	@FXML
	private TextField email;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField pass1;
	@FXML
	private Button btn_register;
	@FXML
	private Label noCoincide;
	@FXML
	private Label UserExistente;
	@FXML
	private Label EmailExistente;
	
	public void register(ActionEvent event)
	{
		if (GestionUsers.checkIfExists(user.getText()) || user.getText().length() < 1)
		{
			UserExistente.setOpacity(1);
		}
		else if (GestionUsers.checkIfMailExists(email.getText()) || email.getText().length() < 1)
		{
			EmailExistente.setOpacity(1);
		}
		else if (pass.getText().equals(pass1.getText()) && pass.getText().length() > 4)
		{
			GestionUsers.addUser(user.getText(),email.getText(),EncryptPass.encryptPass(pass.getText()));
			Parent root;
	        try {
	        	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        	var fxml = new FXMLLoader(getClass().getResource("RegisterCompleted.fxml"));
	    	    root = fxml.<Pane>load();
	            stage.setScene(new Scene(root, 600, 200));
	            stage.setTitle("Play Plus - Registro completado");
	            stage.getIcons().add(new Image("file:icon.png"));
	            stage.setResizable(false);
	            stage.show();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		else
		{
			noCoincide.setOpacity(1);
		}
	}
	
	public void AvisosFuera(KeyEvent event)
	{
		UserExistente.setOpacity(0);
		EmailExistente.setOpacity(0);
		noCoincide.setOpacity(0);
	}
	
	public void goToLogin(ActionEvent event) throws IOException
	{
		var fxml = new FXMLLoader(getClass().getResource("control.fxml"));
	    var root = fxml.<Pane>load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Play Plus - Tu diario para el cine y la tv");
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(false);
        stage.show();
	}
}
