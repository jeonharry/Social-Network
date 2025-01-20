package model.database;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database database;
    private final Map<String, User> users;

    private Database() {
        this.users = new HashMap<>();
    }

    public static Database getDatabase() {
        if(database == null)
            database = new Database();
        return database;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
