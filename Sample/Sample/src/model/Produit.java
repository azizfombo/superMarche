package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produit {
	final private IntegerProperty idProduits;
	final private IntegerProperty quantite;
	final private StringProperty couleur;
	final private StringProperty Type;
	final private IntegerProperty prix;
	final private IntegerProperty taille;
	final private StringProperty photo;
	static int a=0;
	public Produit() {
		super();
		this.idProduits= new SimpleIntegerProperty();
		this.quantite = new SimpleIntegerProperty();
		this.couleur = new SimpleStringProperty();
		this.Type = new SimpleStringProperty();
		this.prix = new SimpleIntegerProperty();
		this.taille = new SimpleIntegerProperty();
		this.photo = new SimpleStringProperty();
	}
	public Produit(Integer idProduits, Integer quantite , String couleur, String Type,
			 Integer prix, Integer taille, String photo) {
		super();
		this.idProduits =new SimpleIntegerProperty(idProduits);
		this.quantite = new SimpleIntegerProperty(quantite);
		this.couleur = new SimpleStringProperty(couleur);
		this.Type = new SimpleStringProperty(Type);
		this.prix = new SimpleIntegerProperty(prix);
		this.taille= new SimpleIntegerProperty(taille);
		this.photo = new SimpleStringProperty(photo);
	}
	
	public final IntegerProperty idProduitsProperty() {
		return this.idProduits;
	}
	
	public final int getIdProduits() {
		return this.idProduitsProperty().get();
	}
	
	public final void setIdProduits(final int idProduits) {
		this.idProduitsProperty().set(idProduits);
	}
	
	public final IntegerProperty quantiteProperty() {
		return this.quantite;
	}
	
	public final int getQuantite() {
		return this.quantiteProperty().get();
	}
	
	public final void setQuantite(final int quantite) {
		this.quantiteProperty().set(quantite);
	}
	
	public final StringProperty couleurProperty() {
		return this.couleur;
	}
	
	public final String getCouleur() {
		return this.couleurProperty().get();
	}
	
	public final void setCouleur(final String couleur) {
		this.couleurProperty().set(couleur);
	}
	
	public final StringProperty TypeProperty() {
		return this.Type;
	}
	
	public final String getType() {
		return this.TypeProperty().get();
	}
	
	public final void setType(final String Type) {
		this.TypeProperty().set(Type);
	}
	
	public final IntegerProperty prixProperty() {
		return this.prix;
	}
	
	public final int getPrix() {
		return this.prixProperty().get();
	}
	
	public final void setPrix(final int prix) {
		this.prixProperty().set(prix);
	}
	
	public final IntegerProperty tailleProperty() {
		return this.taille;
	}
	
	public final int getTaille() {
		return this.tailleProperty().get();
	}
	
	public final void setTaille(final int taille) {
		this.tailleProperty().set(taille);
	}
	
	public final StringProperty photoProperty() {
		return this.photo;
	}
	
	public final String getPhoto() {
		return this.photoProperty().get();
	}
	
	public final void setPhoto(final String photo) {
		this.photoProperty().set(photo);
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
}
