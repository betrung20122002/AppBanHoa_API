package com.example.btl_api.Model;

public class Product {
    private int Id_product,Id_danhmuc,sales,views;
    private String NameProduct,Content,Imagelinks,JoinDate;

    public int getId_product() {
        return Id_product;
    }

    public void setId_product(int id_product) {
        Id_product = id_product;
    }

    public int getId_danhmuc() {
        return Id_danhmuc;
    }

    public void setId_danhmuc(int id_danhmuc) {
        Id_danhmuc = id_danhmuc;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImagelinks() {
        return Imagelinks;
    }

    public void setImagelinks(String imagelinks) {
        Imagelinks = imagelinks;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public Product(int id_product, int id_danhmuc, int sales, int views, String nameProduct, String content, String imagelinks, String joinDate) {
        Id_product = id_product;
        Id_danhmuc = id_danhmuc;
        this.sales = sales;
        this.views = views;
        NameProduct = nameProduct;
        Content = content;
        Imagelinks = imagelinks;
        JoinDate = joinDate;
    }
}
