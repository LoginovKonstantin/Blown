package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Created by Егор on 29.02.2016.
 */
public class SettingController {
    @FXML
    private RadioButton radioBtnUp;
    @FXML
    private RadioButton radioBtnDown;
    public static String controll = "";

    @FXML
    private void initialize(){
        File file = new File("./src/main/resources/files/setting");
        try {
            String s = "";
            Scanner in = new Scanner(file);
            while(in.hasNext()){
                s += in.nextLine();
            }
            in.close();
            if(s.equals("up")) {
                radioBtnUp.setSelected(true);
            }else{
                radioBtnDown.setSelected(true);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkRadio(ActionEvent actionEvent) {
        if(radioBtnUp.isSelected()){
            controll = "up";
        }
        if(radioBtnDown.isSelected()){
            controll = "down";
        }
    }
}
