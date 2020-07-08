package com.example.trucking;

public class User {
    private String Fullname,Email,Mobile,Pass;
    public User(){

    }
    public User(String fullname, String email, String mobile, String pass) {
        Fullname = fullname;
        Email = email;
        Mobile = mobile;
        Pass = pass;

    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

}
