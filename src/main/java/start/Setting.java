package start;


import controllers.SettingController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Егор on 29.02.2016.
 */
public class Setting {

    private static Scene sceneSetting;
    private static Parent rootSetting;

    public void showScene(){
        try {
            rootSetting = FXMLLoader.load(getClass().getResource("/fxml/setting.fxml"));
        } catch (IOException e) {
            System.out.println("Ошибка подключения Setting.fxml");
        }
        sceneSetting = new Scene(rootSetting, 1290, 680);
        sceneSetting.getStylesheets().add("/styles/setting.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneSetting);


        sceneSetting.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.ESCAPE){
                    try {
                        File f = new File("./src/main/resources/files/setting");
                        PrintWriter writer = new PrintWriter(f);
                        writer.write(SettingController.controll+"\n");
                        writer.write(SettingController.weather);
                        writer.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("проблемы при записи в файл" + e);
                    }

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 1290, 680);
                    scene.getStylesheets().add("/styles/main.css");
                    MainApp.stage.setTitle("BLOWN");
                    MainApp.stage.setScene(scene);
                }
            }
        });
    }
}