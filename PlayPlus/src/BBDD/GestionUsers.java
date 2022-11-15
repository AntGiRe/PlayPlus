package BBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Others.EncryptPass;

public class GestionUsers {
    
    public static boolean checkUser(String nick, String pass)
    {
    	String sql = "select * from users";
		
		PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(nick.equals(rs.getString("nickname")))
				{
					return (EncryptPass.verifyPass(pass, rs.getString("pass")));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
    }
    
    public static boolean checkIfExists(String nick)
    {
    	String sql = "select * from users";
		
		PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(nick.equals(rs.getString("nickname")))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
    }
    
    public static boolean checkIfMailExists(String mail)
    {
    	String sql = "select * from users";
		
		PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				if(mail.equals(rs.getString("email")))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
    }
    
    public static void addUser(String user, String mail, String pass)
    {
    	String sql = "insert into users (nickname, email, pass) values (?,?,?)";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setString (2, mail);
		      preparedStmt.setString   (3, pass);

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static void seguirSerie(String user, int codSerie)
    {
    	String sql = "insert into seguidos (nombreUser, codigoSerie) values (?,?)";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setInt (2, codSerie);

		      preparedStmt.execute();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static void dejarSerie(String user, int codSerie)
    {
    	String sql = "delete from seguidos where nombreUser = ? and codigoSerie = ?";
		
		try {
			PreparedStatement preparedStmt = ConexionBBDD.conn.prepareStatement(sql);
		      preparedStmt.setString (1, user);
		      preparedStmt.setInt (2, codSerie);

		      preparedStmt.executeUpdate();
		      
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
    
    public static boolean checkSigueSerie(String user, int codSerie)
    {
    	String sql = "select * from seguidos where nombreUser = ? and codigoSerie = ?";
    	
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
    
    public static ArrayList<Integer> listaCodSerieSeguidos(String user)
    {
    	String sql = "select * from seguidos where nombreUser = ?";
    	ArrayList<Integer> lista = new ArrayList<Integer>();
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, user);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				lista.add(rs.getInt("codigoSerie"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return lista;
    }
    
    public static String getImg(String nick)
    {
    	String sql = "select imagen from users where nickname = ?;";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, nick);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return rs.getString("imagen");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "https://i.imgur.com/Muxov2f.png";
    }
    
    public static void setImg(String nick, String img)
    {
    	String sql = "update users set imagen = ? where nickname = ?";
    	
    	PreparedStatement stmt;
		try {
			stmt = ConexionBBDD.conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString (1, img);
			stmt.setString (2, nick);
			
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
    }
}
