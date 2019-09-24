package com.example.keyaanapplication.retrofit;



public class ApiUtils {

    private ApiUtils() {

    }

    public static final String BASE_URL = "http://keyitsol.com/keyaancashews/api/user/";


    public static Api getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(Api.class);
    }
}
