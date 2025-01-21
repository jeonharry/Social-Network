package org.example.view;

import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Post;
import model.database.Database;

import java.io.IOException;
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
    private ImageView img_plus;

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
    void newPost(MouseEvent event) {

    }

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
        String pro = UserController.getUserController().getUser().getProfile();
        if(pro == null)
            pro = "account.png";
        ImagePattern profile = new ImagePattern(new Image(Objects.requireNonNull(Main.class.getResource(pro)).toExternalForm()));
        crl_profile.setFill(profile);
        img_plus.setImage(new Image(Objects.requireNonNull(Main.class.getResource("icons-plus.png")).toExternalForm()));
        grid_posts.getChildren().remove(0,0);
        int counter = 0;
        for(Post post : UserController.getUserController().getUser().getPosts()){
            ImagePostController.setRecently(post);
            try {
                grid_posts.add(new FXMLLoader(Main.class.getResource("ImagePost.fxml")).load(),counter%3, counter++/3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        grid_posts.add(img_plus,counter%3, counter/3);
    }
}
