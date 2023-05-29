package com.example.btl_api.Model;

public class Notify {
    private int Id_notify,Id_Account,Id_productdetails,Status,watched;

    public Notify(int id_notify, int id_Account, int id_productdetails, int status, int watched) {
        Id_notify = id_notify;
        Id_Account = id_Account;
        Id_productdetails = id_productdetails;
        Status = status;
        this.watched = watched;
    }

    public int getId_notify() {
        return Id_notify;
    }

    public void setId_notify(int id_notify) {
        Id_notify = id_notify;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }
}
