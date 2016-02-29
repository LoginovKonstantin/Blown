package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by 4 on 28.02.2016.
 */
public class MainController {
    @FXML
    private Button btn;

    @FXML
    private Label label;

    public void sayHello(ActionEvent actionEvent) {
        label.setText("HELLO");
    }
}
