package model;

import java.util.ArrayList;

public class Post {
    private String image;
    private String caption;
    private ArrayList<Comment> comments;
    private int likes;

    public Post(String image, String caption) {
        this.image = image;
        this.caption = caption;
        this.comments = new ArrayList<>();
        this.likes = 0;
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

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
