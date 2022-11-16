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
import application.MyListener1;
import connexion.ConnexionDB;
import dao.GestionProduitsDAO;
import dao.GestionUserDAO;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Produit;
import model.User;

public class AccueilController implements Initializable {

	double xOffset;
	double yOffset;
	int a=1;
	
	@FXML
    private Label h8;
	
	 @FXML
	 private Label codeId;

    @FXML
    private PasswordField pass;
	
	@FXML
    private BorderPane bPane;

    @FXML
    private ImageView exit;

    @FXML
    private Label user;

    @FXML
    private Label poste;

    @FXML
    private HBox hb1;

    @FXML
    private Label m1;

    @FXML
    private HBox hb2;

    @FXML
    private Label m2;

    @FXML
    private HBox hb3;

    @FXML
    private Label m3;

    @FXML
    private HBox hb4;

    @FXML
    private Label m4;

    @FXML
    private HBox hb5;

    @FXML
    private Label m5;

    @FXML
    private Label nbPagne;

    @FXML
    private Label nbBracelet;

    @FXML
    private Label nbChassure;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane root;
    
    @FXML
    private AnchorPane root2;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

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
    private TextField login;

    @FXML
    private TextField tel;

    @FXML
    private HBox h3;
    
    @FXML
    private Label h1;
    
    @FXML
    private Label h2;
    
    @FXML
    private TextField c1;

    @FXML
    private TextField c2;

    @FXML
    private PasswordField c3;

    @FXML
    private TextField c4;

    @FXML
    private TextField c5;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private VBox chosenFruitCard1;

    @FXML
    private Label fruitNameLable1;

    @FXML
    private TextField fruitPriceLabel1;

    @FXML
    private ImageView fruitImg1;

    @FXML
    private ScrollPane scroll1;

    @FXML
    private GridPane grid1;
    
    @FXML
    private Label qteMod;
    
    @FXML
    private HBox cat1;

    @FXML
    private HBox cat2;

    @FXML
    private HBox cat3;
    

    @FXML
    private Label vide;
    
    private ObservableList<Produit> produits = FXCollections.observableArrayList();
    private Image image1;
    private MyListener1 myListener1;

