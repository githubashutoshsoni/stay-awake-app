package com.r3bl.stayawake.retrofit;


import com.r3bl.stayawake.database.Location;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceInterface {


    String TWITTER_API = "twitter.com/get/twitter/login/api";


    @GET("api/user/branch/getBranchDetail")
    Call<ResponseBody> getTweetsFeed(@Body Location location);


}
