package com.example.btl_api.Model;

import java.util.Date;

public class Tintuc {
    private int MaTT,Id_Account,likee,binhluan;
    private String Image,NoiDung,NgayDang;

    public Tintuc(int maTT, int id_Account, int likee, int binhluan, String image, String noiDung, String ngayDang) {
        MaTT = maTT;
        Id_Account = id_Account;
        this.likee = likee;
        this.binhluan = binhluan;
        Image = image;
        NoiDung = noiDung;
        NgayDang = ngayDang;
    }

    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int maTT) {
        MaTT = maTT;
    }

    public int getId_Account() {
        return Id_Account;
    }

    public void setId_Account(int id_Account) {
        Id_Account = id_Account;
    }

    public int getLikee() {
        return likee;
    }

    public void setLikee(int likee) {
        this.likee = likee;
    }

    public int getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(int binhluan) {
        this.binhluan = binhluan;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }
}
