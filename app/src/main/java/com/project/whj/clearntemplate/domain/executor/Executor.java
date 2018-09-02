package com.project.whj.clearntemplate.domain.executor;

/**
 * Created by wanghongjun on 2017/3/3.
 */


import com.project.whj.clearntemplate.domain.usecase.base.AbstractUseCase;

/**
 * 代表执行的UseCase在后台线程
 */
public interface Executor {
    /**
     * 这个方法调用UseCase运行方法，从而启动UseCase
     * UseCase:在后台线程做长时间准备
     */
    void executor(final AbstractUseCase abstarctInteractor);
}
