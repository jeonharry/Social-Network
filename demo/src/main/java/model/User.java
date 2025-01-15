package model;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private Date birthdate;
    private String bio;
    private String profile;

    public User(String username, String password, String email, Date birthdate, String bio, String profile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.bio = bio;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
}