package com.example.Apis;

import com.example.Forget.forgetPasswordResponse.Responseforget;
import com.example.Signup.responseSignup.ResponseSignup;
import com.example.login.responseLogin.LoginResponse;
import com.example.thebeerguy.DashBoard.Home.SubCategory.subCategoryResponse.ResponseSubCategory;
import com.example.thebeerguy.DashBoard.Home.categoryResponse.ResponseCategory;
import com.example.thebeerguy.DashBoard.ResponseJson.homeResponse.ResponseHome;
import com.example.thebeerguy.Product_Details.AddToCartResponse.ResponseAddToCart;
import com.example.thebeerguy.Product_Details.ProductDetailsResponse.ResponseProductDetail;
import com.example.thebeerguy.Product_Details.productListResponse.ResponseProductList;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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
    Call<List<ResponseProductList>> searchApiList(@FieldMap Map<String, String> search);

    @FormUrlEncoded
    @POST("product/list")
    Call<List<ResponseProductDetail>> productDetail(@FieldMap Map<String, String> productDetail);

    @FormUrlEncoded
    @POST("shopping_cart/update/")
    Call<ResponseAddToCart> addToCart(@FieldMap Map<String, String> AddToCart);


    @FormUrlEncoded
    @POST("product/category_list")
    Call<List<ResponseCategory>> categoryList (@FieldMap Map<String, String> Category);

    @FormUrlEncoded
    @POST("product/sub_category_list")
    Call<List<ResponseSubCategory>> subCategoryList (@FieldMap Map<String, String> subCategory);



}