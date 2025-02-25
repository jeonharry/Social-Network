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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewPostController implements Initializable {

    @FXML
    private ImageView img_post;

    @FXML
    private TextArea txt_caption;

    private File file;

    @FXML
    private Label post_lbl;

    @FXML
    void back(MouseEvent event) throws IOException {
        back();
    }

    @FXML
    void post(MouseEvent event) throws IOException {
        if(file != null){
            UserController.getUserController().createNewPost(file.toURI().toString(),txt_caption.getText());
            back();
        }
    }

    @FXML
    void selectPhoto(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            img_post.setImage(new Image(file.toURI().toString()));
            if(txt_caption.getText().compareTo("")!=0)
                post_lbl.setTextFill(Color.WHITE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file = null;
        img_post.setImage(new Image(Objects.requireNonNull(Main.class.getResource("pics/photo_15627136.png")).toExternalForm()));

    }

    private void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Main.getStage().setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void typed(KeyEvent event) {
        if(file!=null)
            post_lbl.setTextFill(Color.WHITE);
    }
}
