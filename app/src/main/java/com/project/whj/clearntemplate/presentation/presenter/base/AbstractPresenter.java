package com.project.whj.clearntemplate.presentation.presenter.base;


import com.project.whj.clearntemplate.domain.executor.Executor;
import com.project.whj.clearntemplate.domain.executor.UIThread;

/**
 * Presenter层的基类：和intercator进行通信
 * 这个类提到的Executor和UIThread对象是Interactor对象所需要的
 * UseCase:运行在后台线程中
 */
public abstract class AbstractPresenter {
    public Executor executor;
    public UIThread uiThread;
    public AbstractPresenter(Executor executor,UIThread uiThread){
        this.executor=executor;
        this.uiThread=uiThread;
    }
}
