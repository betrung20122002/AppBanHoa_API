package com.example.btl_api.Model;

public class ProductDetails {
    private int Id_productdetails,Id_product,Price,Promotionalprice;
    private String Size,Picture_1,Picture_2,Picture_3;

    public ProductDetails(int id_productdetails, int id_product, int price, int promotionalprice, String size, String picture_1, String picture_2, String picture_3) {
        Id_productdetails = id_productdetails;
        Id_product = id_product;
        Price = price;
        Promotionalprice = promotionalprice;
        Size = size;
        Picture_1 = picture_1;
        Picture_2 = picture_2;
        Picture_3 = picture_3;
    }

    public int getId_productdetails() {
        return Id_productdetails;
    }

    public void setId_productdetails(int id_productdetails) {
        Id_productdetails = id_productdetails;
    }

    public int getId_product() {
        return Id_product;
    }

    public void setId_product(int id_product) {
        Id_product = id_product;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getPromotionalprice() {
        return Promotionalprice;
    }

    public void setPromotionalprice(int promotionalprice) {
        Promotionalprice = promotionalprice;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getPicture_1() {
        return Picture_1;
    }

    public void setPicture_1(String picture_1) {
        Picture_1 = picture_1;
    }

    public String getPicture_2() {
        return Picture_2;
    }

    public void setPicture_2(String picture_2) {
        Picture_2 = picture_2;
    }

    public String getPicture_3() {
        return Picture_3;
    }

    public void setPicture_3(String picture_3) {
        Picture_3 = picture_3;
    }


}
