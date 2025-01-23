package org.example.view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MakeSureController {

    @FXML
    void sayNo(MouseEvent event) {
        Controller.getController().setMakeSure(false);
        close();
    }

    @FXML
    void sayYes(MouseEvent event) {
        Controller.getController().setMakeSure(true);
        close();
    }
    private void close()
    {
        Controller.getController().getMakeSureStage().close();
    }
}
