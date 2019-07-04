package ua.nure.bulhakov.summary.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int id;

    public void setId(int identificator){
        id = identificator;
    }

    public int getId(){
        return id;
    }

}
