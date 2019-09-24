package com.example.keyaanapplication.retrofit;

import com.example.keyaanapplication.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

//
    @FormUrlEncoded
    @POST("registeruser")
    Call<LoginResponse> registeruser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("Name") String name,
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("loginuser")
    Call<LoginResponse> loginuser(
            @Field("login_id") String email,
            @Field("login_password") String password
    );


//    @FormUrlEncoded
//    @POST("registeruser")
//    Call<MSG> registeruser(@Field("email,") String email,
//                         @Field("password") String password,
//                         @Field("Name,") String name,
//                         @Field("mobile,") String mobile
//    );
}
