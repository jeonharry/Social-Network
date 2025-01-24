package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User {
    private String username;
    private String fullName;
    private final String password;
    private final String email;
    private Calendar birthdate;
    private String bio;
    private String profile;
    private ArrayList<Post> posts;

    public User(String username, String fullName,String password, String email, Calendar birthdate, String bio, String profile) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.bio = bio;
        this.profile = profile;
        this.posts = new ArrayList<>();
    }
    public User(String username, String fullName,String password, String email,String profile) {
        this(username,fullName,password,email,null,"",profile);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public Calendar getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate =Calendar.getInstance();
        this.birthdate.setTime(birthdate);
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
}