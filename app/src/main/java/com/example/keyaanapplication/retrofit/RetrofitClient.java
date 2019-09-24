package com.example.keyaanapplication.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

//    public static Retrofit getClient(){
//        if(retrofit==null){
//            Gson gson=new GsonBuilder()
//                    .setLenient()
//                    .create();
//            retrofit=new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }

//    private RetrofitClient(){
//        retrofit=new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//
//    public static synchronized RetrofitClient getInstance(){
//
//        if (mInstance==null){
//
//            mInstance=new RetrofitClient();
//        }
//        return mInstance;
//    }
//
//    public Api getApi(){
//
//        return retrofit.create(Api.class);
//    }
}
