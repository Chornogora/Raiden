package ua.nure.bulhakov.summary.model;

import java.util.Date;
import java.util.List;

public class Contract extends Entity {

    public enum STATUS{
        ORDERED, CONNECTED, BLOCKED
    }

    private static final long serialVersionUID = 1L;

    private List<Service> services;

    private Client client;

    private Date connected;

    private Date control;

    private STATUS status;

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

}
