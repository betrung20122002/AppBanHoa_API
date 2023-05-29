package com.example.btl_api.Model;


public class Accounts {
    private int Id_Account;
    private String Name,Email,Passwordd,Numberphone,Address,JoinDate,Picture;

    public Accounts(int id_Account, String name, String email, String passwordd, String numberphone, String address, String joinDate, String picture) {
        Id_Account = id_Account;
        Name = name;
        Email = email;
        Passwordd = passwordd;
        Numberphone = numberphone;
        Address = address;
        JoinDate = joinDate;
        Picture = picture;
    }

    public int getId_Account() {
        return Id_Account;
    }

    public void setId_Account(int id_Account) {
        Id_Account = id_Account;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasswordd() {
        return Passwordd;
    }

    public void setPasswordd(String passwordd) {
        Passwordd = passwordd;
    }

    public String getNumberphone() {
        return Numberphone;
    }

    public void setNumberphone(String numberphone) {
        Numberphone = numberphone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
}
