package controller;

import model.User;
import model.database.Database;
import model.exceptions.NotExistenceUserName;
import model.exceptions.UserNameExists;
import model.exceptions.WrongPassword;

import java.util.Date;

public class UserController {
    private static UserController userController;
    private User user;
    private UserController() {
    }

    public static UserController getUserController() {
        if(userController == null)
            userController = new UserController();
        return userController;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void login(String username,String password) throws Exception {
        if(!Database.getDatabase().exist(username))
            throw new NotExistenceUserName();
        if(!Database.getDatabase().checkPassword(username,password))
            throw new WrongPassword();
        user=Database.getDatabase().getUser(username);
    }
    public void logout()
    {
        user=null;
    }
    public void signup(String username, String fullName,String password, String email, Date birthdate, String bio, String profile) throws Exception {
        if(Database.getDatabase().exist(username))
            throw new UserNameExists();
        //regexes
        String regex="";
        user=new User(username,fullName,password,email,birthdate,bio,profile);
        Database.getDatabase().add(username,user);
    }
}
