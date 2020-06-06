package com.r3bl.stayawake.retrofit;

import android.content.Context;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;


public class ApiClient {

    //    public static final String BASE_LOCAL_URL = "http://192.168.1.4:3000/";
//    public static final String BASE_LOCAL_EMULATOR_URL = "http://10.0.2.2:3000/";
    public static final String SERVIER_LOGIN = "http://ec2-13-127-84-252.ap-south-1.compute.amazonaws.com/";
    public static final String BASE_URL = SERVIER_LOGIN;

    private static Retrofit retrofit = null;

    private ApiClient() {


        if (retrofit != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static synchronized Retrofit getClient(Context context) {


        if (retrofit == null) {


            OkHttpClient client = new OkHttpClient.Builder().
                    readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build();

            return retrofit;
        }


        return retrofit;
    }


}
