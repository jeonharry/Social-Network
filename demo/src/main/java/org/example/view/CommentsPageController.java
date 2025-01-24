package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Comment;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentsPageController implements Initializable {

    @FXML
    private TextField txtField_comment;

    @FXML
    private GridPane comments;

    private static Post post;

    @FXML
    void back(MouseEvent event) {
        event.consume();
        Controller.getController().getCommentsCount().setText(String.valueOf(PostPageController.getOpenedPost().getComments().size()));
        Controller.getController().getRoot().getChildren().removeLast();
    }

    @FXML
    void sendComment(MouseEvent event) throws IOException {
        Comment newComment=new Comment(txtField_comment.getText(),UserController.getUserController().getUser());
        post.addComment(newComment);
        if(post.getComments().size()>comments.getRowCount())
            comments.addRow(comments.getRowCount());
        CommentPageController.setComment(newComment);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Comment.fxml"));
        comments.add(fxmlLoader.load(),0,post.getComments().size()-1);
        txtField_comment.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(post.getComments().size()>comments.getRowCount())
            while (post.getComments().size()>comments.getRowCount())
                comments.addRow(comments.getRowCount());
        int counter=0;
        for(Comment comment: post.getComments())
        {
            CommentPageController.setComment(comment);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Comment.fxml"));
            try
            {
                comments.add(fxmlLoader.load(),0,counter++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Post getPost() {
        return post;
    }

    public static void setPost(Post post) {
        CommentsPageController.post = post;
    }
}
