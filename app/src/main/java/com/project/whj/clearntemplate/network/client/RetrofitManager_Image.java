package com.project.whj.clearntemplate.network.client;

import android.text.TextUtils;

import com.project.whj.clearntemplate.network.ip.IPManger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pan on 2018/4/2.
 * retrofit 管理
 */

public class RetrofitManager_Image {

    private static RetrofitManager_Image mRetrofitManager;

    private final Retrofit mRetrofit;

    private RetrofitManager_Image() {


        OkHttpClient okHttp = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IPManger.PROJECT_IP)
                .build();
    }

    public static RetrofitManager_Image getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager_Image.class) {
                mRetrofitManager = new RetrofitManager_Image();
            }
        }
        return mRetrofitManager;
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }
}
