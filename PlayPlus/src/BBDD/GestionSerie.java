package BBDD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestionSerie {
    
    public static void addShow(String name, String entrega, String plataforma, String enlace, String descrp, String nick)
    {
    	if(enlace.length() < 1)
    	{
    		enlace = "https://i.imgur.com/28eMWBv.png";
    	}
    	String sql = "insert into serie (nameSerie, nameTemp, plataforma, descripcion, imagen, nickname_add, fecha_add) values (?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, name);
		      preparedStmt.setString (2, entrega);
		      preparedStmt.setString (3, plataforma);
		      preparedStmt.setString (4, descrp);
		      preparedStmt.setString (5, enlace);
		      preparedStmt.setString (6, nick);
		      preparedStmt.setDate(7, Date.valueOf(LocalDate.now()));

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static String getInfoShow(int idShow, String text)
    {
    	String sql = "select " + text + " from serie where codSerie = ?;";
    			
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
    
    public static ArrayList<Integer> listaCodSerieUltimos()
    {
    	String sql = "select * from serie order by fecha_add desc limit 5;";
    	ArrayList<Integer> lista = new ArrayList<Integer>();
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				lista.add(rs.getInt("codSerie"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return lista;
    }
    
    public static ArrayList<Integer> listaTemporadas(int codSerie)
    {
    	String sql = "select * from temporada where codigoSerie = ?;";
    	ArrayList<Integer> lista = new ArrayList<Integer>();
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, codSerie);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				lista.add(rs.getInt("codTemporada"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return lista;
    }
    
    public static String getInfoSeason(int idSeason, String text)
    {
    	String sql = "select " + text + " from temporada where codTemporada = ?;";
    			
    	try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		    preparedStmt.setInt(1, idSeason);

		    ResultSet rs = preparedStmt.executeQuery();
		      
		    while(rs.next()) {
		    	return rs.getString(text);
			}
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    	return null;
    }
    
    public static String getSeason(int season, int idShow, String text)
    {
    	String sql = "select " + text + " from temporada where numTemporada = ? and codigoSerie = ?;";
    			
    	try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		    preparedStmt.setInt(1, season);
		    preparedStmt.setInt(2, idShow);

		    ResultSet rs = preparedStmt.executeQuery();
		      
		    while(rs.next()) {
		    	return rs.getString(text);
			}
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    	return null;
    }
    
    public static boolean checkPuntuacionSerie(String user, int codSerie)
    {
    	String sql = "select * from puntuacionesSerie where nombreUser = ? and codigoSerie = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			stmt.setInt (2, codSerie);
			
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
    
    public static String getPuntuacionSerie(String user, int codSerie)
    {
    	String sql = "select * from puntuacionesSerie where nombreUser = ? and codigoSerie = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			stmt.setInt (2, codSerie);
			
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
    
    public static void changePtsSerie(int stars, String user, int idShow)
    {
    	String sql = "UPDATE puntuacionesSerie SET puntuacion = ? WHERE nombreUser = ? and codigoSerie = ?";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
			preparedStmt.setInt (1, stars);
		    preparedStmt.setString (2, user);
		    preparedStmt.setInt   (3, idShow);

		    preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static void newPtsSerie(int stars, String user, int idShow)
    {
    	String sql = "insert into puntuacionesSerie (nombreUser, puntuacion, codigoSerie) values (?,?,?)";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setInt (2, stars);
		      preparedStmt.setInt   (3, idShow);

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static double getPuntuacionGlobalSerie(int codSerie)
    {
    	int sum;
    	sum = 0;
    	String sql = "select * from puntuacionesSerie where codigoSerie = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt (1, codSerie);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				sum = sum + rs.getInt("puntuacion");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return (double)sum / (double)getNumVotosSerie(codSerie);
    }
    
    public static int getNumVotosSerie(int codSerie)
    {
    	int sum;
    	
    	sum = 0;
    	String sql = "select * from puntuacionesSerie where codigoSerie = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setInt (1, codSerie);
			
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
    
    public static void addSeason(int codSerie, int numTemp, String descrp, int anyo)
    {
    	String sql = "insert into temporada (codigoSerie, numTemporada, descripcion, anyo) values (?,?,?,?);";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setInt (1, codSerie);
		      preparedStmt.setInt (2, numTemp);
		      preparedStmt.setString (3, descrp);
		      preparedStmt.setInt (4, anyo);

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
}
