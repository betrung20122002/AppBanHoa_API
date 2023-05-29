package com.example.btl_api.Model;

public class order_ {
    private int Id_Order,Id_Account,Id_productdetails,Quantity,TotalMoney,Status;
    private String Message,PaymentMethod,NgayDat,Notes;


    public order_(int id_Order, int id_Account, int id_productdetails, int quantity, int totalMoney, int status, String message, String paymentMethod, String ngayDat, String notes) {
        Id_Order = id_Order;
        Id_Account = id_Account;
        Id_productdetails = id_productdetails;
        Quantity = quantity;
        TotalMoney = totalMoney;
        Status = status;
        Message = message;
        PaymentMethod = paymentMethod;
        NgayDat = ngayDat;
        Notes = notes;
    }

    public int getId_Order() {
        return Id_Order;
    }

    public void setId_Order(int id_Order) {
        Id_Order = id_Order;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
