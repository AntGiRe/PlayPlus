
import BBDD.GestionPeliculas;
import BBDD.GestionUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FilmWindow extends ContentWindow {
	
	@FXML
	public Label name;
	@FXML
	public Label nickname;
	@FXML
	public Label productora;
	@FXML
	public Label anyo;
	@FXML
	public Label duracion;
	@FXML
	public Label director;
	@FXML
	public Label generos;
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
	public Button visto;
	
	public static int idFilm;
	
	public void initialize() {
		System.out.println(GestionPeliculas.getInfoFilm(idFilm,"namePelicula"));
		name.setText(GestionPeliculas.getInfoFilm(idFilm,"namePelicula"));
		description.setText(GestionPeliculas.getInfoFilm(idFilm, "descripcion"));
		duracion.setText(GestionPeliculas.getInfoFilm(idFilm, "duracion"));
		director.setText(GestionPeliculas.getInfoFilm(idFilm, "director"));
		generos.setText(GestionPeliculas.getInfoFilm(idFilm, "genero"));
		anyo.setText(GestionPeliculas.getInfoFilm(idFilm, "anyo").substring(0, 4));
		image.setImage(new Image(GestionPeliculas.getInfoFilm(idFilm,"imagen"),230,330,false,false));
		productora.setText(GestionPeliculas.getInfoFilm(idFilm, "productora"));
		nickname.setText(MainWindow.nick);
		if (Double.toString(GestionPeliculas.getPuntuacionGlobal(idFilm)).length() - Double.toString(GestionPeliculas.getPuntuacionGlobal(idFilm)).indexOf('.') > 2)
		{
			puntuacion.setText(Double.toString(GestionPeliculas.getPuntuacionGlobal(idFilm)).substring(0, Double.toString(GestionPeliculas.getPuntuacionGlobal(idFilm)).indexOf('.') + 3));
		}
		else
		{
			puntuacion.setText(Double.toString(GestionPeliculas.getPuntuacionGlobal(idFilm)));
		}
		if(GestionPeliculas.checkVisto(nickname.getText(), idFilm))
		{
			visto.setText("Marcar como no visto");
		}
		else
		{
			visto.setText("Marcar como visto");
		}
		numeroUsers.setText(Integer.toString(GestionPeliculas.getNumVotos(idFilm)));
		if (Integer.valueOf(GestionPeliculas.getPuntuacion(nickname.getText(), idFilm)) > 0)
		{
			star1.setImage(new Image("file:star.png"));
			if(Integer.valueOf(GestionPeliculas.getPuntuacion(nickname.getText(), idFilm)) > 1)
			{
				star2.setImage(new Image("file:star.png"));
				if(Integer.valueOf(GestionPeliculas.getPuntuacion(nickname.getText(), idFilm)) > 2)
				{
					star3.setImage(new Image("file:star.png"));
					if(Integer.valueOf(GestionPeliculas.getPuntuacion(nickname.getText(), idFilm)) > 3)
					{
						star4.setImage(new Image("file:star.png"));
						if(Integer.valueOf(GestionPeliculas.getPuntuacion(nickname.getText(), idFilm)) > 4)
						{
							star5.setImage(new Image("file:star.png"));
						}
					}
				}
			}
		}
		ImagePattern pattern = new ImagePattern(new Image(GestionUsers.getImg(MainWindow.nick)));
		circulo.setFill(pattern);
	}
	
	public void changePts(int stars)
	{
		if(GestionPeliculas.checkPuntuacion(nickname.getText(),idFilm))
		{
			GestionPeliculas.changePts(stars, nickname.getText(), idFilm);
		}
		else
		{
			GestionPeliculas.newPts(stars, nickname.getText(), idFilm);
		}
	}
	
	public void onVisto(ActionEvent event)
	{
		System.out.println("a");
		if(!GestionPeliculas.checkVisto(nickname.getText(), idFilm))
		{
			visto.setText("Marcar como no visto");
			GestionPeliculas.vistoPeli(nickname.getText(), idFilm);
		}
		else
		{
			visto.setText("Marcar como visto");
			GestionPeliculas.noVistoPeli(nickname.getText(), idFilm);
		}
	}
}
