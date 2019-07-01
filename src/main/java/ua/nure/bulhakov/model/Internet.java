package ua.nure.bulhakov.model;

public class Internet extends Service {

    private static final long serialVersionUID = 1L;

    /*
    MB per month
    */
    private int speed;

    private int monthPrice;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(int monthPrice) {
        this.monthPrice = monthPrice;
    }

    @Override
    public String toString(){
        return String.format("Internet[ id=%d, speed=%d, monthPrice=%d", id, speed, monthPrice);
    }

}