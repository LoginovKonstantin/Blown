package objects;

import javafx.scene.image.Image;

/**
 * Created by Егор on 03.03.2016.
 */
public class Car {
    private String name;
    private Image imgSide, imgAbove;
    private double maxSpeed, minSpeed, price, width, height;
    private boolean isBuy, isSelected;;
    private int id;
    private String img;
    private static int counter = 0;

    public Car(String name, String img, double maxSpeed,
               double width, double height, double price, boolean isBuy, boolean isSelected){

        id = counter++;
        this.name = name;
//        this.imgProfile = img;
        this.img=img;
        this.maxSpeed = maxSpeed;
        this.price = price;
        this.width = width;
        this.height = height;
        this.isBuy = isBuy;
        this.isSelected = isSelected;
    }

    public String getName(){
        return this.name;
    }

    public String getImg(){ return this.img; }

//    public Image getImg(){
//        return this.imgProfile;
//    }

    public double getMaxSpeed(){
        return this.maxSpeed;
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    public double getPrice(){
        return this.price;
    }

    public boolean getBuy(){
        return this.isBuy;
    }

    public boolean getSelected(){
        return this.isSelected;
    }
}
