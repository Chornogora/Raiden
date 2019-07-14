package ua.nure.bulhakov.summary.model;

import java.util.Date;
import java.util.List;

public class Contract extends Entity {

    public enum STATUS{
        CONNECTED, BLOCKED
    }

    private static final long serialVersionUID = 1L;

    private List<Service> services;

    private String address;

    private Client client;

    private Date connected;

    private Date control;

    private STATUS status;

    private double monthPrice;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getConnected() {
        return connected;
    }

    public void setConnected(Date connected) {
        this.connected = connected;
    }

    public Date getControl() {
        return control;
    }

    public void setControl(Date control) {
        this.control = control;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
    }

    public double getMonthPrice(){
        if(monthPrice != 0.0){
            return monthPrice;
        }

        double result = 0;
        for(Service service : services){
            if(service instanceof MonthPaid){
                result += ((MonthPaid)service).getMonthPrice();
            }
        }
        return result;
    }
}