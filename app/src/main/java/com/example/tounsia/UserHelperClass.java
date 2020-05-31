package com.example.tounsia;

class UserHelperClass {
    String username , password ,passwordconf, pays , phoneNo;


    public UserHelperClass() {
    }


    public UserHelperClass(String username, String password, String passwordConf, String pays, String phoneNo) {
        this.username= username;
        this.password=password;
        this.passwordconf=passwordConf;
        this.pays=pays;
        this.phoneNo=phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordconf() {
        return passwordconf;
    }

    public String getPays() {
        return pays;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
