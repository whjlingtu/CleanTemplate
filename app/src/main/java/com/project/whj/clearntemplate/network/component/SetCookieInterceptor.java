package com.project.whj.clearntemplate.network.component;

import android.content.Context;

import com.project.whj.clearntemplate.presentation.ui.util.ShpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanghongjun on 2018/7/3.
 */

/**
 * 设置Cookie
 */

public class SetCookieInterceptor implements Interceptor {

    private Context context;

    public SetCookieInterceptor(Context context){
        super();
        this.context=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        ShpUtil shpUtil=new ShpUtil(context,"Cookie");
        String cookie=shpUtil.getString("cookie","");
        Request request = chain.request();
        Response response;
        if (!cookie.equals("")) {
            Request compressedRequest = request.newBuilder()
                    .header("Content-type","application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Cookie", cookie.substring(0,cookie.length()-1))
                    .build();

            response = chain.proceed(compressedRequest);
        }else{
            response = chain.proceed(request);
        }

        return response;
    }
}
