package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private Label error;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField username;

    @FXML
    void login(MouseEvent event) {
        try
        {
            UserController.getUserController().login(username.getText(),password.getText());
            error.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
            Controller.getController().getRoot().getChildren().clear();
            Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
//            Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
        }catch (Exception exception)
        {
            error.setText(exception.getMessage());
            error.setVisible(true);
        }
    }

    @FXML
    void openSignupPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignupPage.fxml"));
        Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setVisible(false);
        Controller.getController().setRoot(root);
    }
}
