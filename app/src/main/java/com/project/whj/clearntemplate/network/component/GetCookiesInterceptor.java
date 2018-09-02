package com.project.whj.clearntemplate.network.component;

/**
 * Created by wanghongjun on 2018/7/3.
 */

import android.content.Context;


import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 获取Cookie拦截器
 */
public class GetCookiesInterceptor implements Interceptor {

    private Context context;

    public GetCookiesInterceptor(Context context){
        super();
        this.context=context;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            /*Observable.from(originalResponse.headers("Set-Cookie"))
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";");
                            return cookieArray[0];
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            cookieBuffer.append(cookie);
                        }
                    });*/
            //保存成功之后的Cookie
            String cookie=cookieBuffer.toString();


        }
        return originalResponse;
    }
}
