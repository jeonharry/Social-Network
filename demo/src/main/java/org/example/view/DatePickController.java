package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatePickController implements Initializable {

    @FXML
    private MenuButton date;

    @FXML
    private MenuButton month;

    @FXML
    private VBox vbox;

    @FXML
    private MenuButton year;

    @FXML
    void goBack(MouseEvent event) {
        Controller.getController().getRoot().getChildren().removeLast();
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        int m=getMonth(month.getText());
        String d=year.getText()+"/"+String.valueOf(m)+"/"+date.getText();
        UserController.getUserController().levelTwoSignup(d);
        UserPageController.setUser(UserController.getUserController().getUser());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Controller.getController().getRoot().getChildren().clear();
        Controller.getController().getRoot().getChildren().addLast(fxmlLoader.load());
    }

    @FXML
    void openLoginPage(MouseEvent event) {
        Controller.getController().getRoot().getChildren().removeLast();
        Controller.getController().getRoot().getChildren().removeLast();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuItem item;
        for(int i=1;i<=30;++i)
        {
            item=new MenuItem(String.valueOf(i));
            date.getItems().addLast(item);
            item.setOnAction(e->{
                date.setText(((MenuItem)e.getSource()).getText());
            });
        }
        for(int i=2025;i>=1970;--i)
        {
            item=new MenuItem(String.valueOf(i));
            year.getItems().addLast(item);
            item.setOnAction(e->{
                year.setText(((MenuItem)e.getSource()).getText());
            });
        }
        for(MenuItem menuItem: month.getItems())
            menuItem.setOnAction(e->{
                month.setText(((MenuItem)e.getSource()).getText());
            });
    }
    private int getMonth(String month)
    {
        if(month.compareTo("January")==0)
            return 1;
        else if(month.compareTo("February")==0)
            return 2;
        else if(month.compareTo("March")==0)
            return 3;
        else if(month.compareTo("April")==0)
            return 4;
        else if(month.compareTo("May")==0)
            return 5;
        else if(month.compareTo("June")==0)
            return 6;
        else if(month.compareTo("July")==0)
            return 7;
        else if(month.compareTo("August")==0)
            return 8;
        else if(month.compareTo("September")==0)
            return 9;
        else if(month.compareTo("October")==0)
            return 10;
        else if(month.compareTo("November")==0)
            return 11;
        else
            return 12;
    }
}
