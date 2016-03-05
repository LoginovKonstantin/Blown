package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import start.Store;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by 4 on 02.03.2016.
 */
public class StoreController {
    @FXML
    private Label dollars = null;
    @FXML
    private  ImageView placeCars;
    private  Image currentCar =new Image ("/images/carPrioraSide.png");
    public   int positionCar = 0;

    @FXML
    private void initialize(){
        currentCar = new Image("/images/carPrioraSide.png");
        placeCars.setImage(currentCar);
        placeCars.setLayoutY(430);
        placeCars.setLayoutX(530);
        dollars.setText(Double.toString(Store.getMoney()));
    }
//
//    public static void changeCar(){
//        Store.parseCars();
//        if (positionCar<9) {
//            currentCar = new Image(Store.parseCars().get(positionCar).getImgSide());
//            placeCars.setImage(currentCar);
//        }
//        placeCars.setLayoutY(430);
//        placeCars.setLayoutX(530);
//        positionCar++;
//    }

}
