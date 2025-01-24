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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.User;
import model.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UnfollowAskingController implements Initializable {

    @FXML
    private Circle profile_crl;

    @FXML
    private AnchorPane root;

    @FXML
    private Label username_lbl;

    @FXML
    private Label cancel_lbl;

    @FXML
    private Label disconnect_lbl;

    private static User user;

    @FXML
    void cancel(MouseEvent event) throws IOException {
        event.consume();
        Controller.getController().getRoot().getChildren().removeLast();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConnectionsPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
    }

    @FXML
    void disConnect(MouseEvent event) throws IOException {
        event.consume();
        Database.getDatabase().getConnections().remove(user.getUsername(), UserController.getUserController().getUser().getUsername());
        UserController.getUserController().updateSuggestions();
        Controller.getController().getRoot().getChildren().removeLast();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ConnectionsPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lbl.setText(user.getUsername());
        ImagePattern imagePattern=new ImagePattern(new Image(user.getProfile()));
        profile_crl.setFill(imagePattern);
    }

    @FXML
    void changeColorEntered(MouseEvent event) {
        if(((Label)event.getTarget()).getText().compareTo("Cancel")==0)
            cancel_lbl.setTextFill(Color.WHITE);
        else
            disconnect_lbl.setTextFill(Color.RED);
    }

    @FXML
    void changeColorExit(MouseEvent event) {
        if(((Label)event.getTarget()).getText().compareTo("Cancel")==0)
            cancel_lbl.setTextFill(Paint.valueOf("#bfbfbf"));
        else
            disconnect_lbl.setTextFill(Paint.valueOf("#b71010"));
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UnfollowAskingController.user = user;
    }
}
