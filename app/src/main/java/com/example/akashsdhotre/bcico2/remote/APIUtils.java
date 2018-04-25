package com.example.akashsdhotre.bcico2.remote;

import com.example.akashsdhotre.bcico2.Network.NetworkUrls;

import retrofit2.Retrofit;

/**
 * Created by user on 25/4/18.
 */

public class APIUtils {

    private APIUtils(){}

    public static final String API_URL= NetworkUrls.BCICOLinks.BASE_URL;

    public static FileService getFileService()
    {
        return RetrofitClient.getClient(API_URL).create(FileService.class);
    }
}
