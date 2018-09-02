package com.project.whj.clearntemplate.presentation.presenter.impl;


import com.project.whj.clearntemplate.domain.executor.Executor;
import com.project.whj.clearntemplate.domain.executor.UIThread;
import com.project.whj.clearntemplate.domain.repository.LoginRepostory;
import com.project.whj.clearntemplate.domain.usecase.LoginUseCase;
import com.project.whj.clearntemplate.domain.usecase.impl.LoginUseCaseImp;
import com.project.whj.clearntemplate.presentation.presenter.base.AbstractPresenter;
import com.project.whj.clearntemplate.presentation.ui.constact.LoginConstract;

/**
 * 登录数据交互：
 *      1.将presenter层数据，传到domain层的usecase
 *      2.获取domain层下传过来的数据
 */
public class LoginPresenterImp extends AbstractPresenter implements LoginConstract.Presenter
    ,LoginUseCase.CallBack{

    private LoginConstract.View view;
    private LoginRepostory loginRepostory;

    public LoginPresenterImp(Executor executor, UIThread mainThread, LoginRepostory loginRepostory,
                              LoginConstract.View view) {
        super(executor, mainThread);

        this.loginRepostory=loginRepostory;
        this.view=view;
        //presenter和view层建立联系
        this.view.setPresenter(this);
    }

    /**
     * 一般用于初始化
     */
    @Override
    public void start() {

    }

    //-------------------domain返回数据-------------
    @Override
    public void LoginSucess() {
        view.LoginSucess();
    }

    @Override
    public void LoginFailed(String message) {
        //登录失败
        view.LoginFailed(message);
    }




    //----------------向domain提交数据--------------------
    @Override
    public void login() {

        //转换为domai的model
        LoginUseCase loginUseCase=new LoginUseCaseImp(
                executor,
                uiThread,
                this,
                loginRepostory,
                view.getInputUserName(),
                view.getInputPassWord()
                );
        loginUseCase.execute(); //该方法必须有
    }




}
