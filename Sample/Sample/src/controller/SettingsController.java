package controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.GestionProduitsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class SettingsController implements Initializable{
	
	@FXML
    private AnchorPane topbar;

    @FXML
    private ToggleGroup type;

    @FXML
    private ToggleGroup couleur;
    
    @FXML
    private Label qte;

    @FXML
    private TextField prix;
    
    @FXML
    private Label chemin;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
    void add(MouseEvent event) {
		RadioButton rbtn1 = (RadioButton) type.getSelectedToggle();
		RadioButton rbtn2 = (RadioButton) couleur.getSelectedToggle();
		try {
			new GestionProduitsDAO().saveProduits(Integer.parseInt(qte.getText()), "red", "Pagne", Integer.parseInt(prix.getText()), chemin.getText());
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		prix.getScene().getWindow().hide();
    }
	
	@FXML
    void qtePlus(ActionEvent event) {
		int q=Integer.parseInt(qte.getText());
    	qte.setText(""+(q+1));
    }
	
	@FXML
    void close(ActionEvent event) {
		prix.getScene().getWindow().hide();
    }
    
    @FXML
    void qteMoins(ActionEvent event) {
    	int q=Integer.parseInt(qte.getText());
    	if(q>0) {
    	   qte.setText(""+(q-1));
    	}
    }
    
    @FXML
    void imageProd(MouseEvent event) {
    	FileChooser f = new FileChooser();
    	f.setTitle("Choisissez une image");
    	f.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),new FileChooser.ExtensionFilter("PNG", "*.png"));
    	
    	File file = f.showOpenDialog(qte.getScene().getWindow());
    	chemin.setText(file.getPath());
    }

}
