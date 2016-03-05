package start;

import controllers.StoreController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import objects.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 4 on 02.03.2016.
 */
public class Store {
    private static Scene sceneStore;
    private static Parent rootStore;
    private static int countCars=0;
    private static ArrayList<Car> Cars = new ArrayList<Car>();

    public void showScene() {
        //тест методов.. вызов parseCars(); перед реврайтом обязательно, ибо листэрей будет не заполнен
//        parseCars();
//        Cars.get(0).setBuy(true);
//        rewriteFile();
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

//    считывание из файла всех машин + создание объектов
    public static ArrayList<Car> parseCars(){
        countCars=0;
        File file = new File("./src/main/resources/files/store");
        try {
            Scanner out = new Scanner(file);
            String[] car;
            while (out.hasNextLine()){
                countCars++;
                car=out.nextLine().split(";");
                Cars.add(new Car(car[0],car[1],car[2],(Double.parseDouble(car[3])),
                        (Double.parseDouble(car[4])),(Double.parseDouble(car[5])),(Double.parseDouble(car[6])),
                        (Boolean.parseBoolean(car[7])),(Boolean.parseBoolean(car[8]))));
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
        return Cars;
    }

//      перезапись файла Store !
        public static void rewriteFile(){
            int i=0;
            Car car;
            File f = new File("./src/main/resources/files/store");
            try {
                PrintWriter writer = new PrintWriter(f);
                while (i!=countCars){
                    car=Cars.get(i);
                    writer.write(car.getName() + ';' + car.getImgSide()+
                            ';'+car.getImgAbove()+';'+car.getMaxSpeed()+';'+car.getWidth()+
                            ';'+car.getHeight()+';'+car.getPrice()+';'+car.getBuy()+
                            ';'+car.getSelected()+';'+'\n');
                    i++;
                }
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("проблемы при записи в файл" + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
