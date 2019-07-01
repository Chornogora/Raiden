package ua.nure.bulhakov.model;

public class Client extends Entity {

    public enum STATUS{
        NORMAL, BLOCKED
    }

    private static final long serialVersionUID = 1L;

    private long id;

    private String fullName;

    /*
    Can be empty
    */
    private int passwordSeries;

    private int passportNumber;

    private double account;

    private String phoneNumber;

    private String email;

    private STATUS status;

    public Client(){

    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public STATUS getStatus(){
        return status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPasswordSeries() {
        return passwordSeries;
    }

    public void setPasswordSeries(int passwordSeries) {
        this.passwordSeries = passwordSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;

    }

    @Override
    public String toString(){
        return String.format("Client[ id=%d, fullName=%s, account=%f, status=%s ]", id, fullName, account, status.toString());
    }
}