package com.project.whj.clearntemplate.presentation.ui.constact;


import com.project.whj.clearntemplate.presentation.presenter.base.BasePresenter;
import com.project.whj.clearntemplate.presentation.ui.constact.base.BaseView;

/**
 * 登录契约类：链接presenter层
 */
public interface LoginConstract {
    interface View extends BaseView<Presenter> {
        //用户输入用户名
        String getInputUserName();
        //用户输入密码
        String getInputPassWord();

        //登录成功
        void  LoginSucess();
        //登录失败
        void  LoginFailed(String message);
    }

    interface Presenter extends BasePresenter {
        //点击事件---登录
        void login();

    }
}
