package ua.nure.bulhakov.summary.model;

public class Administrator extends Entity {

    private static final long serialVersionUID = 1L;

    private String login;

    private String password;

    private String fullName;

    public String getLogin(){
        return login;
    }

    public void setLogin(String log){
        this.login = log;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName(){
        return fullName;
    }

    @Override
    public String toString(){
        return String.format("Administrator[ id=%d, login=%s, fullName=%s ]", id, login, fullName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
