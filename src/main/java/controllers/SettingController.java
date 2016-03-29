package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;


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
    private ImageView summerSetting;
    @FXML
    private ImageView winterSetting;
    @FXML
    private RadioButton radioBtnDown;
    @FXML
    private ToggleButton toggleButtonWinter;
    @FXML
    private ToggleButton toggleButtonSummer;

    public static String controll = "up";

    public static String weather = "summer";

    @FXML
    private void initialize() {
        File file = new File("./src/main/resources/files/setting");
        try {
            summerSetting.setImage(new Image("./images/chillSummer.png"));
            winterSetting.setImage(new Image("./images/chillWinter.png"));

            toggleButtonWinter.graphicProperty().setValue(winterSetting);

            toggleButtonSummer.graphicProperty().setValue(summerSetting);

            Scanner in = new Scanner(file);
            controll = in.nextLine();
            weather = in.nextLine();
            in.close();
            if (controll.equals("up")) {
                radioBtnUp.setSelected(true);
            } else {
                if (controll.equals("down")) {
                    radioBtnDown.setSelected(true);
                }
            }
            if(weather.equals("summer")) {
                toggleButtonSummer.setStyle("-fx-base:lightgreen");
            }else{
                if(weather.equals("winter")){
                    toggleButtonWinter.setStyle("-fx-base:lightgreen");
                }
            }
          } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
    }

    public static String getControll() {
        File file = new File("./src/main/resources/files/setting");
        try {
            Scanner in = new Scanner(file);
            controll = in.nextLine();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка в SettingController.getControll()");
        }
        return controll;
    }

    public static String getWeather() {
        File file = new File("./src/main/resources/files/setting");
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                weather = in.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка в SettingController.getWeather()");
        }
        return weather;
    }

    public void checkRadio(ActionEvent actionEvent) {
        if (radioBtnUp.isSelected()) {
            controll = "up";
        }
        if (radioBtnDown.isSelected()) {
            controll = "down";
        }
    }

    public void checkToggle2(ActionEvent actionEvent) {
        weather = "summer";
        toggleButtonSummer.setStyle("-fx-base:lightgreen");
        toggleButtonWinter.setStyle("-fx-base:white");
    }

    public void checkToggle1(ActionEvent actionEvent) {
        weather = "winter";
        toggleButtonWinter.setStyle("-fx-base:lightgreen");
        toggleButtonSummer.setStyle("-fx-base:white");
    }
}
