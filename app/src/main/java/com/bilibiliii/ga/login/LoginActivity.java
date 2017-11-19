package com.bilibiliii.ga.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.chat.ConversationActivity;
import com.bilibiliii.ga.main.MainActivity;
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
    private TextView mRegisteTextview;
    UserProxy mUserProxy;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        Handler.Callback callback=new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                return false;
            }
        };
        mUserProxy=UserProxy.getInstance(callback);
        mRoundImageView=(RoundImageView) findViewById(R.id.person_icon);
        mRoundImageView.setImageResource(R.drawable.icon_test);
        mUserNameEditText=(EditText)findViewById(R.id.username_edittext);
        mPassWordEditText=(EditText)findViewById(R.id.password_edittext);
        mLoginButton=(Button) findViewById(R.id.login_btn);
        mRegisteTextview=(TextView)findViewById(R.id.regist_textview);
        mRegisteTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName=mUserNameEditText.getText().toString();
                String passWord=mPassWordEditText.getText().toString();
                if("".equals(userName)||"".equals(passWord)){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("licl","you click login"+"userName:"+userName+" passWord:"+passWord);
                mUserProxy.login(userName, passWord, new CallBack<User>() {
                    @Override
                    public void onSuccess(User result) {
                        Log.d("licl","login success");
                        Intent intent=new Intent(LoginActivity.this,ConversationActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d("licl","login failed"+errorInfo);
                        if("101".equals(errorInfo)){
                            Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        }

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
