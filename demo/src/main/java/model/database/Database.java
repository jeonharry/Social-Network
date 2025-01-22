package model.database;

import controller.UserController;
import model.User;
import model.graph.Graph;
import org.example.view.Main;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database database;
    private final Map<String, User> users;
    private Graph connections;
    private Database() {
        this.users = new HashMap<>();
        connections=new Graph();
        add( "Deleted Account",new User("Deleted Account",null,null,null, Main.class.getResource("pics/images(5).png").toExternalForm()));
    }

    public static Database getDatabase() {
        if(database == null)
            database = new Database();
        return database;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Graph getConnections() {
        return connections;
    }

    public void setConnections(Graph connections) {
        this.connections = connections;
    }

    public boolean exist(String username)
    {
        return users.containsKey(username);
    }
    public boolean checkPassword(String username,String password)
    {
        return users.get(username).getPassword().compareTo(password)==0;
    }
    public User getUser(String username)
    {
        return users.get(username);
    }
    public void add(String username,User user)
    {
        users.put(username,user);
        connections.insert(username);
    }

    public void deleteUSer(User user){
        getUsers().remove(user.getUsername());
        getConnections().remove(user.getUsername());
        users.values().forEach(account -> account.getPosts().forEach(post -> post.getComments().stream().filter(comment -> comment.getSender().equals(user)).forEach(comment -> comment.setSender(users.get("Deleted Account")))));
    }

    public void editUser(User user, User newUser){
        getUsers().remove(user.getUsername());
        getUsers().put(newUser.getUsername(),newUser);
        users.values().forEach(account -> account.getPosts().forEach(post -> post.getComments().stream().filter(comment -> comment.getSender().equals(user)).forEach(comment -> comment.setSender(newUser))));
        getConnections().edite(user.getUsername(),newUser.getUsername());
    }
}
