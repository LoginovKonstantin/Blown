package objects;

import javafx.scene.image.Image;

/**
 * Created by Егор on 03.03.2016.
 */
public class Car {
    private String name;
    private String imgSide, imgAbove;
    private double maxSpeed, price, width, height;
    private boolean isBuy, isSelected;
    private int id;
    private static int counter = 0;

    public Car(String name, String imgSide, String imgAbove, double maxSpeed,
               double width, double height, double price, boolean isBuy, boolean isSelected) {
        id = counter++;
        this.name = name;
        this.imgSide = imgSide;
        this.imgAbove = imgAbove;
        this.maxSpeed = maxSpeed;
        this.price = price;
        this.width = width;
        this.height = height;
        this.isBuy = isBuy;
        this.isSelected = isSelected;
    }

    public String getName() {
        return this.name;
    }

    public String getImgSide() {return this.imgSide;}

    public String getImgAbove() {return this.imgAbove;}

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean getBuy() {
        return this.isBuy;
    }

    public boolean getSelected() {
        return this.isSelected;
    }

    public void setBuy(boolean isBuy) { this.isBuy=isBuy; }

    public void setSelected(boolean isSelected) { this.isSelected=isSelected; }

}
