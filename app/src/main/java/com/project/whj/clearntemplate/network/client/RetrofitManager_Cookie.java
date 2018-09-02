package com.project.whj.clearntemplate.network.client;

import android.text.TextUtils;


import com.project.whj.clearntemplate.app.AndroidApplication;
import com.project.whj.clearntemplate.network.component.GetCookiesInterceptor;
import com.project.whj.clearntemplate.network.ip.IPManger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit 管理
 */

public class RetrofitManager_Cookie {

    private static RetrofitManager_Cookie mRetrofitManager;

    private final Retrofit mRetrofit;

    private RetrofitManager_Cookie() {



        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new GetCookiesInterceptor(AndroidApplication.getInstance()))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IPManger.PROJECT_IP)
                .build();
    }

    public static RetrofitManager_Cookie getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager_Cookie.class) {
                mRetrofitManager = new RetrofitManager_Cookie();
            }
        }
        return mRetrofitManager;
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }
}
