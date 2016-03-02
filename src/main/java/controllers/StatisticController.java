package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Егор on 29.02.2016.
 */
public class StatisticController {
    @FXML
        private Label speed;
    @FXML
        private Label distance;
    @FXML
        private Label money;

        public static String maxSpeed;
        public static String maxDistance;
        public static String maxMoney;

    @FXML
    private void initialize(){
        File file = new File("./src/main/resources/files/statistic");
        try {
            Scanner out = new Scanner(file);
            maxSpeed = out.nextLine();
            maxDistance = out.nextLine();
            maxMoney= out.nextLine();
            out.close();
            speed.setText( maxSpeed + " KM/H " );
            distance.setText( maxDistance + " KM " );
            money.setText( maxMoney + " $ " );
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
