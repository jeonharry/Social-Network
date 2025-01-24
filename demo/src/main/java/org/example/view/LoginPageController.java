package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import model.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {


    @FXML
    private ImageView showPassword;

    @FXML
    private PasswordField passwordHiding;

    @FXML
    private TextField passwordShowing;

    @FXML
    private Label loginLabel;

    @FXML
    private Label error;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField username;
    private boolean usernameEntered=false;
    private boolean passwordEntered=false;


    @FXML
    void changeColor1(KeyEvent event) {
        usernameEntered=true;
        if(passwordEntered)
            loginLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    void changeColor2(KeyEvent event) {
        passwordEntered=true;
        if(usernameEntered)
            loginLabel.setTextFill(Paint.valueOf("white"));
    }

    @FXML
    void login(MouseEvent event) throws IOException {
        if(usernameEntered && passwordEntered)
        {
            try
            {
                if(passwordHiding.isVisible())
                    UserController.getUserController().login(username.getText(),passwordHiding.getText());
                else
                    UserController.getUserController().login(username.getText(),passwordShowing.getText());
                error.setVisible(false);
                UserPageController.setUser(UserController.getUserController().getUser());
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
                Controller.getController().getRoot().getChildren().clear();
                Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
                Controller.getController().getUsersProfiles().push(Database.getDatabase().getUser(username.getText()));
            }catch (Exception exception)
            {
                error.setText(exception.getMessage());
                error.setVisible(true);
            }
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

    @FXML
    void showPassword(MouseEvent event) {
        if(passwordHiding.isVisible())
        {
            showPassword.setImage(new Image(Main.class.getResource("pics/Screenshot 2025-01-24 17.png").toExternalForm()));
            passwordShowing.setText(passwordHiding.getText());
        }
        else
        {
            showPassword.setImage(new Image(Main.class.getResource("pics/Screenshot 2025-01-24 125507.png").toExternalForm()));
            passwordHiding.setText(passwordShowing.getText());
        }
        passwordHiding.setVisible(!passwordHiding.isVisible());
        passwordShowing.setVisible(!passwordShowing.isVisible());
        passwordShowing.setDisable(!passwordShowing.isDisable());
        passwordHiding.setDisable(!passwordHiding.isDisable());
    }
}
