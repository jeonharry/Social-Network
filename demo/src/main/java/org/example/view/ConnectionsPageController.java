package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionsPageController implements Initializable {

    @FXML
    private ImageView back_img;

    @FXML
    private Label connectionNum_lbl;

    @FXML
    private GridPane connections;

    @FXML
    private AnchorPane root;

    @FXML
    private Label username_lbl;

    @FXML
    void back(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
