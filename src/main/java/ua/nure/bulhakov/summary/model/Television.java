package ua.nure.bulhakov.summary.model;

public class Television extends Service implements MonthPaid{

    private static final long serialVersionUID = 1L;

    /*
    Num of channels in tariff
     */
    private int channels;

    private String format;

    private double monthPrice;

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
    }

    @Override
    public String toString(){
        return String.format("Television[ id=%d, channels=%d, format=%s, monthPrice=%f", id, channels, format, monthPrice);
    }

}
