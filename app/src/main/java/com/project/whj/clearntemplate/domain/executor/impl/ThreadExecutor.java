package com.project.whj.clearntemplate.domain.executor.impl;


import com.project.whj.clearntemplate.domain.executor.Executor;
import com.project.whj.clearntemplate.domain.usecase.base.AbstractUseCase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 采用单例设计模式，确保每个交互操作获取一个后台线程
 */
public class ThreadExecutor implements Executor {
    private static volatile ThreadExecutor sThreadExecutor;

    private static final int                     CORE_POOL_SIZE  = 3;
    private static final int                     MAX_POOL_SIZE   = 5;
    private static final int                     KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT       = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE      = new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor mThreadPoolExecutor;

    //私有化构造方法
    private ThreadExecutor(){
        long keepAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }
    @Override
    public void executor(final AbstractUseCase interactor) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                // run the main logic
                interactor.run();

                // mark it as finished
                interactor.onFinished();
            }
        });
    }

    //对外提供对象
    public static Executor getInstance(){
        if (sThreadExecutor==null){
            sThreadExecutor=new ThreadExecutor();
        }
        return sThreadExecutor;
    }
}
