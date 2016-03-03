package start;


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

    double y = 1;

    Timer timerNewGame;

    public void showScene(){
        timerNewGame = new Timer(100, this);
        timerNewGame.start();

        background1 = new ImageView(BACKGROUND_IMAGE1);
        background2 = new ImageView(BACKGROUND_IMAGE2);
        background3 = new ImageView(BACKGROUND_IMAGE3);
        root = new Group(background1);
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

    public void actionPerformed(ActionEvent e) {
        if(root.getLayoutY() == 680){
            root.setClip(background2);
//            Random r = new Random();
//            int image = r.nextInt(3);
//            if(image == 1){
//
//                root.setClip(background1);
//                root.setLayoutY(y+HEIGHT);
//            }else{
//                if(image == 2){
//                    root.setClip(background2);
//                    root.setLayoutY(y+HEIGHT);
//                }else{
//                    root.setClip(background3);
//                    root.setLayoutY(y+HEIGHT);
//                }
//            }
        }

        root.setLayoutY(y);
        y+=5;
        System.out.println(root.getLayoutY());

    }
}
