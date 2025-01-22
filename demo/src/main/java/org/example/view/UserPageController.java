package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Post;
import model.User;
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
    private ImageView back_img;

    private static User user;

    @FXML
    private VBox moreOptions;

    @FXML
    void newPost(MouseEvent event) {

    }

    @FXML
    void showConnections(MouseEvent event) {

    }

    @FXML
    void showMenu(MouseEvent event) {
        moreOptions.setVisible(!moreOptions.isVisible());
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        Controller.getController().getUsersProfiles().pop();
        PostPageController.setOpenedPost(Controller.getController().getOpenedPosts().peek());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PostPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
        CommentsPageController.setPost(PostPageController.getOpenedPost());
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("CommentsPage.fxml"));
        Controller.getController().getRoot().getChildren().addLast(loader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setRoot(root);
        lbl_username.setText(user.getUsername());
        lbl_bio.setText(user.getBio());
        lbl_postsNumber.setText(String.valueOf(user.getPosts().size()));
        lbl_fullName.setText(user.getFullName());
        lbl_connectionsNumber.setText(String.valueOf(Database.getDatabase().getConnections().values(user.getUsername()).size()));
        ImagePattern profile = new ImagePattern(new Image(user.getProfile()));
        crl_profile.setFill(profile);
        back_img.setVisible(user.getUsername().compareTo(UserController.getUserController().getUser().getUsername())!=0);
        int counter = 0;
        for(Post post : user.getPosts()){
            ImagePostController.setRecently(post);
            try {
                grid_posts.add(new FXMLLoader(Main.class.getResource("ImagePost.fxml")).load(),counter%3, counter++/3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        moreOptions.setVisible(false);
        if(user.getUsername().compareTo(UserController.getUserController().getUser().getUsername())!=0)
            img_menu.setVisible(false);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserPageController.user = user;
    }

    @FXML
    void editProfile(MouseEvent event) {

    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        logout();
    }

    public void logout() throws IOException {
        UserController.getUserController().logout();
        Controller.getController().getUsersProfiles().pop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Controller.getController().getRoot().getChildren().add(fxmlLoader.load());
    }
    @FXML
    void deleteAccount(MouseEvent event) throws IOException {
        UserController.getUserController().delete();
        logout();
    }
}
