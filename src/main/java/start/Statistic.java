package start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Created by Егор on 29.02.2016.
 */
public class Statistic {
    private static Scene sceneStatistic;
    private static Parent rootStatistic;
    public void ShowScene(){
        try {
            rootStatistic = FXMLLoader.load(getClass().getResource("/fxml/Statistic.fxml"));

        } catch (IOException e) {
            System.out.println("Ошибка подключения Statistic.fxml");
        }

        sceneStatistic = new Scene(rootStatistic, 1280, 670);
        sceneStatistic.getStylesheets().add("/styles/statistic.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneStatistic);
    }


    public void Statistic(){

    }
}
