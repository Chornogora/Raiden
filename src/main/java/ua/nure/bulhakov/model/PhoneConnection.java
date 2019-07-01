package ua.nure.bulhakov.model;

public class PhoneConnection extends Service {

    private static final long serialVersionUID = 1L;

    private int monthPrice;

    /*
    Time that client can talk with others who use mobile connection
    */
    private int mobileMinutes;


    public int getMobileMinutes() {
        return mobileMinutes;
    }

    public void setMobileMinutes(int mobileMinutes) {
        this.mobileMinutes = mobileMinutes;
    }

    public int getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(int monthPrice) {
        this.monthPrice = monthPrice;
    }

    @Override
    public String toString(){
        return String.format("PhoneConnection[ id=%d, mobileMinutes=%d, monthPrice=%d ]", id, mobileMinutes, monthPrice);
    }
}
