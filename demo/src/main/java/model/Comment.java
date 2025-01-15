package model;

public class Comment {
    private final String message;
    private final String sender;

    public Comment(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
