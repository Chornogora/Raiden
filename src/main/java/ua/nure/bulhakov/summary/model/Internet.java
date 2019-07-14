package ua.nure.bulhakov.summary.model;

public class Internet extends Service implements MonthPaid{

    private static final long serialVersionUID = 1L;

    /*
    MB per month
    */
    private int speed;

    private double monthPrice;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
    }

    @Override
    public String toString(){
        return String.format("Internet[ id=%d, speed=%d, monthPrice=%f ]", id, speed, monthPrice);
    }

}