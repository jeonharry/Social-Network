package model.database;

import model.User;
import model.graph.Graph;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database database;
    private final Map<String, User> users;
    private Graph connections;
    private Database() {
        this.users = new HashMap<>();
        connections=new Graph();
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
}
