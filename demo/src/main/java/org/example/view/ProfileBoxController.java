package org.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileBoxController implements Initializable {

    @FXML
    private Label fullName_lbl;

    @FXML
    private Circle prof_img;

    @FXML
    private StackPane unfollow_btn;

    @FXML
    private Label username_lbl;

    private static User recentUser;

    private User user;

    @FXML
    void openProfile(MouseEvent event) {

    }

    @FXML
    void unfollow(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
