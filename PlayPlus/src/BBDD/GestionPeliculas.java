package BBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestionPeliculas {

	public static void addFilm(String nombre, String director, String anyo, String duracion, String productora, String portada, String descripcion, String nick)
	{
		if(portada.length() < 1)
    	{
    		portada = "https://i.imgur.com/28eMWBv.png";
    	}
		String sql = "insert into pelicula (namePelicula, anyo, duracion, director, productora, descripcion, imagen, nickname_add, fecha_add) values (?,?,?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, nombre);
		      preparedStmt.setString (2, anyo);
		      preparedStmt.setString (3, duracion);
		      preparedStmt.setString (4, director);
		      preparedStmt.setString (5, productora);
		      preparedStmt.setString (6, descripcion);
		      preparedStmt.setString (7, portada);
		      preparedStmt.setString (8, nick);
		      preparedStmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static ArrayList<Integer> listaCodPelisUltimos()
    {
    	String sql = "select * from pelicula order by fecha_add desc limit 5;";
    	ArrayList<Integer> lista = new ArrayList<Integer>();
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				lista.add(rs.getInt("codPelicula"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return lista;
    }
	
	public static ArrayList<Integer> listaPelisVistas(String user)
    {
    	String sql = "select * from PeliculasVistas where nombreUser = ?;";
    	ArrayList<Integer> lista = new ArrayList<Integer>();
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				lista.add(rs.getInt("codigoFilm"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return lista;
    }
	
	public static String getInfoFilm(int idShow, String text)
    {
    	String sql = "select " + text + " from pelicula where codPelicula = ?;";
    			
    	try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		    preparedStmt.setInt(1, idShow);

		    ResultSet rs = preparedStmt.executeQuery();
		      
		    while(rs.next()) {
		    	return rs.getString(text);
			}
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    	return null;
    }
	
	public static boolean checkPuntuacion(String user, int codPeli)
    {
    	String sql = "select * from puntuacionesPeliculas where nombreUser = ? and codigoPelicula = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			stmt.setInt (2, codPeli);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
    }
    
    public static String getPuntuacion(String user, int codPeli)
    {
    	String sql = "select * from puntuacionesPeliculas where nombreUser = ? and codigoPelicula = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			stmt.setInt (2, codPeli);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return rs.getString("puntuacion");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "0";
    }
    
    public static void changePts(int stars, String user, int codPeli)
    {
    	String sql = "UPDATE puntuacionesPeliculas SET puntuacion = ? WHERE nombreUser = ? and codigoPelicula = ?";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
			preparedStmt.setInt (1, stars);
		    preparedStmt.setString (2, user);
		    preparedStmt.setInt (3, codPeli);

		    preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static void newPts(int stars, String user, int codPeli)
    {
    	String sql = "insert into puntuacionesPeliculas (nombreUser, puntuacion, codigoPelicula) values (?,?,?)";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setInt (2, stars);
		      preparedStmt.setInt   (3, codPeli);

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static double getPuntuacionGlobal(int codPeli)
    {
    	int sum;
    	sum = 0;
    	String sql = "select * from puntuacionesPeliculas where codigoPelicula = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt (1, codPeli);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				sum = sum + rs.getInt("puntuacion");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return (double)sum / (double)getNumVotos(codPeli);
    }
    
    public static int getNumVotos(int codPeli)
    {
    	int sum;
    	
    	sum = 0;
    	String sql = "select * from puntuacionesPeliculas where codigoPelicula = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt (1, codPeli);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				sum = sum + 1;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return sum;
    }
    
    public static boolean checkVisto(String user, int codPeli)
    {
    	String sql = "select * from PeliculasVistas where nombreUser = ? and codigoFilm = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			stmt.setInt (2, codPeli);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
    }
    
    public static void vistoPeli(String user, int codPeli)
    {
    	String sql = "insert into PeliculasVistas (nombreUser, codigoFilm) values (?,?)";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setInt (2, codPeli);

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static void noVistoPeli(String user, int codPeli)
    {
    	String sql = "delete from PeliculasVistas where nombreUser = ? and codigoFilm = ?";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setInt (2, codPeli);

		      preparedStmt.executeUpdate();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
}
