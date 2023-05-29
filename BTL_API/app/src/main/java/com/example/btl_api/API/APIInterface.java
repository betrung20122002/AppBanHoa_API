package com.example.btl_api.API;

import com.example.btl_api.Model.Comment;
import com.example.btl_api.Model.Messege;
import com.example.btl_api.Model.Notify;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Carts;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.Model.Tintuc;
import com.example.btl_api.Model.order_;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    APIInterface API = new Retrofit.Builder().baseUrl("https://tallorangepen80.conveyor.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(APIInterface.class);
    @POST("api/Accounts")
    Call<Accounts> PostAccount(@Body Accounts account);
    @PUT("api/order_/{id}")
    Call<order_> PutOder(@Path("id") int id,@Body order_ order);
    @PUT("api/Accounts/{id}")
    Call<String> PutAccount(@Path("id") int id, @Body Accounts account);
    @GET("api/Accounts/{id}")
    Call<Accounts> GetAccount(@Path("id") int id);
    @GET("api/tk/kiemtra/{email}/{mk}")
    Call<List<Accounts>> kiemtra(@Path("email") String email, @Path("mk") String mk);
    @GET("api/Accounts/{id}")
    Call<Accounts> gettt(@Path("id") int id);
    @GET("api/Categories")
    Call<List<Category>> Get_Category();
    @GET("api/Categories/{id}")
    Call<Category> Get_Category_id(@Path("id") int id);
    @GET("api/san_pham/timkiem/{id}")
    Call<List<Product>> GetSearchProduct(@Path("id") String id);
    @GET("api/Products")
    Call<List<Product>> Get_Product();
     @GET("api/Products/{id}")
     Call<Product> getchitiet(@Path("id") int id);
    @GET("api/ProductDetails/{id}")
    Call<ProductDetails> GetProductDetail(@Path("id") int id);
    @GET("api/ProductDetails/GetProductDetails/{Id_product}")
    Call<List<ProductDetails>> GetProductDetails(@Path("Id_product") int Id_product);

    @GET("api/Category/GetId_categorys/{Id_danhmuc}")
    Call<List<Product>> GetId_Product(@Path("Id_danhmuc") int Id_danhmuc);
    @POST("api/Carts")
    Call<List<Carts>> PostCart(@Body Carts cart);
    @PUT("api/Carts/{id}")
    Call<Carts> PutCart(@Path ("id")int id,@Body Carts cart);
    @GET("api/Cart/TotalMoney/{id_account}")
    Call<String> GetTotalMoney(@Path("id_account") int id_account);
    @DELETE("api/Carts/{id}")
    Call<Carts> DeletCarts(@Path("id") int id);
    @GET("api/Cart/GetCartAccpunt/{id_account}")
    Call<List<Carts>> GetCartAccpunt(@Path("id_account") int id_account);
    @POST("api/order_")
    Call<order_> Postorder_(@Body order_ order);
    @DELETE("api/Carts/{id}")
    Call<Carts> DeleteCart(@Path("id") int id);
    @POST("api/Notifies")
    Call<Notify>PostNotify(@Body Notify notify);
    @GET("api/Getorder/{id_Account}/{Status}")
    Call<List<order_>> Getorder(@Path("id_Account") int id_Account, @Path("Status") int Status);
    @PUT("api/order_/{id}")
    Call<order_> Putorder_(@Path("id") int id,@Body order_ order);
    @GET("api/Notifies/{Id_Account}")
    Call<List<Notify>>GetNotifys(@Path("Id_Account")int Id_Account);
    @DELETE("api/Categories/{id}")
    Call<Category> DeleteCategory(@Path("id") int id);
    @POST("api/Categories")
    Call<Category>PostCategory(@Body Category category);
    @PUT("api/Categories/{id}")
    Call<Category> PutCategory(@Path("id") int id, @Body Category category);
    @DELETE("api/Products/{id}")
    Call<Product> DeleteProduct(@Path("id") int id);
    @POST("api/Products")
    Call<Product>PostProduct(@Body Product product);
    @PUT("api/Products/{id}")
    Call<Product> PutProduct(@Path("id") int id,@Body Product product);
    @GET("api/ProductDetails")
    Call<List<ProductDetails>> GetProductDetail();
    @DELETE("api/ProductDetails/{id}")
    Call<ProductDetails> DeleteProductDetail(@Path("id") int id);
    @POST("api/ProductDetails")
    Call<Product>PostProductDetail(@Body ProductDetails productDetails);
    @PUT("api/ProductDetails/{id}")
    Call<ProductDetails> PutProductDetail(@Path("id") int id,@Body ProductDetails productDetails);
    @GET("api/order_")
    Call<List<order_>> Getorder();
    @GET("api/order_/{id}")
    Call<order_> GetOrder_id(@Path("id") int id);
    @GET("api/getall")
    Call<List<Tintuc>> GetTinTuc();
    @DELETE("api/TinTucs/{id}")
    Call<Tintuc> DeletePOST(@Path("id") int id);
    @POST("api/TinTucs")
    Call<Tintuc>PostTinTuc(@Body Tintuc tintuc);
    @GET("api/Messeges")
    Call<List<Messege>> GetTinNhan();
    @POST("api/Messeges")
    Call<Product>PostTinNhan(@Body Messege messege);
    @GET("api/Accounts")
    Call<List<Accounts>> GetAccount();
    @DELETE("api/Messeges/{id}")
    Call<Messege> DeleteMessge(@Path("id") int id);
    @GET("api/revenue/{ngay}/{thang}/{nam}")
    Call<Integer> GetThongKeTheoNgay(@Path("ngay") int ngay, @Path("thang") int thang, @Path("nam") int nam);
    @GET("api/TongHangDaBan")
    Call<Integer> Get_ThongKeHangDaBan();
    @GET("api/TongDoanhThu")
    Call<Integer> Get_ThongKeTongThuNhap();
    @GET("api/chon/TimKiemTheoKhoangGia/{minPrice}/{maxPrice}")
    Call<List<ProductDetails>> Get_timkiem_khoanggia(@Path("minPrice") int minPrice, @Path("maxPrice") int maxPrice);
    @GET("api/revenue/{Account}")
    Call<String> Get_ThongKe_TaiKhoan_Tien(@Path("Account") int Account);
    @GET("api/ThongKeSoLuong/{Account}")
    Call<String> Get_ThongKe_TaiKhoan_SoLuong(@Path("Account") int Account);
    @POST("api/Comments")
    Call<Comment>PostbinhLuan(@Body Comment comment);
    @GET("api/getComment/{Id_Product}")
    Call<List<Comment>> Get_BinhLuan(@Path("Id_Product") int Id_Product);
    @GET("tongtheothang/{tentg}")
    Call<String> getThongKe(@Path("tentg") int tentg);
}
