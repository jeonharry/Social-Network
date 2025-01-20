package org.example.view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class SignupPageController{

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private VBox vbox;

    @FXML
    void next(MouseEvent event) {
//        Label error=new Label("This is invalid");
//        error.setFont(Font.font("System",FontWeight.BOLD,11));
//        error.setTextFill(Paint.valueOf("#e41e1e"));
//        error.setPrefWidth(238);
//        vbox.getChildren().add(2,error); 5 or 6
//        showError
    }

    @FXML
    void openLoginPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Controller.getController().getRoot().getChildren().removeLast();
    }
}
