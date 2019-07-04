package ua.nure.bulhakov.summary.model;

public class Work extends Service {

    private static final long serialVersionUID = 1L;

    private String name;

    private String measure;

    private double price;

    public String getName(){
        return name;
    }

    public String getMeasure(){
        return measure;
    }

    public double getPrice(){
        return price;
    }

    public void setName(String n){
        name = n;
    }

    public void setMeasure(String m){
        measure = m;
    }

    public void setPrice(double p){
        price = p;
    }

    public String toString(){
        return String.format("Work[ id=%d, name=%s, measure=%s, price=%f ]", id, name, measure, price);
    }

}