package com.example.btl_api.Model;

public class Carts {
    private int Id_Cart,Id_Account,Id_productdetails,Quantity,TotalMoney;
    private String Notes;

    public Carts(int id_Cart, int id_Account, int id_productdetails, int quantity, int totalMoney, String notes) {
        Id_Cart = id_Cart;
        Id_Account = id_Account;
        Id_productdetails = id_productdetails;
        Quantity = quantity;
        TotalMoney = totalMoney;
        Notes = notes;
    }

    public int getId_Cart() {
        return Id_Cart;
    }

    public void setId_Cart(int id_Cart) {
        Id_Cart = id_Cart;
    }

    public int getId_Account() {
        return Id_Account;
    }

    public void setId_Account(int id_Account) {
        Id_Account = id_Account;
    }

    public int getId_productdetails() {
        return Id_productdetails;
    }

    public void setId_productdetails(int id_productdetails) {
        Id_productdetails = id_productdetails;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        TotalMoney = totalMoney;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
