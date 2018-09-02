package com.project.whj.clearntemplate.domain.executor;

/**
 * Created by wanghongjun on 2017/3/3.
 */

/**
 * 该接口，将使交互运行操作在主线程中
 * eg:一个interactor要显示一个对象的用户界面用来确保调用
 * UI线程
 */
public interface UIThread {
    /**
     * 使运行线程是UI线程
     * @param runnable
     */
    void post(final Runnable runnable);
}
