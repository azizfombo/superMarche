package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.ConnexionDB;
import model.Produit;

public class GestionProduitsDAO {
	
	public static int saveProduits(int qte, String couleur, String type, int prix, String tof) throws SQLException {
		Connection con=ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="INSERT INTO produit (quantite,couleur,Type,prix,taille,photo)Values(?,?,?,?,?,?)";
			//Transformation de la requete en SQL
			stat=con.prepareStatement(sql);
			//COMPLETONS LES ? PAR LES VALEURS DE LA BD
			stat.setInt(1,qte);
			stat.setString(2,couleur);
			stat.setString(3,type);
			stat.setInt(4,prix);
			stat.setInt(5,36);
			stat.setString(6,tof);
			//EXECUTION 
			valeur=stat.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return valeur;
	}
	public static int UpdateProduits(int qte, int prix, int id) throws SQLException {
		Connection con = ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="Update produit set prix=?, quantite=? WHERE idProduits=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1,qte);
			stat.setInt(2,prix);
			stat.setInt(3,id);
			valeur=stat.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return valeur;
	}
	public static int DeleteProduits(int idProduits) throws SQLException {
		Connection con = ConnexionDB.connect();
		PreparedStatement stat=null;
		int valeur=0;
		try {
			String sql="Delete FROM produit WHERE idProduits=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1,idProduits);
			valeur=stat.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return valeur;
	}
	public static Produit RechercheProduits(int idProduits) throws SQLException {
		Connection con = ConnexionDB.connect();
		Produit p =new Produit();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			String sql="Select * FROM produit WHERE idProduits=?";
			stat=con.prepareStatement(sql);
			stat.setInt(1,idProduits);
			rs=stat.executeQuery();
			if(rs.next()) {
				p.setIdProduits(rs.getInt("idProduits"));
				p.setQuantite(rs.getInt("quantite"));
				p.setCouleur(rs.getString("couleur"));
				p.setType(rs.getString("Type"));
				p.setPrix(rs.getInt("prix"));
				p.setTaille(rs.getInt("taille"));
				p.setPhoto(rs.getString("photo"));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return p;
	}

}
