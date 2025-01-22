package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import model.exceptions.UserNameExists;

public class EditUserPageController implements Initializable {

    @FXML
    private Circle crl_profile;

    @FXML
    private TextArea txt_bio;

    @FXML
    private TextField txt_fullName;

    @FXML
    private TextField txt_username;

    @FXML
    private VBox vbox;

    @FXML
    private Label lbl_save;

    private File file;
    private User user;

    @FXML
    void back(MouseEvent event) throws IOException {
        back();
    }

    @FXML
    void editProfile(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);
        if(file != null)
            crl_profile.setFill(new ImagePattern(new Image(file.toURI().toString())));
    }

    @FXML
    void save(MouseEvent event) throws IOException {
        if(!txt_fullName.getText().isEmpty() && !txt_username.getText().isEmpty()){
            if(!user.getUsername().equals(txt_username.getText())){
                try {
                    if(file != null)
                        UserController.getUserController().editUser(txt_username.getText(),txt_fullName.getText(),txt_bio.getText(),file.toURI().toString());
                    else
                        UserController.getUserController().editUser(txt_username.getText(),txt_fullName.getText(),txt_bio.getText(),user.getProfile());
                    UserPageController.setUser(UserController.getUserController().getUser());
                    back();
                } catch (UserNameExists exception) {
                    Label error=makeLabel(exception.getMessage());
                    vbox.getChildren().add(6,error);
                }
            }
            else{
                if(file != null)
                    UserController.getUserController().editUser(txt_fullName.getText(),txt_bio.getText(),file.toURI().toString());
                else
                    UserController.getUserController().editUser(txt_fullName.getText(),txt_bio.getText(),user.getProfile());
                back();
            }
        }
    }
    private Label makeLabel(String mes)
    {
        Label error=new Label(mes);
        error.setFont(Font.font("System", FontWeight.BOLD,11));
        error.setTextFill(Paint.valueOf("#e41e1e"));
        error.setPrefWidth(238);
        error.setAlignment(Pos.CENTER_LEFT);
        return error;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file = null;
        user = UserController.getUserController().getUser();
        txt_bio.setText(user.getBio());
        txt_username.setText(user.getUsername());
        txt_fullName.setText(user.getFullName());
        ImagePattern profile = new ImagePattern(new Image(user.getProfile()));
        crl_profile.setFill(profile);
    }

    private void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Main.getStage().setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    void edited(KeyEvent event) {
        lbl_save.setTextFill(Color.WHITE);
    }
}
