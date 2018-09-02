package com.project.whj.clearntemplate.domain.usecase.base;


import com.project.whj.clearntemplate.domain.executor.Executor;
import com.project.whj.clearntemplate.domain.executor.UIThread;

/**
 * 这个抽象类实现了，所有Interacor的常用方法。取消Interactor(关联)
 * ，检查其运行和修整interactor一些相同代码。我们可能使用这些方法来自于
 * 不同的线程(主要是从用户界面)
 * eg:当Activity正在破坏我们或许应该取消Interactor(关联)，但请求将来自
 * UI线程，除非请求被专门分配给后台线程
 */
public abstract class AbstractUseCase implements UseCase {
    public Executor mThreadExcutor;
    public UIThread mMainThread;
    //注意volatile这个关键字
    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;
    public AbstractUseCase(Executor executor, UIThread mainThread){
        this.mMainThread=mainThread;
        this.mThreadExcutor=executor;
    }

    /**
     *这个方法包含关联（interactor）的实际业务逻辑。它不应该直接使用
     * 而是，由开发者应该调用interactor的execute()确保在后台线程完成
     * 该方法只在进行单元/集成测试时直接调用。这就是它被宣布的唯一原因
     * ，以帮助更容易测试
     */
    public abstract void run();

    public void cancel(){
        mIsCanceled=true;
        mIsRunning=false;
    }
    public boolean isRuning(){
        return mIsRunning;
    }

    public void onFinished(){
        mIsRunning=false;
        mIsCanceled=false;
    }
    public void execute(){
        //创造interactor是运行
        this.mIsRunning=true;
        //开始后台线程中interactor
        mThreadExcutor.executor(this);
    }

}
