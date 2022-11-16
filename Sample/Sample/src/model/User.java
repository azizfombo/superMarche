package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	final private IntegerProperty id;
	final private StringProperty login;
	final private StringProperty password;
	final private StringProperty NomUser;
	final private IntegerProperty TelUser;
	final private StringProperty poste;
	final private IntegerProperty Statut;
	
	static int a = 0;
	
	public User() {
		this.id = new SimpleIntegerProperty();
		this.login = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.NomUser = new SimpleStringProperty();
		this.TelUser = new SimpleIntegerProperty();
		this.poste = new SimpleStringProperty();
		this.Statut = new SimpleIntegerProperty(0);
	}

	public User(String login, String password, String nomUser,
			int telUser, String poste) {
		this.id = new SimpleIntegerProperty(++a);
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.NomUser = new SimpleStringProperty(nomUser);
		this.TelUser = new SimpleIntegerProperty(telUser);
		this.poste = new SimpleStringProperty(poste);
		this.Statut = new SimpleIntegerProperty(0);
	}

	public int getId() {
		return this.id.get();
	}

	public String getLogin() {
		return this.login.get();
	}

	public String getPassword() {
		return this.password.get();
	}

	public String getNomUser() {
		return this.NomUser.get();
	}

	public Integer getTelUser() {
		return this.TelUser.get();
	}

	public String getPoste() {
		return this.poste.get();
	}

	public int getStatut() {
		return this.Statut.get();
	}
	
	public void setId(int id) {
        this.id.set(id);
    }
	
	public void setNomUser(String nom) {
        this.NomUser.set(nom);
    }
	
	public void setA(int a) {
		this.a = a;
	}

}
