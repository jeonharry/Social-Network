package org.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Comment;
import model.Post;
import model.User;
import model.database.Database;

import java.io.IOException;


public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setTitle("Fakestagram");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        User user1=new User("jeonharry","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user2=new User("mina","Mina","1234","mina666@gmail.com",null,"CE major",Main.class.getResource("pics/images(1).jpg").toExternalForm());
        User user3=new User("bale","bala","1234","baba666@gmail.com",null,"NO major",Main.class.getResource("pics/images(1).jpg").toExternalForm());
        User user4=new User("pp","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user5=new User("tt","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user6=new User("aa","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user7=new User("bb","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user8=new User("cc","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user9=new User("dd","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        User user10=new User("ff","harry jeon","1234","jeon666@gmail.com",null,"OK",Main.class.getResource("pics/pic1.jpg").toExternalForm());
        Post post1=new Post(Main.class.getResource("pics/image123.jpg").toExternalForm(),"This is dope guys!",user1);
        post1.getComments().add(new Comment("wow, thats cool",user1));
        post1.getComments().add(new Comment("wow!",user2));
        post1.getComments().add(new Comment("Nah",user3));
        user1.getPosts().addLast(post1);
        Database.getDatabase().add("jeonharry",user1);
        Database.getDatabase().add("mina",user2);
        Database.getDatabase().add("bale",user3);
        Database.getDatabase().add("pp",user4);
        Database.getDatabase().add("tt",user5);
        Database.getDatabase().add("aa",user6);
        Database.getDatabase().add("bb",user7);
        Database.getDatabase().add("cc",user8);
        Database.getDatabase().add("dd",user9);
        Database.getDatabase().add("ff",user10);
        Database.getDatabase().getConnections().insert("jeonharry","mina");
        Database.getDatabase().getConnections().insert("jeonharry","bale");
        Database.getDatabase().getConnections().insert("mina","bale");
        Database.getDatabase().getConnections().insert("dd","bale");
        Database.getDatabase().getConnections().insert("cc","bale");
        Database.getDatabase().getConnections().insert("aa","bale");
        Database.getDatabase().getConnections().insert("dd","bale");
        Database.getDatabase().getConnections().insert("ff","bale");
        Database.getDatabase().getConnections().insert("bb","bale");
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}