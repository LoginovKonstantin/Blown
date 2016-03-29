package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private RadioButton radioBtnWinter;
    @FXML
    private RadioButton radioBtnSummer;
    @FXML
    private ToggleButton toggleButtonWinter;
    @FXML
    private ToggleButton toggleButtonSummer;

    public static String controll = "up";
    public static String weather="";

    @FXML
    private void initialize(){
        File file = new File("./src/main/resources/files/setting");
        try {
            summerSetting.setLayoutX(483);
            summerSetting.setLayoutY(510);
            summerSetting.setImage(new Image("./images/chillSummer.png"));
            winterSetting.setLayoutX(685);
            winterSetting.setLayoutY(510);
            winterSetting.setImage(new Image("./images/chillWinter.png"));

//            toggleButton = new ToggleButton("", winterSetting);
            toggleButtonWinter.graphicProperty().setValue(winterSetting);
            toggleButtonSummer.graphicProperty().setValue(summerSetting);

            Scanner in = new Scanner(file);
                controll = in.nextLine();
                weather= in.nextLine();
            in.close();
            if(controll.equals("up")) {
                radioBtnUp.setSelected(true);
            }else{
                if(controll.equals("down")){
                    radioBtnDown.setSelected(true);
                }
            }
//            if(weather.equals("summer")) {
//                radioBtnSummer.setSelected(true);
//            }else{
//                if(weather.equals("winter")){
//                    radioBtnWinter.setSelected(true);
//                }
//            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
    }

    public static String getControll(){
        File file = new File("./src/main/resources/files/setting");
        try {
            Scanner in = new Scanner(file);
                controll = in.nextLine();
            in.close();
        } catch (FileNotFoundException e) {

        }
        return controll;
    }

    public static String getWeather(){
        File file = new File("./src/main/resources/files/setting");
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                weather = in.nextLine();
            }
        } catch (FileNotFoundException e) {

        }
        return weather;
    }
    public void checkRadio(ActionEvent actionEvent) {
        if(radioBtnUp.isSelected()){
            controll = "up";
        }
        if(radioBtnDown.isSelected()){
            controll = "down";
        }
        if (radioBtnSummer.isSelected()){
            weather="summer";
        }
        if (radioBtnWinter.isSelected()){
            weather="winter";
        }

    }
}
