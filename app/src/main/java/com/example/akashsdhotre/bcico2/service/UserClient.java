package com.example.akashsdhotre.bcico2.service;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Body;


/**
 * Created by user on 26/4/18.
 */

public interface UserClient {

    @Multipart
    @POST("insertpostData")
    Call<ResponseBody> insertpostData(
            @Part("token") RequestBody token,
            @Part("type") RequestBody type,
            @Part("action") RequestBody action,
            @Part("text") RequestBody text,
            @Part MultipartBody.Part image
    );
}
