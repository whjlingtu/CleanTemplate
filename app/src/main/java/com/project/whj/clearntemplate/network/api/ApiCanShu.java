package com.project.whj.clearntemplate.network.api;

/**
 * Created by wanghongjun on 2018/7/11.
 */

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置网络参数
 */
public class ApiCanShu {

    /**
     * 登录参数设置
     */
    public static Map setLoginNetCanshu(String userName, String passwd) {
        Map map = new HashMap();
        map.put("username", userName);
        map.put("password", passwd);
        return map;
    }




}
