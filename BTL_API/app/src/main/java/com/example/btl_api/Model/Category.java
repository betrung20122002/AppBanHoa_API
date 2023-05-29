package com.example.btl_api.Model;

import androidx.recyclerview.widget.RecyclerView;

public class Category  {
 private int Id_Category;
 private String NameCategory,PictureCategory,JoinDate,Note;

    public int getId_Category() {
        return Id_Category;
    }

    public void setId_Category(int id_Category) {
        Id_Category = id_Category;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public void setNameCategory(String nameCategory) {
        NameCategory = nameCategory;
    }

    public String getPictureCategory() {
        return PictureCategory;
    }

    public void setPictureCategory(String pictureCategory) {
        PictureCategory = pictureCategory;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Category(int id_Category, String nameCategory, String pictureCategory, String joinDate, String note) {
        Id_Category = id_Category;
        NameCategory = nameCategory;
        PictureCategory = pictureCategory;
        JoinDate = joinDate;
        Note = note;
    }
}
