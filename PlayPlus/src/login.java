
import java.io.IOException;

import BBDD.GestionUsers;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class login {

	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Button btn_login;
	@FXML
	private Button plus;
	@FXML
	private Label error;
	@FXML
	private Label errorExists;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Label lbl4;
	@FXML
	private Label lbl3;
	
	public void AlPresionarBoton(ActionEvent event)
	{
		if (user.getText().length() > 0 && pass.getText().length() > 0 && user.getText().length() < 21)
		{
			if (GestionUsers.checkUser(user.getText(),pass.getText()))
			{
				MainWindow.nick = user.getText();
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				openApp(window);
			}
			else
			{
				errorExists.setOpacity(1);
			}
		}
		else
		{
			error.setOpacity(1);
		}
	}
	
	public void openApp(Stage stage)
	{
		try
		{
			var fxml = new FXMLLoader(getClass().getResource("main.fxml"));
			var root = fxml.<BorderPane>load();
			stage.setScene(new Scene(root, 1320, 920));
        	stage.setTitle("Play Plus - Tu diario para el cine y la tv");
        	stage.getIcons().add(new Image("file:icon.png"));
        	stage.setResizable(false);
        	stage.show();
		}
		catch (IOException e)
		{
            e.printStackTrace();
		}
	}
	
	public void AvisoFuera(KeyEvent event)
	{
		error.setOpacity(0);
		errorExists.setOpacity(0);
		if(event.getCode() == KeyCode.TAB){
			pass.requestFocus();
        }
	}
	
	public void register(ActionEvent event) {
        Parent root;
        try {
        	var fxml = new FXMLLoader(getClass().getResource("register.fxml"));
    	    root = fxml.<Pane>load();
    	    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Play Plus - Registro en curso");
            stage.getIcons().add(new Image("file:icon.png"));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
