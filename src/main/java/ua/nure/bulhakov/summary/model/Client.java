package ua.nure.bulhakov.summary.model;

public class Client extends Entity {

    public enum STATUS{
        NORMAL, TIMED_BLOCKED, BLOCKED
    }

    private static final long serialVersionUID = 1L;

    private String password;

    private String fullName;

    /*
    Can be empty
    */
    private String passportSeries;

    private int passportNumber;

    private double account;

    private String phoneNumber;

    private String email;

    private STATUS status;

    public Client(){
        account = 0;
        status=STATUS.NORMAL;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public STATUS getStatus(){
        return status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return String.format("Client[ id=%d, fullName=%s, account=%f, status=%s ]", id, fullName, account, status.toString());
    }
}