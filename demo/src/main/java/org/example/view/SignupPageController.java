package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.exceptions.NotValidEmail;
import model.exceptions.UserNameExists;
import model.exceptions.WeakPassword;

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
    private Label nextLabel;
    boolean emailEntered=false;
    boolean fullNameEntered=false;
    boolean passwordEntered=false;
    boolean usernameEntered=false;

    @FXML
    void checkPassword(KeyEvent event) {
        int score = UserController.getUserController().checkPassword(password.getText());
        if(vbox.getChildren().get(2) instanceof Label) vbox.getChildren().remove(2);
        if(vbox.getChildren().get(4) instanceof Label) vbox.getChildren().remove(4);
        Label error;
        switch (score) {
            case 1,0 -> {
                error=makeLabel("Very Weak");
                error.setTextFill(Color.RED);
            }
            case 2, 3 -> {
                error=makeLabel("Weak");
                error.setTextFill(Color.ORANGE);
            }
            case 4 -> {
                error=makeLabel("Good");
                passwordEntered=true;
                error.setTextFill(Color.YELLOWGREEN);
            }
            default -> {
                error=makeLabel("Strong");
                passwordEntered=true;
                error.setTextFill(Color.GREEN);
            }
        }
        vbox.getChildren().add(4,error);
    }


    @FXML
    void next(MouseEvent event) throws IOException {
        if(passwordEntered && emailEntered && usernameEntered && fullNameEntered)
        {
            try
            {
                UserController.getUserController().levelOneSignup(username.getText(),fullName.getText(),password.getText(),email.getText(),Main.class.getResource("pics/images(5).png").toExternalForm());
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DatePickPage.fxml"));
                Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
            }catch (NotValidEmail exception)
            {
                if(vbox.getChildren().get(2) instanceof Label)
                    vbox.getChildren().remove(2);
                Label error=makeLabel(exception.getMessage());
                vbox.getChildren().add(2,error);
            }
            catch (WeakPassword exception)
            {
                if(vbox.getChildren().get(2) instanceof Label)
                    vbox.getChildren().remove(2);
                if(vbox.getChildren().get(4) instanceof Label)
                    vbox.getChildren().remove(4);
                Label error=makeLabel(exception.getMessage());
                vbox.getChildren().add(4,error);
            }
            catch (UserNameExists exception)
            {
                if(vbox.getChildren().get(2) instanceof Label)
                    vbox.getChildren().remove(2);
                if(vbox.getChildren().get(4) instanceof Label)
                    vbox.getChildren().remove(4);
                if(vbox.getChildren().get(5) instanceof Label)
                    vbox.getChildren().remove(5);
                Label error=makeLabel(exception.getMessage());
                vbox.getChildren().add(5,error);
            }
            catch (Exception exception){
                if(vbox.getChildren().get(2) instanceof Label)
                    vbox.getChildren().remove(2);
                if(vbox.getChildren().get(4) instanceof Label)
                    vbox.getChildren().remove(4);
                if(vbox.getChildren().get(5) instanceof Label)
                    vbox.getChildren().remove(5);
                if(vbox.getChildren().size()>=7 && vbox.getChildren().get(6) instanceof Label)
                    vbox.getChildren().remove(6);
                Label error=makeLabel(exception.getMessage());
                vbox.getChildren().add(6,error);
                throw exception;
            }
        }
    }

    public Label makeLabel(String mes)
    {
        Label error=new Label(mes);
        error.setFont(Font.font("System",FontWeight.BOLD,11));
        error.setTextFill(Paint.valueOf("#e41e1e"));
        error.setPrefWidth(238);
        return error;
    }

    @FXML
    void openLoginPage(MouseEvent event) {
        Controller.getController().getRoot().getChildren().removeLast();
    }

    @FXML
    void changeColor(KeyEvent event) {
        if(((TextField)event.getSource()).getPromptText().compareTo("Email")==0)
            emailEntered=true;
        else if(((TextField)event.getSource()).getPromptText().compareTo("Full name")==0)
            fullNameEntered=true;
        else if(((TextField)event.getSource()).getPromptText().compareTo("Username")==0)
            usernameEntered=true;
        if(passwordEntered && emailEntered && fullNameEntered && usernameEntered)
            nextLabel.setTextFill(Paint.valueOf("white"));
    }
}
