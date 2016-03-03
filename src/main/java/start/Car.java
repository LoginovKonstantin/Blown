package start;

/**
 * Created by Егор on 03.03.2016.
 */
public class Car {

    private int id = -1;
    private String name;
    private String img;
    private double maxSpeed;
    private double minSpeed;
    private double price;
    private double width;
    private double height;
    private boolean isBuy;
    private boolean isSelected;

    public Car(String name, String img, double maxSpeed, double minSpeed,
               double width, double height, double price, boolean isBuy, boolean isSelected){
        id++;
        this.name = name;
        this.img = img;
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

    public String getImg(){
        return this.img;
    }

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
