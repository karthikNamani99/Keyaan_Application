package com.example.keyaanapplication.models;


public class LoginResponse {


    private String login_status;
    private MSG login_message;

    public LoginResponse(String login_status, MSG login_message) {
        this.login_status = login_status;
        this.login_message = login_message;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public MSG getLogin_message() {
        return login_message;
    }

    public void setLogin_message(MSG login_message) {
        this.login_message = login_message;
    }
}
