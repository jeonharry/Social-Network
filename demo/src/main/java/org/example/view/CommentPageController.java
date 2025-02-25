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
import model.OpenedPage;
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
        if(username_lbl.getText().compareTo("Deleted Account")!=0)
        {
            UserPageController.setUser(Database.getDatabase().getUser(username_lbl.getText()));
            Controller.getController().getUsersProfiles().push(Database.getDatabase().getUser(username_lbl.getText()));
            Controller.getController().getOpenedPages().push(OpenedPage.THROUGH_COMMENTS);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
            Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
        }
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
