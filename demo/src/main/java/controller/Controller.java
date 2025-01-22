package controller;

import javafx.scene.layout.AnchorPane;
import model.OpenedPage;
import model.Post;
import model.User;

import java.util.Stack;

public class Controller
{
    private AnchorPane root;
    private static Controller controller;
    private Stack <User> usersProfiles;
    private Stack <Post> openedPosts;
    private Stack <OpenedPage> openedPages;
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

    public void setUsersProfiles(Stack<User> usersProfiles) {
        this.usersProfiles = usersProfiles;
    }

    public Stack<Post> getOpenedPosts() {
        return openedPosts;
    }

    public void setOpenedPosts(Stack<Post> openedPosts) {
        this.openedPosts = openedPosts;
    }
    public Stack<OpenedPage> getOpenedPages() {
        return openedPages;
    }
    public void setOpenedPages(Stack<OpenedPage> openedPages) {
        this.openedPages = openedPages;
    }
}
