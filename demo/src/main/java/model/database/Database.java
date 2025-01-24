package model.database;

import model.User;
import model.graph.Graph;
import org.example.view.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Database {
    private static Database database;
    private final Map<String, User> users;
    private final ArrayList<String> orderOfSignup;
    private final Graph connections;
    private Database() {
        this.users = new HashMap<>();
        this.connections=new Graph();
        this.orderOfSignup = new ArrayList<>();
        this.users.put("Deleted Account", new User("Deleted Account",null,null,null, Objects.requireNonNull(Main.class.getResource("pics/images(5).png")).toExternalForm()));
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
    public void add(User user)
    {
        users.put(user.getUsername(),user);
        connections.insert(user.getUsername());
        orderOfSignup.add(user.getUsername());
    }

    public void deleteUSer(User user){
        getUsers().remove(user.getUsername());
        getConnections().remove(user.getUsername());
        orderOfSignup.remove(user.getUsername());
        users.values().forEach(account -> account.getPosts().forEach(post -> post.getComments().stream().filter(comment -> comment.getSender().equals(user)).forEach(comment -> comment.setSender(users.get("Deleted Account")))));
    }

    public void editUser(User user, User newUser){
        getUsers().remove(user.getUsername());
        getUsers().put(newUser.getUsername(),newUser);
        users.values().forEach(account -> account.getPosts().forEach(post -> post.getComments().stream().filter(comment -> comment.getSender().equals(user)).forEach(comment -> comment.setSender(newUser))));
        users.values().forEach(account -> account.getPosts().forEach(post -> {
            if(post.isLike(user.getUsername())){
                post.getLikes().remove(user.getUsername());
                post.addLike(newUser.getUsername());
            }
        }));
        user.getPosts().forEach(post -> post.setOwner(newUser));
        int i=orderOfSignup.indexOf(user.getUsername());
        orderOfSignup.remove(user.getUsername());
        orderOfSignup.add(i,newUser.getUsername());
        getConnections().edite(user.getUsername(),newUser.getUsername());
    }

    public ArrayList<User> suggestions(String username){
        ArrayList<User> answer = new ArrayList<>();
        for(String user : connections.findSharing(username)){
            answer.add(users.get(user));
        }
        int cnt = orderOfSignup.size()-1;
        while (answer.size() < 6 && cnt>=0){
            if(!answer.contains(users.get(orderOfSignup.get(cnt))) && !connections.values(username).contains(orderOfSignup.get(cnt)) && username.compareTo(orderOfSignup.get(cnt))!=0)
                answer.add(users.get(orderOfSignup.get(cnt)));
            cnt--;
        }
        return answer;
    }
}
