package start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Егор on 29.02.2016.
 */
public class Setting {
    private static Scene sceneSetting;
    private static Parent rootSetting;
    public void ShowScene(){
        try {
            rootSetting = FXMLLoader.load(getClass().getResource("/fxml/Setting.fxml"));

        } catch (IOException e) {
            System.out.println("Ошибка подключения Setting.fxml или инстанс Scene");
        }

        sceneSetting = new Scene(rootSetting, 1280, 670);
        sceneSetting.getStylesheets().add("/styles/my.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneSetting);
    }


    public void Setting(){

    }
}
