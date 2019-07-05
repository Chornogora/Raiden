package ua.nure.bulhakov.summary.model;

public abstract class Service extends Entity {

    private static final long serialVersionUID = 1L;

    protected String name;

    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }
}
