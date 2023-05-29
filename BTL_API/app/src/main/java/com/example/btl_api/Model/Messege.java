package com.example.btl_api.Model;

public class Messege {
    private int Id_TinNhan,Id_MaGui,Id_MaNhan;
    private String Noidung;

    public Messege(int id_TinNhan, int id_MaGui, int id_MaNhan, String noidung) {
        Id_TinNhan = id_TinNhan;
        Id_MaGui = id_MaGui;
        Id_MaNhan = id_MaNhan;
        Noidung = noidung;
    }

    public int getId_TinNhan() {
        return Id_TinNhan;
    }

    public void setId_TinNhan(int id_TinNhan) {
        Id_TinNhan = id_TinNhan;
    }

    public int getId_MaGui() {
        return Id_MaGui;
    }

    public void setId_MaGui(int id_MaGui) {
        Id_MaGui = id_MaGui;
    }

    public int getId_MaNhan() {
        return Id_MaNhan;
    }

    public void setId_MaNhan(int id_MaNhan) {
        Id_MaNhan = id_MaNhan;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }
}
