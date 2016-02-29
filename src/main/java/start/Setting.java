package start;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Created by Егор on 29.02.2016.
 */
public class Setting {
    private static Scene sceneSetting;
    private static Parent rootSetting;

    public void Setting(){

    }

    public void ShowScene(){
        try {
            rootSetting = FXMLLoader.load(getClass().getResource("/fxml/setting.fxml"));

        } catch (IOException e) {
            System.out.println("Ошибка подключения Setting.fxml");
        }

        sceneSetting = new Scene(rootSetting, 1280, 670);
        sceneSetting.getStylesheets().add("/styles/setting.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneSetting);


    }
}
