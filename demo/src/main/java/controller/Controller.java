package controller;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.OpenedPage;
import model.Post;
import model.User;

import java.util.Stack;

public class Controller
{
    private AnchorPane root;
    private static Controller controller;
    private final Stack <User> usersProfiles;
    private final Stack <Post> openedPosts;
    private final Stack <OpenedPage> openedPages;
    private Label commentsCount;
    private boolean isInConnections=true;
    private boolean makeSure=false;
    private Stage makeSureStage;
    private Controller(){
        usersProfiles=new Stack<>();
        openedPosts=new Stack<>();
        openedPages=new Stack<>();
    }
    public static Controller getController()
    {
        if(controller==null)
            controller=new Controller();
        return controller;
    }
    public AnchorPane getRoot()
    {
        return root;
    }
    public void setRoot(AnchorPane root)
    {
        this.root = root;
    }

    public Stack<User> getUsersProfiles() {
        return usersProfiles;
    }
    public Stack<Post> getOpenedPosts() {
        return openedPosts;
    }
    public Stack<OpenedPage> getOpenedPages() {
        return openedPages;
    }
    public Label getCommentsCount() {
        return commentsCount;
    }
    public void setCommentsCount(Label commentsCount) {
        this.commentsCount = commentsCount;
    }
    public boolean isInConnections() {
        return isInConnections;
    }
    public void setInConnections(boolean inConnections) {
        isInConnections = inConnections;
    }
    public boolean isMakeSure() {
        return makeSure;
    }
    public void setMakeSure(boolean makeSure) {
        this.makeSure = makeSure;
    }
    public Stage getMakeSureStage() {
        return makeSureStage;
    }
    public void setMakeSureStage(Stage makeSureStage) {
        this.makeSureStage = makeSureStage;
    }
}
