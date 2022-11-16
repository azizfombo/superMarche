package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MyListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.User;

public class ItemController1 implements Initializable {

	
	@FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(user);
    }

    private User user;
    private MyListener myListener;

    public void setData(User user, MyListener myListener) {
        this.user = user;
        this.myListener = myListener;
        nameLabel.setText(user.getNomUser());
        priceLable.setText("N." + user.getId());
        Image image = new Image(getClass().getResourceAsStream("/view/icons8_user_60px.png"));
        img.setImage(image);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
