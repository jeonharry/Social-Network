package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class PostPageController implements Initializable {

    @FXML
    private ImageView back_img;

    @FXML
    private ImageView img_post;

    @FXML
    private Label lbl_caption;

    @FXML
    private Label lbl_commentNumber;

    @FXML
    private Label lbl_likeNumber;

    @FXML
    private Circle prof_img;

    @FXML
    private AnchorPane root;

    @FXML
    private Label username_lbl;

    @FXML
    private ImageView like_img;

    private static Post openedPost;

    @FXML
    void back(MouseEvent event) {
        Controller.getController().getRoot().getChildren().removeLast();
    }

    @FXML
    void like(MouseEvent event) {
        if(openedPost.getLikes().contains(UserController.getUserController().getUser().getUsername()))
        {
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_160734.png").toExternalForm()));
            openedPost.getLikes().remove(UserController.getUserController().getUser().getUsername());
        }
        else
        {
            like_img.setImage(new Image(Main.class.getResource("pics/").toExternalForm()));
            openedPost.getLikes().add(UserController.getUserController().getUser().getUsername());
        }
    }

    @FXML
    void openComments(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lbl.setText(openedPost.getOwner().getUsername());
        lbl_caption.setText(openedPost.getCaption());
        lbl_likeNumber.setText(String.valueOf(openedPost.getLikes().size()));
        lbl_commentNumber.setText(String.valueOf(openedPost.getComments().size()));
        ImagePattern imagePattern=new ImagePattern(new Image(openedPost.getImage()));
        prof_img.setFill(imagePattern);
        if(openedPost.getLikes().contains(UserController.getUserController().getUser().getUsername()))
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_165246.png").toExternalForm()));
    }

    public static Post getOpenedPost() {
        return openedPost;
    }

    public static void setOpenedPost(Post openedPost) {
        PostPageController.openedPost = openedPost;
    }
}
