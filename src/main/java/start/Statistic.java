package start;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/**
 * Created by Егор on 29.02.2016.
 */
public class Statistic {
    private static Scene sceneStatistic;
    private static Parent rootStatistic;

    public void Statistic(){

    }

    public void ShowScene(){
        try {
            rootStatistic = FXMLLoader.load(getClass().getResource("/fxml/Statistic.fxml"));

        } catch (IOException e) {
            System.out.println("Ошибка подключения Statistic.fxml");
        }

        sceneStatistic = new Scene(rootStatistic, 1290, 680);
        sceneStatistic.getStylesheets().add("/styles/statistic.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneStatistic);

        sceneStatistic.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
