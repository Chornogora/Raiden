package ua.nure.bulhakov.model;

public class Television extends Service {

    private static final long serialVersionUID = 1L;

    /*
    Num of channels in tariff
     */
    private int channels;

    private String format;

    private int monthPrice;

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

    public int getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(int monthPrice) {
        this.monthPrice = monthPrice;
    }

    @Override
    public String toString(){
        return String.format("Television[ id=%d, channels=%d, format=%s, monthPrice=%d", id, channels, format, monthPrice);
    }

}
