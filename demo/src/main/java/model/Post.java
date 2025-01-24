package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Post {
    private String image;
    private String caption;
    private final ArrayList<Comment> comments;
    private final Set<String> likes;
    private User owner;

    public Post(String image, String caption,User owner) {
        this.image = image;
        this.caption = caption;
        this.comments = new ArrayList<>();
        this.likes = new HashSet<>();
        this.owner=owner;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }
    public Set<String> getLikes() {
        return likes;
    }
    public void addLike(String user) {
        likes.add(user);
    }
    public boolean isLike(String user){
        return likes.contains(user);
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
