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
import java.util.Objects;


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
        addUsers();
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void addUsers(){
        User user1=new User("jeonharry","harry jeon","1234","jeon666@gmail.com",null,"OK", Objects.requireNonNull(Main.class.getResource("pics/pic1.jpg")).toExternalForm());
        User user2=new User("mina","Mina Abbapour","1234","mina4434@gmail.com",null,"Happy :)", Objects.requireNonNull(Main.class.getResource("pics/images(1).jpg")).toExternalForm());
        User user3=new User("bale","bala","1234","baba666@gmail.com",null,"NO major", Objects.requireNonNull(Main.class.getResource("pics/bale.jpg")).toExternalForm());
        User user4=new User("Sahar","Sahar Fatemi","1234","jeon666@gmail.com",null,"Hello World!", Objects.requireNonNull(Main.class.getResource("pics/sahar.jpg")).toExternalForm());
        User user5=new User("Ali_Reza","Alireza Sadeghi","1234","jeon666@gmail.com",null,"Lord Of The Rings", Objects.requireNonNull(Main.class.getResource("pics/lord.jpg")).toExternalForm());
        User user6=new User("e","Maryam Kabiri","1234","jeon666@gmail.com",null,"Harry Potter", Objects.requireNonNull(Main.class.getResource("pics/potter.jpg")).toExternalForm());
        User user7=new User("d","Kiana Fakhrian","1234","jeon666@gmail.com",null,"Piano\nFrance", Objects.requireNonNull(Main.class.getResource("pics/piano.jpg")).toExternalForm());
        User user8=new User("c","Mahdi Semsarzade","1234","jeon666@gmail.com",null,"Music is Everything", Objects.requireNonNull(Main.class.getResource("pics/music.jpg")).toExternalForm());
        User user9=new User("b","AmirHossein Ala","1234","jeon666@gmail.com",null,"I'm unstoppable", Objects.requireNonNull(Main.class.getResource("pics/aha.jpg")).toExternalForm());
        User user10=new User("a","Hossein Momeni","1234","jeon666@gmail.com",null,"World is beautiful because Math is beautiful", Objects.requireNonNull(Main.class.getResource("pics/math.jpg")).toExternalForm());
        Post post1=new Post(Objects.requireNonNull(Main.class.getResource("pics/image123.jpg")).toExternalForm(),"This is dope guys!",user1);
        post1.addComment(new Comment("wow, thats cool",user4));
        post1.addComment(new Comment("wow!",user2));
        post1.addComment(new Comment("Nah",user3));
        user1.getPosts().addLast(post1);
        Database.getDatabase().add(user1);
        Database.getDatabase().add(user2);
        Database.getDatabase().add(user3);
        Database.getDatabase().add(user4);
        Database.getDatabase().add(user5);
        Database.getDatabase().add(user6);
        Database.getDatabase().add(user7);
        Database.getDatabase().add(user8);
        Database.getDatabase().add(user9);
        Database.getDatabase().add(user10);
        Database.getDatabase().getConnections().insert("jeonharry","mina");
        Database.getDatabase().getConnections().insert("jeonharry","bale");
        Database.getDatabase().getConnections().insert("mina","bale");
        Database.getDatabase().getConnections().insert("a","b");
        Database.getDatabase().getConnections().insert("a","c");
        Database.getDatabase().getConnections().insert("a","d");
        Database.getDatabase().getConnections().insert("e","c");
        Database.getDatabase().getConnections().insert("e","d");
//        Database.getDatabase().getConnections().insert(user4.getUsername(),"bale");
//        Database.getDatabase().getConnections().insert(user5.getUsername(),"bale");
//        Database.getDatabase().getConnections().insert(user6.getUsername(),"bale");
//        Database.getDatabase().getConnections().insert(user7.getUsername(),"bale");
//        Database.getDatabase().getConnections().insert(user8.getUsername(),"bale");
//        Database.getDatabase().getConnections().insert(user9.getUsername(),"bale");
    }
}