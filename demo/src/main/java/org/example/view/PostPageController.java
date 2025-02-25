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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PostPageController implements Initializable {

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
    private ImageView delete_img;

    @FXML
    private ImageView edit_img;

    @FXML
    void back(MouseEvent event) throws IOException {
        back();
    }

    @FXML
    void like(MouseEvent event) {
        if(openedPost.isLike(UserController.getUserController().getUser().getUsername()))
        {
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_160734.png").toExternalForm()));
            openedPost.getLikes().remove(UserController.getUserController().getUser().getUsername());
            lbl_likeNumber.setText(String.valueOf(openedPost.getLikes().size()));
        }
        else
        {
            like_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_165246.png").toExternalForm()));
            openedPost.addLike(UserController.getUserController().getUser().getUsername());
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
        if(openedPost.isLike(UserController.getUserController().getUser().getUsername()))
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
        makeSure("deletePost");
    }

    public static void deletePost() throws IOException {
        openedPost.getOwner().getPosts().remove(openedPost);
        back();
    }

    private void makeSure(String req) throws IOException {
        MakeSureController.setReq(req);
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("MakeSure.fxml"));
        Controller.getController().getRoot().getChildren().addLast(loader.load());
    }

    @FXML
    void editPost(MouseEvent event) throws IOException {
        EditPostController.setPost(openedPost);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditPost.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
    }

    private static void back() throws IOException {
        UserPageController.setUser(Controller.getController().getUsersProfiles().peek());
        Controller.getController().getOpenedPosts().pop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
    }

    @FXML
    void deleteChangeColorEnter(MouseEvent event) {
        delete_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_230314.png").toExternalForm()));
    }

    @FXML
    void deleteChangeColorExit(MouseEvent event) {
        delete_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_21_230314.png").toExternalForm()));
    }

    @FXML
    void editChangeColorEnter(MouseEvent event) {
        edit_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_20250121_230343.png").toExternalForm()));
    }

    @FXML
    void editChangeColorExit(MouseEvent event) {
        edit_img.setImage(new Image(Main.class.getResource("pics/Clipped_image_611621_230343.png").toExternalForm()));
    }
}
