package org.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Post;
import model.User;
import model.database.Database;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 650);
        stage.setTitle("Fakestagram");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        User user1=new User("jeonharry","harry jeon","1234","jeon666@gmail.com",new Date(),"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        user1.getPosts().addLast(new Post(Main.class.getResource("pics/image123.jpg").toExternalForm(),"This is dope guys!",user1));
        Database.getDatabase().add("jeonharry",user1);
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}