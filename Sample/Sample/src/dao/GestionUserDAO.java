package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.ConnexionDB;
import model.User;

public class GestionUserDAO {
	public static int saveUser(User u) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="INSERT INTO user (login,password,NomUser,TelUser,poste)Values(?,?,?,?,?)";
			
			stat=con.prepareStatement(sql);
			
			stat.setString(1,u.getLogin());
			stat.setString(2,u.getPassword());
			stat.setString(3,u.getNomUser());
			stat.setInt(4,u.getTelUser());
			stat.setString(5,u.getPoste());
			
			valeur=stat.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return valeur;
	}
	public static int UpdateUser(String login, String password, int tel, String nom) throws SQLException {
		Connection con = ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="Update user set login=?, password=?, TelUser=? WHERE NomUser=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,login);
			stat.setString(2,password);
			stat.setInt(3,tel);
			stat.setString(4,nom);
			valeur=stat.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return valeur;
	}
	public static int DeleteUser(String login) throws SQLException {
		Connection con = ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="Delete FROM user WHERE login=?";
			stat=con.prepareStatement(sql);
			stat.setString(1,login);
			valeur=stat.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return valeur;
	}
	/*public static User RechercheUser(int id) throws SQLException {
		Connection con = ConnexionDB.connect();
		User u =new User();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			String sql="Select* from user Where id=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1,id);
			rs=stat.executeQuery();
			if(rs.next()) {
				u.setLogin(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setNomUser(rs.getString(4));
				u.setTelUser(rs.getInt(5));
				u.setPoste(rs.getString(6));
				u.setStatut(rs.getInt(7));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return u;
	}*/

}
