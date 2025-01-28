package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.OpenedPage;
import model.Post;
import model.User;
import model.database.Database;

import java.io.IOException;
import java.net.URL;
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
    private Label delete_lbl;

    @FXML
    private Label edit_lbl;

    @FXML
    private Label logout_lbl;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView noPost_img;

    @FXML
    private HBox notConnectInfo;

    @FXML
    private StackPane connect_btn;

    @FXML
    private VBox vBox_root;

    @FXML
    private Line line;

    @FXML
    void newPost(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("NewPost.fxml"));
        Controller.getController().getRoot().getChildren().addLast(loader.load());
    }

    @FXML
    void showConnections(MouseEvent event) throws IOException {
        if(user.getUsername().compareTo(UserController.getUserController().getUser().getUsername())==0 || Database.getDatabase().getConnections().values(UserController.getUserController().getUser().getUsername()).contains(user.getUsername()))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConnectionsPage.fxml"));
            Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
        }
    }

    @FXML
    void showMenu(MouseEvent event) {
        moreOptions.setVisible(!moreOptions.isVisible());
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        OpenedPage op=Controller.getController().getOpenedPages().pop();
        Controller.getController().getUsersProfiles().pop();
        if(op==OpenedPage.THROUGH_COMMENTS)
        {
            PostPageController.setOpenedPost(Controller.getController().getOpenedPosts().peek());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PostPage.fxml"));
            Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
            CommentsPageController.setPost(PostPageController.getOpenedPost());
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("CommentsPage.fxml"));
            Controller.getController().getRoot().getChildren().addLast(loader.load());
        }
        else
        {
            UserPageController.setUser(Controller.getController().getUsersProfiles().peek());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConnectionsPage.fxml"));
            Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
        }
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
        back_img.setVisible(!Controller.getController().getOpenedPages().isEmpty());
        int counter = 0;
        if(Database.getDatabase().getConnections().values(UserController.getUserController().getUser().getUsername()).contains(user.getUsername()) || user.getUsername().compareTo(UserController.getUserController().getUser().getUsername())==0)
        {
            vBox_root.getChildren().remove(notConnectInfo);
            vBox_root.getChildren().remove(connect_btn);
            scrollPane.setPrefViewportHeight(300.0);
            scrollPane.setMinViewportHeight(290.0);
            img_plus.setLayoutY(340.0);
            line.setLayoutY(330.0);
            if(user.getPosts().isEmpty())
            {
                noPost_img.setVisible(true);
                scrollPane.setVisible(false);
            }
            else
                scrollPane.setVisible(true);
            for(Post post : user.getPosts()){
                ImagePostController.setRecently(post);
                try {
                    grid_posts.add(new FXMLLoader(Main.class.getResource("ImagePost.fxml")).load(),counter%3, counter++/3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else
        {
            connect_btn.setVisible(true);
            connect_btn.setDisable(false);
            noPost_img.setVisible(false);
            scrollPane.setVisible(false);
            notConnectInfo.setVisible(true);
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
    void editProfile(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditUserPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Controller.getController().getRoot().getChildren().add(fxmlLoader.load());
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        showPage("logout");
    }
    public static void logout() throws IOException {
        UserController.getUserController().logout();
        Controller.getController().getUsersProfiles().pop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
    }

    private void showPage(String req) throws IOException {
        MakeSureController.setReq(req);
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("MakeSure.fxml"));
        Controller.getController().getRoot().getChildren().addLast(loader.load());
    }
    @FXML
    void deleteAccount(MouseEvent event) throws IOException {
        showPage("deleteAcc");
    }
    public static void deleteAccount() throws IOException {
        UserController.getUserController().delete();
        UserController.getUserController().logout();
        Controller.getController().getUsersProfiles().pop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Controller.getController().getRoot().getChildren().add(fxmlLoader.load());
    }
    @FXML
    void labelChangeColorEnter(MouseEvent event) {
        if(((Label)event.getTarget()).getText().compareTo("Edit Profile")==0)
            edit_lbl.setTextFill(Color.WHITE);
        else if(((Label)event.getTarget()).getText().compareTo("Delete Account")==0)
            delete_lbl.setTextFill(Paint.valueOf("#e70b0b"));
        else
            logout_lbl.setTextFill(Paint.valueOf("#e70b0b"));
    }

    @FXML
    void labelChangeColorExit(MouseEvent event) {
        if(((Label)event.getTarget()).getText().compareTo("Edit Profile")==0)
            edit_lbl.setTextFill(Paint.valueOf("#c4c4c4"));
        else if(((Label)event.getTarget()).getText().compareTo("Delete Account")==0)
            delete_lbl.setTextFill(Paint.valueOf("#b73b3b"));
        else
            logout_lbl.setTextFill(Paint.valueOf("#b73b3b"));
    }

    @FXML
    void connect(MouseEvent event) throws IOException {
        Database.getDatabase().getConnections().insert(user.getUsername(),UserController.getUserController().getUser().getUsername());
        UserController.getUserController().updateSuggestions();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
    }
}
