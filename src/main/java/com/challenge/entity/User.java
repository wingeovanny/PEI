package com.challenge.entity;

public class User {

    private String user;

    private String token;

    public String gettoken() {
        return token;
    }

    public String getuser(String user) {
        return user;
    }

    public void settoken(String token) {

        this.token = token;
    }

    public void setuser(String user) {

        this.user = user;
    }

}
