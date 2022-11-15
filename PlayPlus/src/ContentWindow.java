import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ContentWindow extends MainWindow{
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
	
	public void volver(MouseEvent event) throws IOException
	{
		var fxml = new FXMLLoader(getClass().getResource("main.fxml"));
		var root = fxml.<BorderPane>load();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root, 1320, 920));
    	stage.setTitle("Play Plus - Tu diario para el cine y la tv");
    	stage.getIcons().add(new Image("file:icon.png"));
    	stage.show();
	}
	
	public void star1(MouseEvent event)
	{
		star1.setImage(new Image("file:star.png"));
		star2.setImage(new Image("file:star_out.png"));
		star3.setImage(new Image("file:star_out.png"));
		star4.setImage(new Image("file:star_out.png"));
		star5.setImage(new Image("file:star_out.png"));
		changePts(1);
	}
	
	public void star2(MouseEvent event)
	{
		star1.setImage(new Image("file:star.png"));
		star2.setImage(new Image("file:star.png"));
		star3.setImage(new Image("file:star_out.png"));
		star4.setImage(new Image("file:star_out.png"));
		star5.setImage(new Image("file:star_out.png"));
		changePts(2);
	}
	
	public void star3(MouseEvent event)
	{
		star1.setImage(new Image("file:star.png"));
		star2.setImage(new Image("file:star.png"));
		star3.setImage(new Image("file:star.png"));
		star4.setImage(new Image("file:star_out.png"));
		star5.setImage(new Image("file:star_out.png"));
		changePts(3);
	}
	
	public void star4(MouseEvent event)
	{
		star1.setImage(new Image("file:star.png"));
		star2.setImage(new Image("file:star.png"));
		star3.setImage(new Image("file:star.png"));
		star4.setImage(new Image("file:star.png"));
		star5.setImage(new Image("file:star_out.png"));
		changePts(4);
	}
	
	public void star5(MouseEvent event)
	{
		star1.setImage(new Image("file:star.png"));
		star2.setImage(new Image("file:star.png"));
		star3.setImage(new Image("file:star.png"));
		star4.setImage(new Image("file:star.png"));
		star5.setImage(new Image("file:star.png"));
		changePts(5);
	}
	
	public void changePts(int stars) {
	}

}