	private int x;
	private int y;
	private int z;
	private int cat;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			nbrePagne();
			nbreBracelet();
			nbreChaussure();
			infos();
			categorie1(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		iniLineChart();
		iniPieChart();
		
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
	
//----------------------chargement des données enregistrées dans la base de données-----------------------------------------//
	
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
	
	private ObservableList<Produit> getDataP() throws SQLException {
        ObservableList<Produit> produits = FXCollections.observableArrayList();
        Produit prod = new Produit();
		Connection con = ConnexionDB.connect();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = null;
		try {
			this.produits.clear();
			query = "SELECT * FROM produit WHERE Type='Pagne'";
			p = con.prepareStatement(query);
			rs = p.executeQuery();
			prod.setA(0);
			while (rs.next()){
				produits.add(new Produit(
					rs.getInt("idProduits"),
					rs.getInt("quantite"),
					rs.getString("couleur"),
					rs.getString("Type"),
					rs.getInt("Prix"),
					rs.getInt("taille"),
					rs.getString("photo")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		} 
        return produits;
    }
	
	private ObservableList<Produit> getDataB() throws SQLException {
        ObservableList<Produit> produits = FXCollections.observableArrayList();
        Produit prod = new Produit();
		Connection con = ConnexionDB.connect();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = null;
		try {
			this.produits.clear();
			query = "SELECT * FROM produit WHERE Type='Bracelet'";
			p = con.prepareStatement(query);
			rs = p.executeQuery();
			prod.setA(0);
			while (rs.next()){
				produits.add(new Produit(
					rs.getInt("idProduits"),
					rs.getInt("quantite"),
					rs.getString("couleur"),
					rs.getString("Type"),
					rs.getInt("Prix"),
					rs.getInt("taille"),
					rs.getString("photo")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		} 
        return produits;
    }
	
	private ObservableList<Produit> getDataC() throws SQLException {
        ObservableList<Produit> produits = FXCollections.observableArrayList();
        Produit prod = new Produit();
		Connection con = ConnexionDB.connect();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = null;
		try {
			this.produits.clear();
			query = "SELECT * FROM produit WHERE Type='Chaussure'";
			p = con.prepareStatement(query);
			rs = p.executeQuery();
			prod.setA(0);
			while (rs.next()){
				produits.add(new Produit(
					rs.getInt("idProduits"),
					rs.getInt("quantite"),
					rs.getString("couleur"),
					rs.getString("Type"),
					rs.getInt("Prix"),
					rs.getInt("taille"),
					rs.getString("photo")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		} 
        return produits;
    }
	
//--------------------------------------------Interaction d'ajout, affichage...-----------------------------//
	private void setChosenUser(User user) {
		fruitNameLable.setText(user.getNomUser());
		fruitPriceLabel.setText("N." + user.getId());
		login.setText(user.getLogin());
		tel.setText("+237 " + user.getTelUser());
		pass.setText(user.getPassword());
		image = new Image(getClass().getResourceAsStream("/view/icons8_user_60px.png"));
		fruitImg.setImage(image);
	}
	
	private void setChosenFruit(Produit prod) {
        fruitNameLable1.setText(prod.getType());
        fruitPriceLabel1.setText("" + prod.getPrix());
        codeId.setText("" + prod.getIdProduits());
        qteMod.setText("" + prod.getQuantite());
        image1 = new Image(getClass().getResourceAsStream(prod.getPhoto()));
        fruitImg1.setImage(image1);
        chosenFruitCard1.setStyle("-fx-background-color: " + prod.getCouleur() + ";\n" +
                "    -fx-background-radius: 30;");
    }
	
	@FXML
    void ajouterUser(MouseEvent event) {
		try {
			new GestionUserDAO().saveUser(new User(c2.getText(),c3.getText(),c1.getText(),Integer.parseInt(c5.getText()),c4.getText()));
			data.clear();
			initialize(null,null);
			right(null);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
    }

	@FXML
	void supprimerUser(MouseEvent event) {
		try {
			
			new GestionUserDAO().DeleteUser(login.getText());
			grid.getChildren().clear();
			data.clear();
			initialize(null,null);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void modidierUser(MouseEvent event) {
		try {
			
			new GestionUserDAO().UpdateUser(login.getText(), pass.getText(), Integer.parseInt(tel.getText(5, tel.getText().length())), fruitNameLable.getText());
			grid.getChildren().clear();
			data.clear();
			initialize(null,null);
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
    }
	
    @FXML
    void delProd(ActionEvent event) {
    	try {
			new GestionProduitsDAO().DeleteProduits(Integer.parseInt(codeId.getText()));
			grid1.getChildren().clear();
			produits.clear();
			if(cat==1) {
				initStock(getDataP());
				}
				else if(cat==2) {
					initStock(getDataB());
				}
				else {
					initStock(getDataC());
				}
			nbrePagne();
			nbreBracelet();
			nbreChaussure();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void categorie1(MouseEvent event) throws SQLException {
    	cat2.getStyleClass().clear();
		cat2.getStyleClass().add("free-delivery-card");
		cat3.getStyleClass().clear();
		cat3.getStyleClass().add("free-delivery-card");
		cat1.getStyleClass().clear();
		cat1.getStyleClass().add("shopping-online-card");
    	grid1.getChildren().clear();
    	initStock(getDataP());
    	cat=1;
    }

    @FXML
    void categorie2(MouseEvent event) throws SQLException {
		cat1.getStyleClass().clear();
		cat1.getStyleClass().add("free-delivery-card");
		cat3.getStyleClass().clear();
		cat3.getStyleClass().add("free-delivery-card");
		cat2.getStyleClass().clear();
		cat2.getStyleClass().add("shopping-online-card");
    	grid1.getChildren().clear();
    	initStock(getDataB());
    	cat=2;
    }

    @FXML
    void categorie3(MouseEvent event) throws SQLException {
    	
    	cat1.getStyleClass().clear();
		cat1.getStyleClass().add("free-delivery-card");
		cat2.getStyleClass().clear();
		cat2.getStyleClass().add("free-delivery-card");
		cat3.getStyleClass().clear();
		cat3.getStyleClass().add("shopping-online-card");
    	grid1.getChildren().clear();
    	initStock(getDataC());
    	cat=3;
    }
    
    @FXML
    void saveProd(ActionEvent event) throws IOException {
    	try {
			new GestionProduitsDAO().UpdateProduits(Integer.parseInt(fruitPriceLabel1.getText()),Integer.parseInt(qteMod.getText()),Integer.parseInt(codeId.getText()));
			grid1.getChildren().clear();
			produits.clear();
			if(cat==1 && searchBar.getText().equals("")) {
			categorie1(null);
			}
			else if(cat==2 && searchBar.getText().equals("")) {
				categorie2(null);
			}
			else if(cat==3 && searchBar.getText().equals("")){
				categorie3(null);
			}
			if(searchBar.getText()!="") {
				search(null);
			}
			nbrePagne();
			nbreBracelet();
			nbreChaussure();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void ajouterProd(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);
		AnchorPane root = FXMLLoader.load(getClass().getResource("/view/SettingsView.fxml"));  				
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
    }
    
    @FXML
    void search(ActionEvent event) throws SQLException, IOException {
    	if(searchBar.getText().charAt(0) == 'B') {
    		if(verify("Bracelet")) {
    		categorie2(null);
    		grid1.getChildren().clear();
    		fouille();
    		}
    		else {
    			categorie2(null);
        		grid1.getChildren().clear();
    			chosenFruitCard1.setVisible(false);
            	vide.setVisible(true);
    		}
    	}
    	if(searchBar.getText().charAt(0) == 'P') {
    		if(verify("Pagne")) {
    		categorie1(null);
    		grid1.getChildren().clear();
    		fouille();
    		}
    		else {
    			categorie1(null);
        		grid1.getChildren().clear();
    			chosenFruitCard1.setVisible(false);
            	vide.setVisible(true);
    		}
    	}
    	if(searchBar.getText().charAt(0) == 'C') {
    		if(verify("Chaussure")) {
    		categorie3(null);
    		grid1.getChildren().clear();
    		fouille();
    		}
    		else {
    			categorie3(null);
        		grid1.getChildren().clear();
    			chosenFruitCard1.setVisible(false);
            	vide.setVisible(true);
    		}
    	}
    }


    private void fouille() throws NumberFormatException, SQLException, IOException {
    	chosenFruitCard1.setVisible(true);
    	vide.setVisible(false);
        setChosenFruit(new GestionProduitsDAO().RechercheProduits(Integer.parseInt(searchBar.getText(1, searchBar.getText().length()))));
        myListener1 = new MyListener1() {
            @Override
            public void onClickListener(Produit prod) {
                setChosenFruit(prod);
            }
        };
        
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Item.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        ItemStockController itemController1 = fxmlLoader.getController();
        itemController1.setData(new GestionProduitsDAO().RechercheProduits(Integer.parseInt(searchBar.getText(1, searchBar.getText().length()))),myListener1);
		
		grid1.add(anchorPane, 1, 2); //(child,column,row)
        //set grid width
        grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid1.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid1.setMaxHeight(Region.USE_PREF_SIZE);

        GridPane.setMargin(anchorPane, new Insets(10));
    }
    
    private boolean verify(String str) throws SQLException {
    	boolean b=true;
    	boolean t=false;
		Connection con = ConnexionDB.connect();
		PreparedStatement p = null;
		ResultSet rs = null;
		String query = null;
		try {
			this.produits.clear();
			query = "SELECT * FROM produit WHERE Type=?";
			p = con.prepareStatement(query);
			p.setString(1,str);
			rs = p.executeQuery();
			while (rs.next()){
				if(Integer.parseInt(searchBar.getText(1, searchBar.getText().length()))==rs.getInt("idProduits")) {
					b=true;
					t=b;
				}
				else {
					b=false;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
		} 
		return t;
    }
    @FXML
    void qtePlus(ActionEvent event) {
    	int q=Integer.parseInt(qteMod.getText());
    	qteMod.setText(""+(q+1));
    }
    
    @FXML
    void qteMoins(ActionEvent event) {
    	int q=Integer.parseInt(qteMod.getText());
    	if(q>0) {
    	   qteMod.setText(""+(q-1));
    	}
    }
    
//--------------------------------------------Animation------------------------------------------------------------------//
	@FXML
	void left(ActionEvent event) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5),p);
		translate.setToX(root.getParent().getLayoutX()-20);
		translate.play();

		fruitNameLable.setText("");
		fruitPriceLabel.setText("");
		h1.setText("");
		h2.setText("");
		h8.setText("");
		h3.setVisible(false);
		login.setText("");
		tel.setText("");
		pass.setText("");
		image = new Image(getClass().getResourceAsStream("/view/icons8_add_user_male_52px.png"));
		fruitImg.setImage(image);    	
		login.setStyle("-fx-background-color: transparent;");
		tel.setStyle("-fx-background-color: transparent;");
		pass.setStyle("-fx-background-color: transparent;");
	}
	
	@FXML
	void right(ActionEvent event) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(0.5),p);
		translate.setToX(p.getLayoutX()+(root.getPrefWidth()-p.getPrefWidth()));
		translate.play();
		
		c1.clear();
		c2.clear();
		c3.clear();
		c4.clear();
		c5.clear();

		h1.setText("login :");
		h2.setText("tel :");
		h8.setText("password :");
		setChosenUser(data.get(0));
		h3.setVisible(true);
		image = new Image(getClass().getResourceAsStream("/view/icons8_user_60px.png"));
		fruitImg.setImage(image);    	
		login.setStyle("-fx-background-color: rgb(34,177,76);");
		tel.setStyle("-fx-background-color: rgb(34,177,76);");
		pass.setStyle("-fx-background-color: rgb(34,177,76);");
	}
//-------------------------------------------------remplissage pieChart et lineChart--------------------------------------------------//
	private void iniLineChart() {
		XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data("Lundi",65));
		series.getData().add(new XYChart.Data("Mardi",15));
		series.getData().add(new XYChart.Data("Mercredi",20));
		series.getData().add(new XYChart.Data("Jeudi",6));
		series.getData().add(new XYChart.Data("Vendredi",8));
		series.getData().add(new XYChart.Data("Samedi",46));
		series.getData().add(new XYChart.Data("Dimanche",23));
		lineChart.getData().addAll(series);
		lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
		series.getNode().setStyle("-fx-stroke: black");
	}

	private void iniPieChart() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Pagnes", 15),
				new PieChart.Data("Bracelets", 10),
				new PieChart.Data("Chaussures", 30));
		pieChart.setData(pieChartData);			
	}
	
//--------------------------------------------------Navigation------------------------------------------------------------------//
	@FXML
	void disconnected(MouseEvent event) throws SQLException {
		Connection conn = ConnexionDB.connect();
		PreparedStatement stat=null;
		ResultSet rs=null;
		String sql1 = "UPDATE user SET Statut = 0 WHERE Statut=1";
		try {
			stat = conn.prepareStatement(sql1);
			stat.executeUpdate();

			Stage stage = new Stage();
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			exit.getScene().getWindow().hide();
			stage.show();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	void lancerUser(MouseEvent event) throws IOException {
		
		hb4.getStyleClass().clear();
		hb4.getStyleClass().add("hbbgcolor");
		m4.getStyleClass().clear();
		m4.getStyleClass().add("shadow2");
		
		switch(a){
		case 1:
			hb1.getStyleClass().clear();
			m1.getStyleClass().clear();
			m1.getStyleClass().add("shadow");
			bPane.getCenter().setVisible(false);
		case 2:
			hb2.getStyleClass().clear();
			m2.getStyleClass().clear();
			m2.getStyleClass().add("shadow");
		case 3:
			hb3.getStyleClass().clear();
			m3.getStyleClass().clear();
			m3.getStyleClass().add("shadow");
			root2.setVisible(false);
		case 5:
			hb5.getStyleClass().clear();
			m5.getStyleClass().clear();
			m5.getStyleClass().add("shadow");
			
		}
		if(a!=4) {
		  right(null);
		  root.setVisible(true);
		  a=4;
		}
	}
	
	@FXML
    void lancerStock(MouseEvent event) {
		hb3.getStyleClass().clear();
		hb3.getStyleClass().add("hbbgcolor");
		m3.getStyleClass().clear();
		m3.getStyleClass().add("shadow2");
		
		switch(a){
		case 1:
			hb1.getStyleClass().clear();
			m1.getStyleClass().clear();
			m1.getStyleClass().add("shadow");
			bPane.getCenter().setVisible(false);
		case 2:
			hb2.getStyleClass().clear();
			m2.getStyleClass().clear();
			m2.getStyleClass().add("shadow");
		case 4:
			hb4.getStyleClass().clear();
			m4.getStyleClass().clear();
			m4.getStyleClass().add("shadow");
			root.setVisible(false);
		case 5:
			hb5.getStyleClass().clear();
			m5.getStyleClass().clear();
			m5.getStyleClass().add("shadow");
		}

		if(a!=3) {
		  root2.setVisible(true);
		  a=3;
		}
    }
	
	@FXML
	void lancerAccueil(MouseEvent event) throws IOException {
		
		hb1.getStyleClass().clear();
		hb1.getStyleClass().add("hbbgcolor");
		m1.getStyleClass().clear();
		m1.getStyleClass().add("shadow2");
		
		switch(a){
		case 2:
			hb2.getStyleClass().clear();
			m2.getStyleClass().clear();
			m2.getStyleClass().add("shadow");
		case 3:
			hb3.getStyleClass().clear();
			m3.getStyleClass().clear();
			m3.getStyleClass().add("shadow");
			root2.setVisible(false);
		case 4:
			hb4.getStyleClass().clear();
			m4.getStyleClass().clear();
			m4.getStyleClass().add("shadow");
			root.setVisible(false);
		case 5:
			hb5.getStyleClass().clear();
			m5.getStyleClass().clear();
			m5.getStyleClass().add("shadow");
		}

		if(a!=1) {
		  bPane.getCenter().setVisible(true);
		  a=1;
		}
	}
	
	private void initStock(ObservableList<Produit> listep) throws SQLException {
		produits.addAll(listep);
        if (produits.size() > 0) {
        	chosenFruitCard1.setVisible(true);
        	vide.setVisible(false);
            setChosenFruit(produits.get(0));
            myListener1 = new MyListener1() {
                @Override
                public void onClickListener(Produit prod) {
                    setChosenFruit(prod);
                }
            };
        }
        else {
        	chosenFruitCard1.setVisible(false);
        	vide.setVisible(true);
        }
        int columns = 0;
        int rows = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemStockController itemController1 = fxmlLoader.getController();
                itemController1.setData(produits.get(i),myListener1);

                if (columns == 2) {
                    columns = 0;
                    rows++;
                }

                grid1.add(anchorPane, columns++, rows); //(child,column,row)
                //set grid width
                grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid1.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid1.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//------------------------------nombre d'articles et info de l'utilisateur courant a l'accueil-------------------------------------------------------------//
	private void nbrePagne() throws SQLException{
		Connection conn = ConnexionDB.connect();
		x=0;
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produit WHERE Type='Pagne'";
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				x = x + rs.getInt("quantite");
			}
			nbPagne.setText(""+x);
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void nbreBracelet() throws SQLException{
		y=0;
		Connection conn = ConnexionDB.connect();
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produit WHERE Type='Bracelet'";
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				y = y + rs.getInt("quantite");
			}
			nbBracelet.setText(""+y);
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void nbreChaussure() throws SQLException{
		z=0;
		Connection conn = ConnexionDB.connect();
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produit WHERE Type='Chaussure'";
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while(rs.next()) {
				z = z + rs.getInt("quantite");
			}
			nbChassure.setText(""+z);
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void infos() throws SQLException {
		Connection conn = ConnexionDB.connect();
		PreparedStatement stat=null;
		ResultSet rs=null;
		String sql="SELECT NomUser, poste FROM user WHERE Statut=1";
		try {    	   
			stat=conn.prepareStatement(sql);
			rs=stat.executeQuery();
			while(rs.next()) {   
				user.setText(rs.getString("NomUser"));
				poste.setText(rs.getString("poste"));
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void settings(MouseEvent event) {

	}
}