package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Post;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ImagePostController implements Initializable {
    private Post post;
    private static Post recently;

    public static Post getRecently() {
        return recently;
    }

    public static void setRecently(Post recently) {
        ImagePostController.recently = recently;
    }

    @FXML
    private ImageView img_post;

    @FXML
    void openPost(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.post = recently;
        img_post.setImage(new Image(post.getImage()));
    }
}
