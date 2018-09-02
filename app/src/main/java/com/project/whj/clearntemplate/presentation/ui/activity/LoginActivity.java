package com.project.whj.clearntemplate.presentation.ui.activity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.whj.clearntemplate.R;
import com.project.whj.clearntemplate.domain.executor.impl.ThreadExecutor;
import com.project.whj.clearntemplate.presentation.presenter.impl.LoginPresenterImp;
import com.project.whj.clearntemplate.presentation.ui.component.util.ImeObserver;
import com.project.whj.clearntemplate.presentation.ui.constact.LoginConstract;
import com.project.whj.clearntemplate.storage.imp.LoginRepostoryImp;
import com.project.whj.clearntemplate.uithreading.UIThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements LoginConstract.View {


    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_passwd)
    EditText etLoginPasswd;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    private LoginConstract.Presenter presenter;//链接presenter层


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //关闭键盘，点击除EditTextView,隐藏键盘
        ImeObserver.observer(this);

        init();

    }


    /**
     * 初始化
     */
    private void init() {
        new LoginPresenterImp(
                ThreadExecutor.getInstance(),
                UIThreadImpl.getInstance(),
                new LoginRepostoryImp(),
                this);      //必须有
        presenter.start();  //必须有

    }


    @Override
    public void setPresenter(LoginConstract.Presenter presenter) {
        this.presenter = presenter;
    }
    //-------------用户输入数据---------------------------

    /**
     * 用户名输入
     *
     * @return
     */
    @Override
    public String getInputUserName() {
        return etLoginUsername.getText().toString();
    }

    /**
     * 用户密码输入
     *
     * @return
     */
    @Override
    public String getInputPassWord() {
        return etLoginPasswd.getText().toString();
    }


    /**
     * 登陆成功
     */
    @Override
    public void LoginSucess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 登录：
     * -1:用户名为空，密码为空
     * 0：用户名为空，密码不为空
     * 1：用户名不为空，密码为空
     * 2：用户名或者密码输入错误
     * 3：链接服务器失败
     */
    @Override
    public void LoginFailed(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.bt_login_login)
    public void onViewClicked() {
        presenter.login();  //点击登录
    }
}
