package start;


import controllers.SettingController;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import objects.Car;

import java.io.IOException;

/**
 * Created by 4 on 02.03.2016.
 */
public class NewGame{

    final static int HEIGHT = 1280;
    final static int WIDTH = 680;

    final static Image BACKGROUND_IMAGE1 = new Image("/images/road1.jpg");
    final static Image BACKGROUND_IMAGE2 = new Image("/images/road2.jpg");
    final static Image BACKGROUND_IMAGE3 = new Image("/images/road3.jpg");
    private static Image MONEY_PNG = new Image("/images/money.png");
    private static Image BOX_PNG = new Image("/images/box.png");

    private ImageView background1;
    private ImageView background2;
    private ImageView background3;
    private ImageView moneyPng;
    private ImageView boxPng;

    private Scene sceneNewGame;
    private Group root;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private KeyCode[] controll = new KeyCode[4];//массив в котором коды клавиш, которые используются для управления
    private Label labelMoney;
    private Car currentCar;
    private Gauge radial;

    double offsetY = 1;//смещение по Y
    double offsetCarY = 5;
    double offsetCarX = 7;
    double speedCar = 5;
    double maxSpeedCar;
    double money;

    boolean up = false, left = false, right = false, down = false;

    public void showScene(){

        money = 0;
        currentCar = Store.getCar();
        maxSpeedCar = currentCar.getMaxSpeed();

        labelMoney = new Label("MONEY");
        labelMoney.setFont(Font.font("AVENTURA"));
        labelMoney.setTextFill(Color.RED);

        identifyControll();//определяем управление

        //спидометр
        radial = GaugeBuilder.create()
                .skinType(Gauge.SkinType.MODERN)
                .minValue(0)
                .maxValue(300)
                .maxWidth(150)
                .maxHeight(150)
                .value(Math.pow(speedCar, -1) * 120)
                .build();

        background1 = new ImageView(BACKGROUND_IMAGE1);
        background2 = new ImageView(BACKGROUND_IMAGE2);
        background3 = new ImageView(BACKGROUND_IMAGE3);
        moneyPng = new ImageView(MONEY_PNG);
        boxPng = new ImageView(BOX_PNG);

        ImageView car = new ImageView(new Image(currentCar.getImgAbove()));

        root = new Group();
        root.getChildren().add(0, background1); root.getChildren().get(0).setLayoutY(0);
        root.getChildren().add(1, background2);
        root.getChildren().add(2, background3);

        root.getChildren().add(3, car);
        root.getChildren().get(3).setLayoutY(470);
        root.getChildren().get(3).setLayoutX(540);

        root.getChildren().add(4, labelMoney);
        root.getChildren().get(4).setLayoutX(10);
        root.getChildren().get(4).setLayoutY(10);

        root.getChildren().add(5, boxPng);
        root.getChildren().add(6, moneyPng);
        randomMoney(root.getChildren().get(6));
        randomBox(root.getChildren().get(6), root.getChildren().get(5));

        root.getChildren().add(7, radial);
        root.getChildren().get(7).setLayoutX(20);
        root.getChildren().get(7).setLayoutY(510);

        sceneNewGame = new Scene(root, HEIGHT, WIDTH);
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneNewGame);


