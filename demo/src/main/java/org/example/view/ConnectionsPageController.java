package org.example.view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    private ImageView back_img;

    @FXML
    private Label connectionNum_lbl;

    @FXML
    private GridPane connections;

    @FXML
    private AnchorPane root;

    @FXML
    private Label username_lbl;

    private User user;

    @FXML
    void back(MouseEvent event) throws IOException {
        UserPageController.setUser(Controller.getController().getUsersProfiles().peek());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserPage.fxml"));
        Main.getStage().setScene(new Scene(fxmlLoader.load(),700,650));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user=Controller.getController().getUsersProfiles().peek();
        username_lbl.setText(user.getUsername());
        connectionNum_lbl.setText(String.valueOf(Database.getDatabase().getConnections().values(user.getUsername()).size()));
        if(connections.getRowCount()<Database.getDatabase().getConnections().values(user.getUsername()).size()+6)
        {
            while (connections.getRowCount()<Database.getDatabase().getConnections().values(user.getUsername()).size()+6)
                connections.addRow(connections.getRowCount());
        }
        int counter=1;
        for(String followings:Database.getDatabase().getConnections().values(user.getUsername()))
        {
            User followingUser=Database.getDatabase().getUser(followings);
            ProfileBoxController.setRecentUser(followingUser);
            try {
                connections.add(new FXMLLoader(Main.class.getResource("ProfileBox.fxml")).load(),0, counter++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Label label=makeLabel();
        connections.add(label,0,counter);
        //add sugggestions
    }
    public Label makeLabel()
    {
        Label label=new Label("Suggested For You");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR,17));
        return label;
    }
}
