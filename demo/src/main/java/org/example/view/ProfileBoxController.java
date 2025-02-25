package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.OpenedPage;
import model.User;
import model.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileBoxController implements Initializable {

    @FXML
    private Label fullName_lbl;

    @FXML
    private Circle prof_img;

    @FXML
    private StackPane unfollow_btn;

    @FXML
    private Label username_lbl;

    private static User recentUser;

    private User user;

    @FXML
    private StackPane connect_btn;

    @FXML
    void openProfile(MouseEvent event) throws IOException {
        UserPageController.setUser(Database.getDatabase().getUser(username_lbl.getText()));
        Controller.getController().getUsersProfiles().push(Database.getDatabase().getUser(username_lbl.getText()));
        Controller.getController().getOpenedPages().push(OpenedPage.THROUGH_CONNECTIONS);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
    }

    @FXML
    void unfollow(MouseEvent event) throws IOException {
        event.consume();
        if(UserController.getUserController().getSuggestions().contains(user))
        {
            UserController.getUserController().getSuggestions().remove(user);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConnectionsPage.fxml"));
            Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
        }
        else
        {
            UnfollowAskingController.setUser(Database.getDatabase().getUser(username_lbl.getText()));
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("UnfollowAsking.fxml"));
            Controller.getController().getRoot().getChildren().addLast(loader.load());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = getRecentUser();
        username_lbl.setText(user.getUsername());
        fullName_lbl.setText(user.getFullName());
        ImagePattern imagePattern=new ImagePattern(new Image(user.getProfile()));
        prof_img.setFill(imagePattern);
        if(UserController.getUserController().getSuggestions().contains(user) && !Controller.getController().isInConnections())
            connect_btn.setVisible(true);
        else if(UserController.getUserController().getUser().getUsername().compareTo(Controller.getController().getUsersProfiles().peek().getUsername())!=0)
        {
            unfollow_btn.setVisible(false);
            unfollow_btn.setDisable(true);
        }
    }

    @FXML
    void connect(MouseEvent event) throws IOException {
        event.consume();
        Database.getDatabase().getConnections().insert(user.getUsername(),UserController.getUserController().getUser().getUsername());
        UserController.getUserController().updateSuggestions();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConnectionsPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
    }

    public static User getRecentUser() {
        return recentUser;
    }

    public static void setRecentUser(User recentUser) {
        ProfileBoxController.recentUser = recentUser;
    }
}
