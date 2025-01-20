package org.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.database.Database;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 650);
        stage.setTitle("Fakestagram");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        User user1=new User("jeonharry","harry jeon","1234","jeon666@gmail.com",new Date(),"OK","");
        Database.getDatabase().add("jeonharry",user1);
        launch();
    }
}