package start;

import javafx.scene.image.Image;

/**
 * Created by Егор on 03.03.2016.
 */
public class Car {

    private int id = -1;
    private String name;
    private Image imgSide, imgAbove;
    private double maxSpeed, minSpeed, price, width, height;
    private boolean isBuy, isSelected;;

    public Car(String name, Image img, double maxSpeed, double minSpeed,
               double width, double height, double price, boolean isBuy, boolean isSelected){
        id++;
        this.name = name;
//        this.imgProfile = img;
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

    public int getId(){
        return this.id;
    }

//    public Image getImg(){
//        return this.imgProfile;
//    }

    public double getMaxSpeed(){
        return this.maxSpeed;
    }

    public double getMinSpeed(){
        return this.minSpeed;
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
