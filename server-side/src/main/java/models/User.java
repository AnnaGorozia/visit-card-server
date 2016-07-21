package models;

public class User {

    private String firstName;
    private String lastName;
    private String mainMail;
    private String phone;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMainMail() {
        return mainMail;
    }

    public void setMainMail(String mainMail) {
        this.mainMail = mainMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
