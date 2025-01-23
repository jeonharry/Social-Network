package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Post;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditPostController implements Initializable {

    @FXML
    private ImageView img_post;

    @FXML
    private TextArea txt_caption;

    @FXML
    private VBox vbox;

    private File file;
    private static Post post;

    @FXML
    void back(MouseEvent event) throws IOException {
        back();
    }

    @FXML
    void save(MouseEvent event) throws IOException {
        post.setCaption(txt_caption.getText());
        if(file != null){
            post.setImage(file.toURI().toString());
        }
        back();
    }

    @FXML
    void selectPhoto(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);
        if(file != null)
            img_post.setImage(new Image(file.toURI().toString()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file=null;
        img_post.setImage(new Image(post.getImage()));
        txt_caption.setText(post.getCaption());
    }

    public static Post getPost() {
        return post;
    }

    public static void setPost(Post post) {
        EditPostController.post = post;
    }
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PostPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),460,680));
    }
}
