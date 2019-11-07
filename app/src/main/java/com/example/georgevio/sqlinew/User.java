package com.example.georgevio.sqlinew;

/**
 * Created by Mitch on 2016-05-13.
 */
public class User {
    private String fName;
    private String Phone;
    private String Email;
    private String Street;

    public User(String name, String phone, String email , String street){
        fName = name;
        Phone = phone;
        Email = email;
        Street = street;
    }

    public String getName() {
        return fName;
    }

    public void setfName(String name) {
        fName = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

}
