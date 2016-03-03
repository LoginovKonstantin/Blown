package start;

/**
 * Created by Егор on 03.03.2016.
 */
public class Car {

    private int id;
    private String name;
    private String img;
    private double maxSpeed;
    private double minSpeed;
    private double price;
    private double width;
    private double height;
    private boolean isBuy;
    private boolean isSelected;

    public void setCar(int id,String name,String img,double maxSpeed,double minSpeed,double width,double height,double price,boolean isBuy,boolean isSelected){
        this.id=id;
        this.name=name;
        this.img=img;
        this.maxSpeed=maxSpeed;
        this.price=price;
        this.width=width;
        this.height=height;
        this.isBuy=isBuy;
        this.isSelected=isSelected;
    }

    public Car getCar(Car car){
        return car;
    }
}
