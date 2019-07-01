package ua.nure.bulhakov.model;

public class Work extends Service {

    private static final long serialVersionUID = 1L;

    private String name;

    private String measure;

    private int price;

    public String getName(){
        return name;
    }

    public String getMeasure(){
        return measure;
    }

    public int getPrice(){
        return price;
    }

    public void setName(String n){
        name = n;
    }

    public void setMeasure(String m){
        measure = m;
    }

    public void setPrice(int p){
        price = p;
    }

    public String toString(){
        return String.format("Work[ id=%d, name=%s, measure=%s, price=%d ]", id, name, measure, price);
    }

}