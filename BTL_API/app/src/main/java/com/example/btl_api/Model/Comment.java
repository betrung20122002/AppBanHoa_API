package com.example.btl_api.Model;

public class Comment {
    private int id_comment,Id_Account,Id_Product;
    private String binhluan;

    public Comment(int id_comment, int id_Account, int id_Product, String binhluan) {
        this.id_comment = id_comment;
        Id_Account = id_Account;
        Id_Product = id_Product;
        this.binhluan = binhluan;
    }

    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public int getId_Account() {
        return Id_Account;
    }

    public void setId_Account(int id_Account) {
        Id_Account = id_Account;
    }

    public int getId_Product() {
        return Id_Product;
    }

    public void setId_Product(int id_Product) {
        Id_Product = id_Product;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }
}
