package com.project.whj.clearntemplate.domain.usecase.impl;


import android.text.TextUtils;

import com.project.whj.clearntemplate.domain.executor.Executor;
import com.project.whj.clearntemplate.domain.executor.UIThread;
import com.project.whj.clearntemplate.domain.repository.LoginRepostory;
import com.project.whj.clearntemplate.domain.usecase.LoginUseCase;
import com.project.whj.clearntemplate.domain.usecase.base.AbstractUseCase;

/**
 * 登录逻辑代码处理
 */
public class LoginUseCaseImp extends AbstractUseCase implements LoginUseCase {

    private LoginUseCase.CallBack callBack; //登录回调
    private UIThread uiThread;
    private LoginRepostory loginRepostory;  //登录资源
    private String userName;
    private String passWd;


    public LoginUseCaseImp(Executor executor, UIThread mainThread, LoginUseCase.CallBack callBack,
                           LoginRepostory loginRepostory, String userName,String passWd) {
        super(executor, mainThread);

        //赋值操作
        this.callBack=callBack;
        this.uiThread=mainThread;
        this.loginRepostory=loginRepostory;
        this.userName=userName;
        this.passWd=passWd;

    }

    @Override
    public void run() {
        //登录
        if (isLogin()){
            Login();
        }
    }

    private boolean isLogin(){
        if (userName.isEmpty()){
            uiThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.LoginFailed("用户名为空");
                }
            });
            return false;
        }
        if (passWd.isEmpty()){
            uiThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.LoginFailed("密码为空");
                }
            });
        }
        return true;
    }

    /**
     * 登录：
     *      -1:用户名为空，密码为空
     *      0：用户名为空，密码不为空
     *      1：用户名不为空，密码为空
     *      2：用户名或者密码输入错误
     *      3：链接服务器失败
     */
    private void Login(){
        if (userName.equals("whj") && passWd.equals("123")){
            uiThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.LoginSucess();
                }
            });
        }
    }

}
