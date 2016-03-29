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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by 4 on 02.03.2016.
 */
public class NewGame {

    final static int HEIGHT = 1280;
    final static int WIDTH = 680;

    private static Image backgroundImage1;
    private static Image backgroundImage2;
    private static Image backgroundImage3;
    private static Image MONEY_PNG = new Image("/images/money.png");
    private static Image BOX_PNG;
    private static Image smoke = new Image("/images/smoke.png");
    private static Image dollars = new Image("/images/money.png");

    private ImageView background1, background2,
            background3, moneyPng, boxPng, smokeDownLeft, smokeDownRight,moneyUp;

    private Scene sceneNewGame;
    private static Group root;
    private Timeline timeline, timelineLeftMove, timelineRightMove, timelineSmoke,timelineMoneyUp, timelinePhysicLeft,timelineResetRotateCar,timelinePhysicRight;
    private KeyFrame keyFrame;
    private KeyCode[] controll = new KeyCode[4];//массив в котором коды клавиш, которые используются для управления
    private Label labelMoney, labelDistance;
    private Car currentCar;
    private Gauge radial;

    double offsetY = 1, speedCar = 5,
            maxSpeedCar, money, distance, currentMaxSpeed;

    int countPhysicLeft,countPhysicRight;

    boolean up, left, right, down;

    public void showScene() {
        up = false;
        left = false;
        right = false;
        down = false;
        MainApp.music.stopMenu();
        MainApp.music.playNewGame();
        countPhysicLeft=0;
        countPhysicRight=0;
        money = 0;
        distance = 0;
        currentCar = Store.getCar();
        maxSpeedCar = currentCar.getMaxSpeed();

        labelMoney = new Label("MONEY");
        labelMoney.setFont(Font.font("AVENTURA"));
        labelMoney.setTextFill(Color.GOLD);

        labelDistance = new Label("DISTANCE");
        labelDistance.setFont(Font.font("AVENTURA"));
        labelDistance.setTextFill(Color.PINK);

        identifyControll();//определяем управление
        identifyTheme();//определяем тему

        //спидометр
        radial = GaugeBuilder.create()
                .skinType(Gauge.SkinType.MODERN)
                .minValue(0)
                .maxValue(300)
                .maxWidth(150)
                .maxHeight(150)
                .value(Math.pow(speedCar, -1) * 120)
                .build();
        currentMaxSpeed = radial.getValue();

        background1 = new ImageView(backgroundImage1);
        background2 = new ImageView(backgroundImage2);
        background3 = new ImageView(backgroundImage3);
        moneyPng = new ImageView(MONEY_PNG);
        boxPng = new ImageView(BOX_PNG);
        smokeDownLeft = new ImageView(smoke);
        smokeDownRight = new ImageView(smoke);
        moneyUp = new ImageView(dollars);

        ImageView car = new ImageView(new Image(currentCar.getImgAbove()));

        root = new Group();
        root.getChildren().add(0, background1);
        root.getChildren().get(0).setLayoutY(0);
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
        randomMoney(root.getChildren().get(6), root.getChildren().get(5));
        randomBox(root.getChildren().get(6), root.getChildren().get(5));

        root.getChildren().add(7, radial);
        root.getChildren().get(7).setLayoutX(20);
        root.getChildren().get(7).setLayoutY(510);

        root.getChildren().add(8, labelDistance);
        root.getChildren().get(8).setLayoutX(10);
        root.getChildren().get(8).setLayoutY(30);

        root.getChildren().add(9, smokeDownLeft);
        root.getChildren().get(9).setLayoutX(590);
        root.getChildren().get(9).setLayoutY(600);
        root.getChildren().get(9).setOpacity(0);

        root.getChildren().add(10, smokeDownRight);
        root.getChildren().get(10).setLayoutX(510);
        root.getChildren().get(10).setLayoutY(600);
        root.getChildren().get(10).setOpacity(0);

        root.getChildren().add(11, moneyUp);
        root.getChildren().get(11).setLayoutX(10);
        root.getChildren().get(11).setLayoutY(10);
        root.getChildren().get(11).setOpacity(0);

        sceneNewGame = new Scene(root, HEIGHT, WIDTH);
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneNewGame);


