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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Comment;
import model.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentPageController implements Initializable {

    @FXML
    private Label comment_lbl;

    @FXML
    private Circle prof_img;

    @FXML
    private Label username_lbl;

    private static Comment comment;

    @FXML
    void openProfile(MouseEvent event) throws IOException {
        if(Database.getDatabase().getConnections().values(UserController.getUserController().getUser().getUsername()).contains(username_lbl.getText()))
        {
            UserPageController.setUser(Database.getDatabase().getUser(username_lbl.getText()));
            Controller.getController().getUsersProfiles().push(Database.getDatabase().getUser(username_lbl.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
            Main.getStage().setScene(new Scene(fxmlLoader.load(),750,650));
        }
    }

    public static Comment getComment() {
        return comment;
    }

    public static void setComment(Comment comment) {
        CommentPageController.comment = comment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lbl.setText(comment.getSender().getUsername());
        comment_lbl.setText(comment.getMessage());
        ImagePattern imagePattern=new ImagePattern(new Image(comment.getSender().getProfile()));
        prof_img.setFill(imagePattern);
    }
}
