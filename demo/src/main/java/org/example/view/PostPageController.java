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
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Post;

import java.io.IOException;
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
    private VBox moreOptions;

    @FXML
    private ImageView menu_img;

    @FXML
    void back(MouseEvent event) throws IOException {
        back();
    }

    @FXML
    void like(MouseEvent event) {
        if(openedPost.getLikes().contains(UserController.getUserController().getUser().getUsername()))
        {
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_160734.png").toExternalForm()));
            openedPost.getLikes().remove(UserController.getUserController().getUser().getUsername());
            lbl_likeNumber.setText(String.valueOf(openedPost.getLikes().size()));
        }
        else
        {
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_165246.png").toExternalForm()));
            openedPost.getLikes().add(UserController.getUserController().getUser().getUsername());
            lbl_likeNumber.setText(String.valueOf(openedPost.getLikes().size()));
        }
    }

    @FXML
    void openComments(MouseEvent event) throws IOException {
        CommentsPageController.setPost(openedPost);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CommentsPage.fxml"));
        Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setRoot(root);
        Controller.getController().setCommentsCount(lbl_commentNumber);
        username_lbl.setText(openedPost.getOwner().getUsername());
        lbl_caption.setText(openedPost.getCaption());
        lbl_likeNumber.setText(String.valueOf(openedPost.getLikes().size()));
        lbl_commentNumber.setText(String.valueOf(openedPost.getComments().size()));
        ImagePattern imagePattern=new ImagePattern(new Image(openedPost.getOwner().getProfile()));
        prof_img.setFill(imagePattern);
        img_post.setImage(new Image(openedPost.getImage()));
        if(openedPost.getLikes().contains(UserController.getUserController().getUser().getUsername()))
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_165246.png").toExternalForm()));
        if(username_lbl.getText().compareTo(UserController.getUserController().getUser().getUsername())!=0)
            menu_img.setVisible(false);
    }

    public static Post getOpenedPost() {
        return openedPost;
    }

    public static void setOpenedPost(Post openedPost) {
        PostPageController.openedPost = openedPost;
    }

    @FXML
    void openMoreOptions(MouseEvent event) {
        moreOptions.setVisible(!moreOptions.isVisible());
    }

    @FXML
    void deletePost(MouseEvent event) throws IOException {
        openedPost.getOwner().getPosts().remove(openedPost);
        back();
    }

    @FXML
    void editPost(MouseEvent event) {

    }

    private void back() throws IOException {
        UserPageController.setUser(Controller.getController().getUsersProfiles().peek());
        Controller.getController().getOpenedPosts().pop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
    }
}
