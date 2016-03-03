package start;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by 4 on 02.03.2016.
 */
public class NewGame implements ActionListener{
    final static int HEIGHT = 1280;
    final static int WIDTH = 680;

    final static Image BACKGROUND_IMAGE1 = new Image("/images/road1.jpg");
    final static Image BACKGROUND_IMAGE2 = new Image("/images/road2.jpg");
    final static Image BACKGROUND_IMAGE3 = new Image("/images/road3.jpg");

    private ImageView background1;
    private ImageView background2;
    private ImageView background3;

    private Scene sceneNewGame;
    private Group root;
    private Timer timerNewGame;

    double offsetY = 1;

    public void showScene(){
        timerNewGame = new Timer(1, this);
        timerNewGame.start();

        background1 = new ImageView(BACKGROUND_IMAGE1);
        background2 = new ImageView(BACKGROUND_IMAGE2);
        background3 = new ImageView(BACKGROUND_IMAGE3);

        root = new Group();
        root.getChildren().add(0, background1); root.getChildren().get(0).setLayoutY(0);
        root.getChildren().add(1, background2);
        root.getChildren().add(2, background3);

        sceneNewGame = new Scene(root, HEIGHT, WIDTH);

        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneNewGame);

        sceneNewGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                    timerNewGame.stop();
                }
            }
        });

    }

    //WARNING при большом количестве вызовов, вылезает куча исключений(((9 ПЕЧАЛИТИ
    public void actionPerformed(ActionEvent e) {
        if(root.getChildren().get(0).getLayoutY() == 0){
            int randomChildren = (int)(Math.random() * 2) + 1;// второй(1) или третий(2) слой берем
            root.getChildren().get(randomChildren).setLayoutY(-720);
        }else{
            if(root.getChildren().get(1).getLayoutY() == 0){
                int range = (int)(Math.random() * 2);
                int randomChildren = (range == 0) ? 0 : 2;//первый(0) или третий(2) слой берем
                root.getChildren().get(randomChildren).setLayoutY(-720);
            }else{
                if(root.getChildren().get(2).getLayoutY() == 0){
                    int randomChildren = (int)(Math.random() * 2);//первый(0) или второй(1) слой берем
                    root.getChildren().get(randomChildren).setLayoutY(-720);
                }
            }
        }
        root.getChildren().get(0).setLayoutY((root.getChildren().get(0).getLayoutY() + offsetY));
        root.getChildren().get(1).setLayoutY((root.getChildren().get(1).getLayoutY() + offsetY));
        root.getChildren().get(2).setLayoutY((root.getChildren().get(2).getLayoutY() + offsetY));

    }

}
