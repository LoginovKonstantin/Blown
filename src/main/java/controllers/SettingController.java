package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


/**
 * Created by Егор on 29.02.2016.
 */
public class SettingController {
    @FXML
    private RadioButton radioBtnLeft;
    @FXML
    private RadioButton radioBtnRight;

    public static String controll = "";

    public void checkRadio(ActionEvent actionEvent) {
        if(radioBtnLeft.isSelected()){
            controll = "left";
        }
        if(radioBtnRight.isSelected()){
            controll = "right";
        }
    }
}
