package start;

import controllers.StoreController;
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
import objects.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 4 on 02.03.2016.
 */
public class Store {
    final static int WIDTH = 1280;
    final static int HEIGHT = 680;

    final static Image BACKGROUND = new Image("/images/store.jpg");
    private ImageView left;
    private ImageView right;
    private ImageView close;
    private ImageView background;
    private ImageView car;
    private ImageView drive;

    private Group root;

    private Label lbMoney;
    private Label outMoney;
    private Label price;
    private Label maxSpeed;
    private Label nameCar;
    private Label outPrice;

    private int currentPositionCar;

    private static Scene sceneStore;
    private static Parent rootStore;

    public static int countCars = 0;
    private static double money = 0;

    private static ArrayList<Car> Cars = new ArrayList<Car>();

    public void showScene() {
        root = new Group();

        background = new ImageView(BACKGROUND);
        left=new ImageView("/images/left.png");
        right=new ImageView("/images/right.png");
        close=new ImageView("/images/close.png");
        drive=new ImageView("/images/drive.png");

        lbMoney=new Label();
        outMoney=new Label();
        lbMoney.setText("MONEY");
        lbMoney.setTextFill(Color.GOLD);
        outMoney.setTextFill(Color.GOLD);
        outMoney.setText(Double.toString(getMoney()));
        price=new Label();
        price.setText("PRICE:");
        price.setTextFill(Color.GOLD);
        nameCar=new Label();
        nameCar.setTextFill(Color.GOLD);
        maxSpeed=new Label();
        maxSpeed.setTextFill(Color.GOLD);
        outPrice=new Label();
        outPrice.setTextFill(Color.GOLD);
        outPrice.setText(Double.toString(parseCars().get(currentPositionCar).getPrice()));

        root.getChildren().add(0, background);
        root.getChildren().get(0).setLayoutY(0);

        root.getChildren().add(1,lbMoney);
        root.getChildren().get(1).setLayoutY(10);
        root.getChildren().get(1).setLayoutX(10);

        root.getChildren().add(2,outMoney);
        root.getChildren().get(2).setLayoutX(70);
        root.getChildren().get(2).setLayoutY(10);

        root.getChildren().add(3,left);
        root.getChildren().get(3).setLayoutX(300);
        root.getChildren().get(3).setLayoutY(450);

        root.getChildren().add(4,right);
        root.getChildren().get(4).setLayoutX(922);
        root.getChildren().get(4).setLayoutY(450);


        car=new ImageView(getCar().getImgSide());
        root.getChildren().add(5,car);
        root.getChildren().get(5).setLayoutX(560);
        root.getChildren().get(5).setLayoutY(460);
        parseCars();
        checkCurrentCar();
        nameCar.setText(parseCars().get(currentPositionCar).getName());
        maxSpeed.setText("SPEED: "+(Math.round(Math.pow(parseCars().get(currentPositionCar).getMaxSpeed(), -1) * 120))+" KM/H");

        root.getChildren().add(6,price);
        root.getChildren().get(6).setLayoutY(400);
        root.getChildren().get(6).setLayoutX(400);
        root.getChildren().add(7,outPrice);
        root.getChildren().get(7).setLayoutY(400);
        root.getChildren().get(7).setLayoutX(470);

        root.getChildren().add(8,nameCar);
        root.getChildren().get(8).setLayoutY(430);
        root.getChildren().get(8).setLayoutX(620);

        root.getChildren().add(9,maxSpeed);
        root.getChildren().get(9).setLayoutY(400);
        root.getChildren().get(9).setLayoutX(770);

        root.getChildren().add(10,new ImageView("images/clear.png"));
        root.getChildren().get(10).setLayoutX(560);
        root.getChildren().get(10).setLayoutY(460);

        root.getChildren().add(11,new ImageView("images/drive.png"));
        root.getChildren().get(11).setLayoutX(400);
        root.getChildren().get(11).setLayoutY(430);


        try {
            rootStore = FXMLLoader.load(getClass().getResource("/fxml/store.fxml"));
        } catch (IOException e) {
            System.out.println("Ошибка подключения Store.fxml !!");
        }

        sceneStore = new Scene(root, WIDTH,HEIGHT);
        sceneStore.getStylesheets().add("/styles/store.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneStore);

        sceneStore.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode()==KeyCode.LEFT){
                    if (currentPositionCar>0){
                        root.getChildren().set(4,(new ImageView("images/right.png")));
                        root.getChildren().get(4).setLayoutX(922);
                        root.getChildren().get(4).setLayoutY(450);;

                        currentPositionCar--;
                        car=new ImageView(parseCars().get(currentPositionCar).getImgSide());
                        car.setLayoutY(460);
                        car.setLayoutX(560);
                        root.getChildren().set(5,car);
                        if ((parseCars().get(currentPositionCar).getPrice()>getMoney())
                                && !(parseCars().get(currentPositionCar).getBuy())){
                            outPrice.setTextFill(Color.RED);
                        } else {
                            outPrice.setTextFill(Color.GOLD);
                        }
                        outPrice.setText(Double.toString(parseCars().get(currentPositionCar).getPrice()));
                        nameCar.setText(parseCars().get(currentPositionCar).getName());
                        maxSpeed.setText("SPEED: "+(Math.round(Math.pow(parseCars().get(currentPositionCar).getMaxSpeed(), -1) * 120))+" KM/H");
                    }
                    if (currentPositionCar==0){
                        root.getChildren().set(3,(new ImageView("images/clear.png")));
                    }
                    checkBuy();
                    checkSelected();
                }
                if (event.getCode()==KeyCode.RIGHT){
                    if (currentPositionCar<8){
                        root.getChildren().set(3,(new ImageView("images/left.png")));
                        root.getChildren().get(3).setLayoutX(300);
                        root.getChildren().get(3).setLayoutY(450);

                        currentPositionCar++;
                        car=new ImageView(parseCars().get(currentPositionCar).getImgSide());
                        car.setLayoutY(460);
                        car.setLayoutX(560);
                        root.getChildren().set(5,car);
                        if ((parseCars().get(currentPositionCar).getPrice()>getMoney())
                                && !(parseCars().get(currentPositionCar).getBuy())){
                            outPrice.setTextFill(Color.RED);
                        } else {
                            outPrice.setTextFill(Color.GOLD);
                        }
                        outPrice.setText(Double.toString(parseCars().get(currentPositionCar).getPrice()));
                        nameCar.setText(parseCars().get(currentPositionCar).getName());
                        maxSpeed.setText("SPEED: "+(Math.round(Math.pow(parseCars().get(currentPositionCar).getMaxSpeed(), -1) * 120))+" KM/H");
                    }
                    if (currentPositionCar==8) {
                        root.getChildren().set(4,(new ImageView("images/clear.png")));
                    }
                    checkBuy();
                    checkSelected();
                }
                if (event.getCode()==KeyCode.ENTER){
                    if (parseCars().get(currentPositionCar).getBuy()) {
                        select(currentPositionCar);
                        rewriteStore();
                        checkSelected();
                    } else {
                        if (getMoney()>=parseCars().get(currentPositionCar).getPrice()){
                            buyCar(currentPositionCar,parseCars().get(currentPositionCar).getPrice());
                            rewriteStore();
                            rewriteMoney();
                            outMoney.setText(Double.toString(getMoney()));
                            checkBuy();
                            select(currentPositionCar);
                            checkSelected();
                        }
                    }
                }

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

    // считывание из файла всех машин + создание объектов
    public static ArrayList<Car> parseCars() {
        countCars = 0;
        File file = new File("./src/main/resources/files/store");
        try {
            Scanner out = new Scanner(file);
            String[] car;
            while (out.hasNextLine()) {
                countCars++;
                car = out.nextLine().split(";");
                Cars.add(new Car(car[0], car[1], car[2], (Double.parseDouble(car[3])),
                        (Double.parseDouble(car[4])), (Double.parseDouble(car[5])), (Double.parseDouble(car[6])),
                        (Boolean.parseBoolean(car[7])), (Boolean.parseBoolean(car[8]))));
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
        return Cars;
    }




    // перезапись файла Store !
    public static void rewriteStore() {
        int i = 0;
        Car car;
        File f = new File("./src/main/resources/files/store");
        try {
            PrintWriter writer = new PrintWriter(f);
            while (i != countCars) {
                car = Cars.get(i);
                writer.write(car.getName() + ';' + car.getImgSide() +
                        ';' + car.getImgAbove() + ';' + car.getMaxSpeed() + ';' + car.getWidth() +
                        ';' + car.getHeight() + ';' + car.getPrice() + ';' + car.getBuy() +
                        ';' + car.getSelected() + ';' + '\n');
                i++;
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("проблемы при записи в файл" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод достает из файла бабки!
    public static double getMoney() {
        File file = new File("./src/main/resources/files/money");
        try {
            Scanner out = new Scanner(file);
            while (out.hasNextLine()) {
                money = Double.parseDouble(out.nextLine());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
        return money;
    }

    //перезапись денег в файл
    public static void rewriteMoney() {
        try {
            File f = new File("./src/main/resources/files/money");
            PrintWriter writer = new PrintWriter(f);
            writer.write(Double.toString(money));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("проблемы при записи в файл" + e);
        }
    }

    //прибавка к деньгам после игры newgame
    public static void setMoney(double moneyAdd){
        money = getMoney()+moneyAdd;
        rewriteMoney();
    }
    //покупка машины ( вычет из баблишек +  )
    public static void buyCar(int pos,double price){
        Cars.get(pos).setBuy(true);
        money-=price;
    }

    //получение текуще-выбранной тачки!
    public static Car getCar() {
        String[] car;
        Car outCar;
        File file = new File("./src/main/resources/files/store");
        try {
            Scanner out = new Scanner(file);
            while (out.hasNextLine()) {
                car = out.nextLine().split(";");
                if ((Boolean.parseBoolean(car[7]) == true) && (Boolean.parseBoolean(car[8]) == true)) {
                    outCar = new Car(car[0], car[1], car[2], (Double.parseDouble(car[3])),
                            (Double.parseDouble(car[4])), (Double.parseDouble(car[5])), (Double.parseDouble(car[6])),
                            (Boolean.parseBoolean(car[7])), (Boolean.parseBoolean(car[8])));
                    return outCar;
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
        return (new Car("copeuka", "/images/carCopeukaSide.png", "/images/carCopeukaAbove.png", (Double.parseDouble("100.0"))
                , (Double.parseDouble("90.0")), (Double.parseDouble("190.0"))
                , (Double.parseDouble("50000.0")), (Boolean.parseBoolean("true")), (Boolean.parseBoolean("true"))));
    }

    //
    public void checkCurrentCar(){
        for (int i=0;i<countCars;i++){
            if (getCar().getName().equals(Cars.get(i).getName())){
                currentPositionCar=i;
            }
        }
        if (currentPositionCar==0){
            root.getChildren().set(3,(new ImageView("images/clear.png")));
        } else if (currentPositionCar==8){
            root.getChildren().set(4,(new ImageView("images/clear.png")));
        }
    }

    public void checkSelected(){
        if ((parseCars().get(currentPositionCar).getSelected())){
            root.getChildren().set(11,new ImageView("images/drive.png"));
            root.getChildren().get(11).setLayoutX(400);
            root.getChildren().get(11).setLayoutY(430);
        } else {
            root.getChildren().set(11,new ImageView("images/clear.png"));
            root.getChildren().get(11).setLayoutX(400);
            root.getChildren().get(11).setLayoutY(430);
        }
    }

    public void checkBuy(){
        if ((parseCars().get(currentPositionCar).getBuy())){
            root.getChildren().set(10,new ImageView("images/clear.png"));
            root.getChildren().get(10).setLayoutX(595);
            root.getChildren().get(10).setLayoutY(455);
        } else {
            root.getChildren().set(10,new ImageView("images/close.png"));
            root.getChildren().get(10).setLayoutX(595);
            root.getChildren().get(10).setLayoutY(455);
        }
    }

    public void select(int pos){
        Cars.get(pos).setSelected(true);
        for (int i=0;i<countCars;i++){
            if (i!=pos){
                Cars.get(i).setSelected(false);
            }
        }
    }

}