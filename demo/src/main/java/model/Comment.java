package model;

public class Comment {
    private final String message;
    private final User sender;

    public Comment(String message, User sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }
}
