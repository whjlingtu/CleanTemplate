package com.project.whj.clearntemplate.app;

import android.app.Activity;
import android.app.Application;
import android.os.Process;



import java.util.ArrayList;
import java.util.List;



public class AndroidApplication extends Application {

    /**
     * context对象
     */
    private static AndroidApplication context;
    /**
     * 已经开启activity集合
     */
    private static List<Activity> activitys;

    /**
     * 是否在发送数据
     */
    public static boolean isSendData;




    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

    }



    public static AndroidApplication getInstance(){
        return context;
    }
    //添加Activity到容器中
    public static void addActivity(Activity activity) {
        if (activitys == null) {
            activitys = new ArrayList<>();
        }
        activitys.add(activity);
    }

    //遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activitys) {
            activity.finish();
        }
        Process.killProcess(0);
        System.exit(0);
    }



}
