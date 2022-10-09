package com.example.Apis;

import com.example.Forget.forgetPasswordResponse.Responseforget;
import com.example.Profile.AddAddressResponse.ResponseAddAddress;
import com.example.Signup.responseSignup.ResponseSignup;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.Home.CheckOut.PaymentReq;
import com.example.thebeerguy.DashBoard.Home.CheckOut.ReviewCartModel;
import com.example.thebeerguy.DashBoard.Home.PaymentResponse.ResponsePayment;
import com.example.thebeerguy.DashBoard.Home.ResponseSearch.ResponseSearch;
import com.example.thebeerguy.DashBoard.Home.SubCategory.FilterResponse.ResponseFilter;
import com.example.thebeerguy.DashBoard.Home.SubCategory.subCategoryResponse.ResponseSubCategory;
import com.example.thebeerguy.DashBoard.Home.categoryResponse.ResponseCategory;
import com.example.thebeerguy.DashBoard.PurchaseHistoryResponse.ResponsePurchaseHistory;
import com.example.thebeerguy.DashBoard.ResponseJson.ProductReq;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.DashBoard.ReviewCart;
import com.example.thebeerguy.DashBoard.ReviewModel;
import com.example.thebeerguy.Intro.ResponseStore.ResponseStore;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.FavResponse.ResponseFav;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.ResponseProductDetail;
import com.example.thebeerguy.Product_Details.productListResponse.ResponseProductList;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("api-auth-token")
    Call<LoginResponse> createUser(@FieldMap Map<String, String> login);

    @FormUrlEncoded
    @POST("customer/create")
    Call<ResponseSignup> signupUser(@FieldMap Map<String, String> Signup);

    @FormUrlEncoded
    @POST("forgot_password")
    Call<Responseforget> forgetUser(@FieldMap Map<String, String> forgetPassword);

    @FormUrlEncoded
    @POST("product/list")
    Call<List<ResponseHome>> home(@FieldMap Map<String, String> home);

    @FormUrlEncoded
    @POST("product/list")
    Call<List<ResponseProductList>> productList(@FieldMap Map<String, String> productList);

    @FormUrlEncoded
    @POST("product/list")
    Call<ResponseSearch> searchApiList(@FieldMap Map<String, String> search);

    @FormUrlEncoded
    @POST("product/list")
    Call<List<ResponseProductDetail>> productDetail(@FieldMap Map<String, String> productDetail);

//    @FormUrlEncoded
    @POST("shopping_cart/update/")
    Call<ResponseAddToCart> addToCart(@Body ReviewModel reviewModel);

    @POST("shopping_cart/update/")
    Call<ResponseAddToCart> checkout(@Body ReviewCartModel reviewModel);


    @POST("shopping_cart/update/")
    Call<ResponseAddToCart> payment_done(@Body PaymentReq reviewModel);

    @FormUrlEncoded
    @POST("customer/fave_product")
    Call<ResponseFav> fav (@FieldMap Map<String, String> fav,  @Header("Authorization") String authHeader);

//    @FormUrlEncoded
    @POST("purchase/create/")
    Call<ResponsePayment> payment (@Body ReviewModel reviewMod);

    @FormUrlEncoded
    @POST("customer_location/create/")
    Call<ResponseAddAddress> addAddress (@FieldMap Map<String, String> addAddress);

    @FormUrlEncoded
    @POST("product/category_list")
    Call<List<ResponseCategory>> categoryList (@FieldMap Map<String, String> Category);

    @FormUrlEncoded
    @POST("product/sub_category_list")
    Call<List<ResponseSubCategory>> subCategoryList (@FieldMap Map<String, String> subCategory);

    @FormUrlEncoded
    @POST("store_location/list")
    Call<List<ResponseStore>> storeApi(@FieldMap Map<String, String> login);

    @FormUrlEncoded
    @POST("search_filter_menu")
    Call<ResponseFilter> filter(@FieldMap Map<String, String> filter);

    @FormUrlEncoded
    @POST("store_location/list")
    Call<List<ResponsePurchaseHistory>> purchaseHistory(@FieldMap Map<String, String> purchaseHistory);



}