package controller;

import application.MyListener1;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Produit;

public class ItemStockController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(p);
    }

    private Produit p;
    private MyListener1 myListener;

    public void setData(Produit p, MyListener1 myListener) {
        this.p = p;
        this.myListener = myListener;
        nameLabel.setText("" + p.getIdProduits());
        priceLable.setText(p.getPrix() + "frs");
        Image image = new Image(getClass().getResourceAsStream(p.getPhoto()));
        img.setImage(image);
    }
}
