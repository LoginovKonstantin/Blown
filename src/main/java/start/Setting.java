package start;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

        sceneSetting = new Scene(rootSetting, 1290, 680);
        sceneSetting.getStylesheets().add("/styles/setting.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneSetting);

        sceneSetting.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.ESCAPE){
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
