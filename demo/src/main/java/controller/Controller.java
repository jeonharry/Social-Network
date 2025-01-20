package controller;

import javafx.scene.layout.AnchorPane;

public class Controller
{
    private AnchorPane root;
    private static Controller controller;
    private Controller(){}
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
}
