package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PostPageController {

    @FXML
    private ImageView img_like;

    @FXML
    private ImageView img_menu;

    @FXML
    private ImageView img_post;

    @FXML
    private ImageView img_send;

    @FXML
    private Label lbl_caption;

    @FXML
    private Label lbl_likeNumber;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtField_comment;

    @FXML
    private VBox vBox_comments;

    @FXML
    void openMenu(MouseEvent event) {

    }

    @FXML
    void sendComment(MouseEvent event) {

    }

}
