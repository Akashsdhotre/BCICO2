package com.example.akashsdhotre.bcico2.remote;

import com.example.akashsdhotre.bcico2.model.FileInfo;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 25/4/18.
 */

public interface FileService {

    @Multipart
    @POST("insertpostData")
    Call<FileInfo> upload(@Part MultipartBody.Part file);

}
