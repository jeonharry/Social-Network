package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    void next(MouseEvent event) {
        int count=0;
        try
        {
//            UserController.getUserController().signup(username.getText(),fullName.getText(),password.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
            Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
        }catch (NotValidEmail exception)
        {
            Label error=makeLabel(exception.getMessage());
            vbox.getChildren().add(2,error);
            count++;
        }
        catch (WeakPassword exception)
        {
            Label error=makeLabel(exception.getMessage());
            vbox.getChildren().add(4+count,error);
            count++;
        }
        catch (UserNameExists exception)
        {
            Label error=makeLabel(exception.getMessage());
            vbox.getChildren().add(5+count,error);
            count++;
        }
        catch (Exception exception){
            Label error=makeLabel(exception.getMessage());
            vbox.getChildren().add(6+count,error);
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
    void openLoginPage(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Controller.getController().getRoot().getChildren().removeLast();
    }
}
