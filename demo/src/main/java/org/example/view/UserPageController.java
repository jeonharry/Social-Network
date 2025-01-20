package org.example.view;

import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.database.Database;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {

    @FXML
    private Circle crl_profile;

    @FXML
    private GridPane grid_posts;

    @FXML
    private ImageView img_menu;

    @FXML
    private Label lbl_bio;

    @FXML
    private Label lbl_connectionsNumber;

    @FXML
    private Label lbl_fullName;

    @FXML
    private Label lbl_postsNumber;

    @FXML
    private Label lbl_username;

    @FXML
    private AnchorPane root;

    @FXML
    void showConnections(MouseEvent event) {

    }

    @FXML
    void showMenu(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_username.setText(UserController.getUserController().getUser().getUsername());
        lbl_bio.setText(UserController.getUserController().getUser().getBio());
        lbl_postsNumber.setText(String.valueOf(UserController.getUserController().getUser().getPosts().size()));
        lbl_fullName.setText(UserController.getUserController().getUser().getFullName());
        lbl_connectionsNumber.setText(String.valueOf(Database.getDatabase().getConnections().values(UserController.getUserController().getUser().getUsername()).size()));
        ImagePattern profile = new ImagePattern(new Image(Objects.requireNonNull(Main.class.getResource(UserController.getUserController().getUser().getProfile())).toExternalForm()));
        crl_profile.setFill(profile);
    }
}
