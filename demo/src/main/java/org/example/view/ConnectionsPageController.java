package org.example.view;

import controller.Controller;
import controller.UserController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.User;
import model.database.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionsPageController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label connectionNum_lbl;

    @FXML
    private VBox connections;

    @FXML
    private Label username_lbl;

    @FXML
    void back(MouseEvent event) throws IOException {
        UserPageController.setUser(Controller.getController().getUsersProfiles().peek());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.getController().setRoot(root);
        User user = Controller.getController().getUsersProfiles().peek();
        username_lbl.setText(user.getUsername());
        connectionNum_lbl.setText(String.valueOf(Database.getDatabase().getConnections().values(user.getUsername()).size()));
        Label label=makeLabel("All Connections");
        connections.getChildren().addLast(label);
        for(String followings : Database.getDatabase().getConnections().values(user.getUsername()))
        {
            Controller.getController().setInConnections(true);
            User followingUser=Database.getDatabase().getUser(followings);
            ProfileBoxController.setRecentUser(followingUser);
            try {
                connections.getChildren().addLast(new FXMLLoader(Main.class.getResource("ProfileBox.fxml")).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        label=makeLabel("Suggested For You");
        connections.getChildren().addLast(label);
        for(User suggest: UserController.getUserController().getSuggestions())
        {
            Controller.getController().setInConnections(false);
            ProfileBoxController.setRecentUser(suggest);
            try {
                connections.getChildren().addLast(new FXMLLoader(Main.class.getResource("ProfileBox.fxml")).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Label makeLabel(String mess)
    {
        Label label=new Label(mess);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR,17));
        return label;
    }
}