        sceneNewGame.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == controll[0]){
                    up = false;
                }
                if(event.getCode() == controll[1]){
                    left = false;
                }
                if(event.getCode() == controll[2]){
                    down = false;
                }
                if(event.getCode() == controll[3]){
                    right = false;
                }
            }
        });

        sceneNewGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                Node currentNode = root.getChildren().get(3);
                if(event.getCode()== KeyCode.ESCAPE){
                    Store.setMoney(money);
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
                    timeline.stop();
                }else{
                    if(event.getCode() == controll[0]) up = true;
                    if(event.getCode() == controll[1]) left = true;
                    if(event.getCode() == controll[2]) down = true;
                    if(event.getCode() == controll[3]) right = true;

                    if(left){
                        if (currentNode.getLayoutX() > 445) {
                            currentNode.setLayoutX(currentNode.getLayoutX() - offsetCarX);
                        }
                    }
                    if(right){
                        if((currentNode.getLayoutX()) < 750){
                            currentNode.setLayoutX(currentNode.getLayoutX() + offsetCarX);
                        }
                    }
                    if(up){
                        if (speedCar > maxSpeedCar) {
                            resetTimer(timeline, speedCar);
                            speedCar -= 0.1;
                        }
                        if(currentNode.getLayoutY() > 320){
                            currentNode.setLayoutY(currentNode.getLayoutY() - offsetCarY);
                        }
                    }
                    if(down){
                        if (speedCar < 5) {
                            resetTimer(timeline, speedCar);
                            speedCar += 0.1;
                        }
                        if(currentNode.getLayoutY() < 480){
                            currentNode.setLayoutY(currentNode.getLayoutY() + offsetCarY);
                        }
                    }
                    radial.setValue(Math.pow(speedCar, -1) * 120);
                }
            }
        });

        keyFrame = new KeyFrame(Duration.millis(speedCar), ae -> moveBackground());
        timeline = new Timeline(new KeyFrame(Duration.millis(speedCar), ae -> moveBackground()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //для изменения времени, обновление таймера
    private void resetTimer(Timeline timer, double timerInterval) {
        keyFrame = new KeyFrame(Duration.millis(timerInterval),ae -> moveBackground());
        timer.stop();
        timer.getKeyFrames().setAll(keyFrame);
        timer.play();
    }

    //меняем слои, выбираем из 3 слуйчайны и подставляем + двигаем все слои
    public void moveBackground(){
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

        Node nodeCar = root.getChildren().get(3);

        //box
        double [] pointsBox = new double[]{root.getChildren().get(5).getLayoutX(), root.getChildren().get(5).getLayoutY(),
                root.getChildren().get(5).getLayoutX(), root.getChildren().get(5).getLayoutY() + 80,
                root.getChildren().get(5).getLayoutX() + 80, root.getChildren().get(5).getLayoutY(),
                root.getChildren().get(5).getLayoutX() + 80, root.getChildren().get(5).getLayoutY() + 80};
        for(int i = 0; i < pointsBox.length; i += 2){
            if(pointsBox[i] > nodeCar.getLayoutX() && pointsBox[i] < nodeCar.getLayoutX() + currentCar.getWidth() &&
                    pointsBox[i + 1] > nodeCar.getLayoutY() && pointsBox[i + 1] < nodeCar.getLayoutY() + currentCar.getHeight()){
                Store.setMoney(money);


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
                timeline.stop();
                break;
            }
        }
        if(root.getChildren().get(5).getLayoutY() > 680){
            randomBox(root.getChildren().get(6), root.getChildren().get(5));
        }

        //money
        double [] pointsMoney = new double[]{root.getChildren().get(6).getLayoutX(), root.getChildren().get(6).getLayoutY(),
                root.getChildren().get(6).getLayoutX(), root.getChildren().get(6).getLayoutY() + 25,
                root.getChildren().get(6).getLayoutX() + 75, root.getChildren().get(6).getLayoutY(),
                root.getChildren().get(6).getLayoutX() + 75, root.getChildren().get(6).getLayoutY() + 25};
        for(int i = 0; i < pointsMoney.length; i += 2){
            if(pointsMoney[i] > nodeCar.getLayoutX() && pointsMoney[i] < nodeCar.getLayoutX() + currentCar.getWidth() &&
                    pointsMoney[i + 1] > nodeCar.getLayoutY() && pointsMoney[i + 1] < nodeCar.getLayoutY() + currentCar.getHeight()){
                randomMoney(root.getChildren().get(6));
                money += 10;
                break;
            }
        }
        if(root.getChildren().get(6).getLayoutY() > 680){
            randomMoney(root.getChildren().get(6));
        }

        root.getChildren().get(0).setLayoutY((root.getChildren().get(0).getLayoutY() + offsetY));
        root.getChildren().get(1).setLayoutY((root.getChildren().get(1).getLayoutY() + offsetY));
        root.getChildren().get(2).setLayoutY((root.getChildren().get(2).getLayoutY() + offsetY));
        root.getChildren().get(5).setLayoutY((root.getChildren().get(5).getLayoutY() + offsetY));
        root.getChildren().get(6).setLayoutY((root.getChildren().get(6).getLayoutY() + offsetY));

        labelMoney.setText("MONEY " + (money));
    }

    //получаем вид управления, который выбран в игре в setting
    public void identifyControll(){
        if(SettingController.controll.equals("up") || SettingController.controll.equals("")){
            controll[0] = KeyCode.W; controll[1] = KeyCode.A;
            controll[2] = KeyCode.S; controll[3] = KeyCode.D;
        }else{
            controll[0] = KeyCode.UP; controll[1] = KeyCode.LEFT;
            controll[2] = KeyCode.DOWN; controll[3] = KeyCode.RIGHT;
        }
    }

    private static void randomMoney(Node money){
        money.setLayoutX(0);money.setLayoutY(700);
        if((int)(Math.random() * 500) == 1){
            money.setLayoutX((int)(442 + Math.random() * 315)) ;
            money.setLayoutY(0);
        }
    }
    private static void randomBox(Node money, Node box){
        box.setLayoutX(100);box.setLayoutY(800);
        if((int)(Math.random() * 500) == 1){
            int x = (int)(442 + Math.random() * 315);
            while(x + 70 > money.getLayoutX() && x + 80 < money.getLayoutX()){
                x = (int)(442 + Math.random() * 315);
            }
            box.setLayoutX(x);box.setLayoutY(0);
        }

    }

}
