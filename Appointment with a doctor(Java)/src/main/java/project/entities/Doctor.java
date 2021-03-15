package project.entities;

public class Doctor {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String speciality;
    private int mobileNum;

    public Doctor(String name, String surname, String username, String password, String speciality, int mobileNum) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.speciality = speciality;
        this.mobileNum = mobileNum;
    }

    public Doctor(int id, String name, String surname, String username, String password, String speciality, int mobileNum) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.speciality = speciality;
        this.mobileNum = mobileNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }
}