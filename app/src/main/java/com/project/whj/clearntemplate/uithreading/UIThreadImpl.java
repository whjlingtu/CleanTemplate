package com.project.whj.clearntemplate.uithreading;

import android.os.Handler;
import android.os.Looper;

import com.project.whj.clearntemplate.domain.executor.UIThread;


/*
* 采用单例设计，创造Runabled对象提供给主线程
* */
public class UIThreadImpl implements UIThread {
    private static UIThread mMainThread;
    private Handler mHandler;
    private UIThreadImpl(){
        mHandler=new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
    /*获取MainThread对象*/
    public static UIThread getInstance(){
        if (mMainThread==null){
            mMainThread=new UIThreadImpl();
        }
        return mMainThread;
    }
}