        sceneNewGame.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == controll[0]) {
                    root.getChildren().get(9).setOpacity(0);
                    root.getChildren().get(10).setOpacity(0);
                    up = false;
                }
                if (event.getCode() == controll[1]) {
                    countPhysicLeft=0;
                    timelinePhysicLeft.play();
                    timelineLeftMove.stop();
                    root.getChildren().get(3).setRotate(0);
                    left = false;
                }
                if (event.getCode() == controll[2]) {
                    down = false;
                }
                if (event.getCode() == controll[3]) {
                    countPhysicRight=0;
                    timelinePhysicRight.play();
                    timelineRightMove.stop();
                    root.getChildren().get(3).setRotate(0);
                    right = false;
                }
            }
        });
        timelineRightMove = new Timeline(new KeyFrame(
                Duration.millis(2),
                ae -> right()));
        timelineRightMove.setCycleCount(Animation.INDEFINITE);

        timelineLeftMove = new Timeline(new KeyFrame(
                Duration.millis(2),
                ae -> left()));
        timelineLeftMove.setCycleCount(Animation.INDEFINITE);

        timelineSmoke = new Timeline(new KeyFrame(
                Duration.millis(3),
                ae -> smokeDowns()));
        timelineSmoke.setCycleCount(Animation.INDEFINITE);

        timelineMoneyUp = new Timeline(new KeyFrame(
                Duration.millis(3),
                ae -> dollarsUp()));
        timelineMoneyUp.setCycleCount(Animation.INDEFINITE);

        timelinePhysicLeft = new Timeline(new KeyFrame(
                Duration.millis(3),
                ae -> physicLeft()));
        timelinePhysicLeft.setCycleCount(Animation.INDEFINITE);

        timelinePhysicRight = new Timeline(new KeyFrame(
                Duration.millis(3),
                ae -> physicRight()));
        timelinePhysicRight.setCycleCount(Animation.INDEFINITE);





        sceneNewGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                Node currentNode = root.getChildren().get(3);
                if (event.getCode() == KeyCode.ESCAPE) {
                    MainApp.music.pauseNewGame();
                    MainApp.music.playMenu();
                    writeInStatistic(currentMaxSpeed, distance, money);
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
                    timelineLeftMove.stop();
                    timelineRightMove.stop();
                    timelineSmoke.stop();
                    timelineMoneyUp.stop();
                    timelinePhysicRight.stop();
                    timelinePhysicLeft.stop();

                    up = false;
                    left = false;
                    right = false;
                    down = false;
                } else {
                    if (event.getCode() == controll[0]) up = true;
                    if (event.getCode() == controll[1]) left = true;
                    if (event.getCode() == controll[2]) down = true;
                    if (event.getCode() == controll[3]) right = true;

                    if (left) {
                        timelineLeftMove.play();
                    }
                    if (right) {
                        timelineRightMove.play();
                    }
                    if (up) {
                        if (speedCar > maxSpeedCar) {
                            timelineSmoke.play();
                            resetTimer(timeline, speedCar);
                            speedCar -= 0.1;
                            smokeReset();
                        }
                    }
                    if (down) {
                        if (speedCar < 5) {
                            resetTimer(timeline, speedCar);
                            speedCar += 0.1;
                        }
                    }
                    radial.setValue(Math.pow(speedCar, -1) * 120);
                    if (radial.getValue() > currentMaxSpeed) {
                        currentMaxSpeed = radial.getValue();
                    }

                    if ((event.getCode()==KeyCode.SPACE) && (up)){
                        if (speedCar > maxSpeedCar) {
                            resetTimer(timeline, speedCar);
                            speedCar -= 0.2;
                        }
                    }
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
        keyFrame = new KeyFrame(Duration.millis(timerInterval), ae -> moveBackground());
        timer.stop();
        timer.getKeyFrames().setAll(keyFrame);
        timer.play();
    }

    //меняем слои, выбираем из 3 слуйчайны и подставляем + двигаем все слои
    public void moveBackground() {
        if (root.getChildren().get(0).getLayoutY() == 0) {
            distance += 5;
            int randomChildren = (int) (Math.random() * 2) + 1;// второй(1) или третий(2) слой берем
            root.getChildren().get(randomChildren).setLayoutY(-720);
        } else {
            if (root.getChildren().get(1).getLayoutY() == 0) {
                distance += 5;
                int range = (int) (Math.random() * 2);
                int randomChildren = (range == 0) ? 0 : 2;//первый(0) или третий(2) слой берем
                root.getChildren().get(randomChildren).setLayoutY(-720);
            } else {
                if (root.getChildren().get(2).getLayoutY() == 0) {
                    distance += 5;
                    int randomChildren = (int) (Math.random() * 2);//первый(0) или второй(1) слой берем
                    root.getChildren().get(randomChildren).setLayoutY(-720);
                }
            }
        }
        labelDistance.setText("DISTANCE " + (int) (distance));


        Node nodeCar = root.getChildren().get(3);

        //box
        double[] pointsBox = new double[]{root.getChildren().get(5).getLayoutX(), root.getChildren().get(5).getLayoutY(),
                root.getChildren().get(5).getLayoutX(), root.getChildren().get(5).getLayoutY() + 80,
                root.getChildren().get(5).getLayoutX() + 80, root.getChildren().get(5).getLayoutY(),
                root.getChildren().get(5).getLayoutX() + 80, root.getChildren().get(5).getLayoutY() + 80};
        for (int i = 0; i < pointsBox.length; i += 2) {
            if (pointsBox[i] > nodeCar.getLayoutX() && pointsBox[i] < nodeCar.getLayoutX() + currentCar.getWidth() &&
                    pointsBox[i + 1] > nodeCar.getLayoutY() && pointsBox[i + 1] < nodeCar.getLayoutY() + currentCar.getHeight()) {

                for (int j = 0; j < controll.length; j++) {
                    controll[j] = null;
                }
                up = false;
                left = false;
                down = false;
                right = false;
                timelineRightMove.stop();
                timelineLeftMove.stop();

                Store.setMoney(money);

                timeline.stop();
                Label labelEnd = new Label("END GAME");
                labelEnd.setFont(Font.font("AVENTURA", 55));
                labelEnd.setTextFill(Color.RED);
                root.getChildren().add(9, labelEnd);
                root.getChildren().get(9).setLayoutX(500);
                root.getChildren().get(9).setLayoutY(200);

                break;
            }
        }
        if (root.getChildren().get(5).getLayoutY() > 680) {
            randomBox(root.getChildren().get(6), root.getChildren().get(5));
        }

        //money
        double[] pointsMoney = new double[]{root.getChildren().get(6).getLayoutX(), root.getChildren().get(6).getLayoutY(),
                root.getChildren().get(6).getLayoutX(), root.getChildren().get(6).getLayoutY() + 25,
                root.getChildren().get(6).getLayoutX() + 75, root.getChildren().get(6).getLayoutY(),
                root.getChildren().get(6).getLayoutX() + 75, root.getChildren().get(6).getLayoutY() + 25};
        for (int i = 0; i < pointsMoney.length; i += 2) {
            if (pointsMoney[i] > nodeCar.getLayoutX() && pointsMoney[i] < nodeCar.getLayoutX() + currentCar.getWidth() &&
                    pointsMoney[i + 1] > nodeCar.getLayoutY() && pointsMoney[i + 1] < nodeCar.getLayoutY() + currentCar.getHeight()) {
                randomMoney(root.getChildren().get(6), root.getChildren().get(5));
                money += 100;
                dollarsReset();
                timelineMoneyUp.play();
                break;
            }
        }
        if (root.getChildren().get(6).getLayoutY() > 680) {
            randomMoney(root.getChildren().get(6), root.getChildren().get(5));
        }

        root.getChildren().get(0).setLayoutY((root.getChildren().get(0).getLayoutY() + offsetY));
        root.getChildren().get(1).setLayoutY((root.getChildren().get(1).getLayoutY() + offsetY));
        root.getChildren().get(2).setLayoutY((root.getChildren().get(2).getLayoutY() + offsetY));
        root.getChildren().get(5).setLayoutY((root.getChildren().get(5).getLayoutY() + offsetY));
        root.getChildren().get(6).setLayoutY((root.getChildren().get(6).getLayoutY() + offsetY));

        labelMoney.setText("MONEY " + (int) (money));
    }

    //получаем вид управления, который выбран в игре в setting
    public void identifyControll() {
        if (SettingController.getControll().equals("up") || SettingController.getControll().equals("")) {
            controll[0] = KeyCode.W;
            controll[1] = KeyCode.A;
            controll[2] = KeyCode.S;
            controll[3] = KeyCode.D;
        } else {
            controll[0] = KeyCode.UP;
            controll[1] = KeyCode.LEFT;
            controll[2] = KeyCode.DOWN;
            controll[3] = KeyCode.RIGHT;
        }
    }

    //получаем тему(зима/лето)
    public void identifyTheme(){
        if(SettingController.getWeather().equals("summer") || SettingController.getWeather().equals("")){
            backgroundImage1 = new Image("/images/road1.jpg");
            backgroundImage2 = new Image("/images/road2.jpg");
            backgroundImage3 = new Image("/images/road3.jpg");
            BOX_PNG = new Image("/images/box.png");
        }else{
            backgroundImage1 = new Image("/images/background1.jpg");
            backgroundImage2 = new Image("/images/background2.jpg");
            backgroundImage3 = new Image("/images/background3.jpg");
            BOX_PNG = new Image("/images/snowBox.png");
        }
    }

    private static void randomMoney(Node money, Node box) {
        money.setLayoutX(0);
        money.setLayoutY(700);
        if ((int) (Math.random() * 500) == 1) {
            int x = (int) (442 + Math.random() * 315);
            while (x > box.getLayoutX() && x < box.getLayoutX() + 80 || x + 70 > box.getLayoutX() && x + 70 < box.getLayoutX() + 80) {
                x = (int) (442 + Math.random() * 315);
            }
            System.out.println("Рандом мани +++++ " + x);
            money.setLayoutX(x);
            money.setLayoutY(-25);
        }
    }

    private static void randomBox(Node money, Node box) {
        box.setLayoutX(100);
        box.setLayoutY(800);
        if ((int) (Math.random() * 500) == 1) {
            int x = (int) (442 + Math.random() * 315);
            while (x > money.getLayoutX() && x < money.getLayoutX() + 70 || x + 80 > money.getLayoutX() && x + 80 < money.getLayoutX() + 70) {
                x = (int) (442 + Math.random() * 315);
            }
            System.out.println("Рандом бокс |_|_| " + x);
            box.setLayoutX(x);
            box.setLayoutY(-80);
        }

    }

    public static void right() {
        Node node = root.getChildren().get(3);
        if (node.getLayoutX() < 750) {
            node.setLayoutX(node.getLayoutX() + 1);
            if (node.getRotate() < 11) {
                node.setRotate(node.getRotate() + 0.06);
            }
        }

    }

    public static void left() {
        Node node = root.getChildren().get(3);
        if (node.getLayoutX() > 440) {
            node.setLayoutX(node.getLayoutX() - 1);
            if (node.getRotate() > -11) {
                node.setRotate(node.getRotate() - 0.06);
            }
        }
    }

    private static void writeInStatistic(double speed, double distance, double money) {
        File fileStatistic = new File("./src/main/resources/files/statistic");
        try {
            Scanner scan = new Scanner(fileStatistic);
            double speedInFile = Double.parseDouble(scan.nextLine());
            double distanceInFile = Double.parseDouble(scan.nextLine());
            double moneyInFile = Double.parseDouble(scan.nextLine());
            scan.close();
            moneyInFile += money;
            distanceInFile += distance;
            if (speed > speedInFile) {
                speedInFile = speed;
            }
            PrintWriter out = new PrintWriter(fileStatistic);
            out.write(String.valueOf((int) speedInFile) + "\n");
            out.write(String.valueOf((int) distanceInFile) + "\n");
            out.write(String.valueOf((int) moneyInFile));
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    void smokeDowns() {
        Node nodeLeft=root.getChildren().get(9);
        Node nodeRight=root.getChildren().get(10);

        nodeLeft.setOpacity(nodeLeft.getOpacity()+0.002);
        nodeRight.setOpacity(nodeRight.getOpacity()+0.002);
        nodeRight.setLayoutY(nodeRight.getLayoutY()+0.4);
        nodeLeft.setLayoutY(nodeLeft.getLayoutY()+0.4);
    }

    void smokeReset(){
        Node nodeLeft=root.getChildren().get(9);
        Node nodeRight=root.getChildren().get(10);

        nodeLeft.setLayoutY(root.getChildren().get(3).getLayoutY()+165);
        nodeLeft.setLayoutX(root.getChildren().get(3).getLayoutX()+50);

        nodeRight.setLayoutY(root.getChildren().get(3).getLayoutY()+165);
        nodeRight.setLayoutX(root.getChildren().get(3).getLayoutX()-30);
    }

    void dollarsUp(){
        Node money=root.getChildren().get(11);
        Node car=root.getChildren().get(3);
        if (car.getLayoutX()>600){
            money.setLayoutX(money.getLayoutX()-2);
            money.setLayoutY(money.getLayoutY()-1);
        } else {
            money.setLayoutX(money.getLayoutX()-1);
            money.setLayoutY(money.getLayoutY()-1);
        }

        money.setRotate(money.getRotate()+0.35);
        money.setOpacity(money.getOpacity()-0.003);

    }

    void dollarsReset(){
        timelineMoneyUp.stop();
        Node car=root.getChildren().get(3);
        Node money=root.getChildren().get(11);
        money.setOpacity(1);
        money.setLayoutX(car.getLayoutX()-30);
        money.setLayoutY(car.getLayoutY()-50);
        money.setRotate(0);
    }

    void physicLeft(){
        Node node = root.getChildren().get(3);
        countPhysicLeft++;
        if ((node.getLayoutX() > 440) && (countPhysicLeft!=70)) {
            node.setLayoutX(node.getLayoutX()-0.6);
        }
        if (countPhysicLeft==70){
            timelinePhysicLeft.stop();
        }

    }

    void physicRight(){
        Node node = root.getChildren().get(3);
        countPhysicRight++;
        if ((node.getLayoutX() < 750) && (countPhysicRight!=70)) {
            node.setLayoutX(node.getLayoutX()+0.6);
        }
        if (countPhysicRight==70){
            timelinePhysicRight.stop();
        }
    }

}
