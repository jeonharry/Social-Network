package controller;

import model.User;
import model.database.Database;
import model.exceptions.*;

import java.util.Date;
import java.util.regex.Pattern;

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
    public void signup(String username, String fullName,String password, String email, Date birthdate, String bio, String profile) throws UserNameExists,NotValidEmail, WeakPassword {
        if(Database.getDatabase().exist(username))
            throw new UserNameExists();
        String emailRegex="^[^(\\.|\\W)](?=\\d*[a-zA-Z])([a-zA-Z0-9]\\.?){1,25}[^(\\.|\\W)]@(?=\\d*[a-zA-Z])([a-zA-Z0-9]-?){2,28}[^\\W-]\\.[a-zA-Z]{2,20}$";
        String passwordRegex="^$";
        Pattern emailPattern=Pattern.compile(emailRegex);
        Pattern passwordPattern=Pattern.compile(passwordRegex);
        if(!emailPattern.matcher(email).matches())
            throw new NotValidEmail();
        if(!passwordPattern.matcher(password).matches())
            throw new WeakPassword();
        user=new User(username,fullName,password,email,birthdate,bio,profile);
        Database.getDatabase().add(username,user);
    }
}
