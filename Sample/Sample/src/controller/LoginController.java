package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connexion.ConnexionDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {


	@FXML
	private TextField username;

	@FXML
	private Button signin;

	@FXML
	private PasswordField password;

	@FXML
	private Label etat;

	double xOffset;
	double yOffset;
	int a=0;
	@FXML
	void authentification(ActionEvent event) throws SQLException {

		Connection conn = ConnexionDB.connect();
		PreparedStatement stat=null;
		ResultSet rs=null;
		String sql="SELECT * FROM User WHERE login= ? AND password= ?";
		String sql1 = "UPDATE user SET Statut = 1 WHERE login= ?";

		try {

			stat = conn.prepareStatement(sql1);
			stat.setString(1, username.getText().toString());
			stat.executeUpdate();

			stat=conn.prepareStatement(sql);
			stat.setString(1, username.getText().toString());
			stat.setString(2, password.getText().toString());
			rs=stat.executeQuery();

			if(rs.next()) {    		
				Stage stage = new Stage();
				stage.initStyle(StageStyle.TRANSPARENT);
				AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AccueilView.fxml"));  				
				Scene scene = new Scene(root);
				root.setOnMouseMoved(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						xOffset = e.getSceneX();
						yOffset = e.getSceneY();
					}
				});
				root.setOnMouseDragged(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						stage.setX(e.getScreenX()-xOffset);
						stage.setY(e.getScreenY()-yOffset);
					}
				});
				scene.setFill(Color.TRANSPARENT);
				stage.setScene(scene);
				stage.show();
				signin.getScene().getWindow().hide();
			}
			else {
				etat.setText("disconnected");
			}	
		}catch (Exception e) {
			
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		username.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case ENTER:
					try {
						authentification(null);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		password.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case ENTER:
					try {
						authentification(null);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}