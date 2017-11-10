package com.bilibiliii.ga.login;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.UserProxy;
import com.bilibiliii.ga.view.RoundImageView;

/**
 * @author No.47 create at 2017/11/8.
 */
public class LoginActivity extends BaseActivity {
    private EditText mUserNameEditText;
    private EditText mPassWordEditText;
    private Button mLoginButton;
    private Button mRegisteButton;
    private RoundImageView mRoundImageView;
    UserProxy mUserProxy;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        mUserProxy=UserProxy.getInstance();
        mRoundImageView=(RoundImageView) findViewById(R.id.person_icon);
        mRoundImageView.setImageResource(R.drawable.icon_test);
        mUserNameEditText=(EditText)findViewById(R.id.username_edittext);
        mPassWordEditText=(EditText)findViewById(R.id.password_edittext);
        mLoginButton=(Button) findViewById(R.id.login_btn);
        mRegisteButton=(Button)findViewById(R.id.registe_btn);
        mRegisteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("licl","you click register");
                String userName=mUserNameEditText.getText().toString();
                String passWord=mPassWordEditText.getText().toString();
                String email=mUserNameEditText.getText().toString();
                mUserProxy.register(userName, passWord, email, new CallBack<User>() {
                    @Override
                    public void onSuccess(User result) {
                        Log.d("licl","register success");
                        Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d("licl","register failed"+errorInfo);
                        Toast.makeText(LoginActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("licl","you click login");
                String userName=mUserNameEditText.getText().toString();
                String passWord=mPassWordEditText.getText().toString();
                String email="";
                mUserProxy.login(userName, passWord, new CallBack<User>() {
                    @Override
                    public void onSuccess(User result) {
                        Log.d("licl","login success");
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d("licl","login failed"+errorInfo);
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }
}
