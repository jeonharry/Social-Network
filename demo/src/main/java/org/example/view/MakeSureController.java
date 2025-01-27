package org.example.view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MakeSureController implements Initializable {

    @FXML
    private VBox vbox;
    private static String req;

    @FXML
    void sayNo(MouseEvent event) {
        close();
    }

    @FXML
    void sayYes(MouseEvent event) throws IOException {
        if(req.compareTo("logout")==0)
        {
            close();
            UserPageController.logout();
        }
        else if(req.compareTo("deleteAcc")==0)
        {
            close();
            UserPageController.deleteAccount();
        }
        else if(req.compareTo("deletePost")==0)
        {
            close();
            PostPageController.deletePost();
        }
    }
    private void close()
    {
        Controller.getController().getRoot().getChildren().removeLast();
    }

    public static String getReq() {
        return req;
    }

    public static void setReq(String req) {
        MakeSureController.req = req;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(req.compareTo("deletePost")==0)
        {
            vbox.setTranslateX(-120);
        }
    }
}
