package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.MyListener;
import connexion.ConnexionDB;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.User;

public class UserController implements Initializable {

	@FXML
	private TextField login;

	@FXML
	private TextField tel;

	@FXML
	private ScrollPane scroll;

	@FXML
	private GridPane grid;

	@FXML
	private AnchorPane root;

	@FXML
	private Pane p;

	@FXML
	private VBox chosenFruitCard;

	@FXML
	private Label fruitNameLable;

	@FXML
	private Label fruitPriceLabel;

	@FXML
	private ImageView fruitImg;

	@FXML
	private Label h1;

	@FXML
	private Label h2;

	@FXML
	private HBox h3;

	private ObservableList<User> data = FXCollections.observableArrayList();  
	private Image image;
	private MyListener myListener;

	private ObservableList<User> getData() throws SQLException{
		ObservableList<User> data = FXCollections.observableArrayList();
		User user = new User();

		Connection con = ConnexionDB.connect();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = null;
		try {
			data.clear();
			query = "SELECT * FROM user";
			p = con.prepareStatement(query);
			rs = p.executeQuery();
			user.setA(0);
			while (rs.next()){
				data.add(new User(
					rs.getString("login"),
					rs.getString("password"),
					rs.getString("NomUser"),
					rs.getInt("TelUser"),
					rs.getString("poste")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		} 
		return data;
	}

	private void setChosenUser(User user) {
		fruitNameLable.setText(user.getNomUser());
		fruitPriceLabel.setText("N." + user.getId());
		login.setText(user.getLogin());
		tel.setText("+237 " + user.getTelUser());
		image = new Image(getClass().getResourceAsStream("/view/icons8_user_60px.png"));
		fruitImg.setImage(image);
	}


	@FXML
	void left(ActionEvent event) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(1),p);
		translate.setToX(root.getParent().getLayoutX());
		translate.play();

		fruitNameLable.setText("");
		fruitPriceLabel.setText("");
		h1.setText("");
		h2.setText("");
		h3.setVisible(false);
		login.setText("");
		tel.setText("");
		image = new Image(getClass().getResourceAsStream("/view/icons8_add_user_male_52px.png"));
		fruitImg.setImage(image);    	
		login.setStyle("-fx-background-color: transparent;");
		tel.setStyle("-fx-background-color: transparent;");
	}

	@FXML
	void right(ActionEvent event) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5),p);
		translate.setToX(p.getLayoutX()+(root.getPrefWidth()-p.getPrefWidth()));
		translate.play();

		h1.setText("login :");
		h2.setText("tel :");
		setChosenUser(data.get(0));
		h3.setVisible(true);
		image = new Image(getClass().getResourceAsStream("/view/icons8_user_60px.png"));
		fruitImg.setImage(image);    	
		login.setStyle("-fx-background-color: rgb(34,177,76);");
		tel.setStyle("-fx-background-color: rgb(34,177,76);");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5),p);
		translate.setToX(p.getLayoutX()+(root.getPrefWidth()-p.getPrefWidth()));
		translate.play();

		try {
			data.addAll(getData());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (data.size() > 0) {
			setChosenUser(data.get(0));
			myListener = new MyListener() {
				@Override
				public void onClickListener(User user) {
					setChosenUser(user);
				}
			};
		}
		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < data.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/ItemView.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				ItemController1 itemController = fxmlLoader.getController();
				itemController.setData(data.get(i),myListener);

				if (column == 1) {
					column = 0;
					row++;
				}

				grid.add(anchorPane, column++, row); //(child,column,row)
				//set grid width
				grid.setMinWidth(Region.USE_COMPUTED_SIZE);
				grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
				grid.setMaxWidth(Region.USE_PREF_SIZE);

				//set grid height
				grid.setMinHeight(Region.USE_COMPUTED_SIZE);
				grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
				grid.setMaxHeight(Region.USE_PREF_SIZE);

				GridPane.setMargin(anchorPane, new Insets(10));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}