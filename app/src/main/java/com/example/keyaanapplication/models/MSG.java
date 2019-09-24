package com.example.keyaanapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MSG {

    @SerializedName("reg_name")
    @Expose
    private String reg_name;
    @SerializedName("email_id")
    @Expose
    private String email_id;
    @SerializedName("mobile")
    @Expose
    private Integer mobile;
    @SerializedName("password")
    @Expose
    private String password;

    public MSG(String reg_name,String email_id,Integer mobile,String password){

        this.reg_name=reg_name;
        this.email_id=email_id;
        this.mobile=mobile;
        this.password=password;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MSG{" +
                "reg_name='" + reg_name + '\'' +
                ", email_id='" + email_id + '\'' +
                ", mobile=" + mobile +
                ", password=" + password +
                '}';
    }
}
