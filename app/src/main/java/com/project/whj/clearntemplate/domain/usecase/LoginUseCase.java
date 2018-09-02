package com.project.whj.clearntemplate.domain.usecase;


import com.project.whj.clearntemplate.domain.usecase.base.UseCase;

/**
 * 登录关联:所有都在后台运行，而不应该影响UI展示
 */
public interface LoginUseCase extends UseCase {
    /**
     * 负责与主线程UI组件通信
     * 一般在：presentation的persenter下调用
     */
    interface CallBack{
        //登录成功
        void LoginSucess();
        //登录失败
        void LoginFailed(String message);
    }

}
