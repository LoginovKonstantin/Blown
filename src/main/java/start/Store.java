package start;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 4 on 02.03.2016.
 */
public class Store {
    private static Scene sceneStore;
    private static Parent rootStore;

    public static ArrayList<Car> Cars = new ArrayList<Car>();

    public void showScene() {
//        Cars.add(new Car("SENTIEL", "./resources/images/sentiel.png", 20, 250, 15000, 73, 163, false, false));
//        Cars.add(new Car("SABRE", "./resources/images/sabre.png", 5, 130, 3000, 76, 159, false, false));
//        Cars.add(new Car("BANHEE", "./resources/images/banhee.png", 10, 180, 6000, 77, 174, false, false));
        try {
            rootStore = FXMLLoader.load(getClass().getResource("/fxml/store.fxml"));

        } catch (IOException e) {
            System.out.println("Ошибка подключения Store.fxml !!");
        }

        sceneStore = new Scene(rootStore, 1290, 680);
        sceneStore.getStylesheets().add("/styles/store.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneStore);

        sceneStore.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
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
