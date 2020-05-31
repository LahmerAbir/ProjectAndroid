package com.example.tounsia.User;

public class User {
    private String password ;
    private String passwordconf;
    private String pays;
    private String phoneNo;
    private String username;

    public User() {
    }

    public User(String password, String passwordconf, String pays, String phoneNo, String username) {
        this.password = password;
        this.passwordconf = passwordconf;
        this.pays = pays;
        this.phoneNo = phoneNo;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordconf() {
        return passwordconf;
    }

    public void setPasswordconf(String passwordconf) {
        this.passwordconf = passwordconf;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
