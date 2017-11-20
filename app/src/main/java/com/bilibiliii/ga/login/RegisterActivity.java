package com.bilibiliii.ga.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.main.MainActivity;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.UserProxy;

public class RegisterActivity extends BaseActivity {
    private EditText mUserNameEditText;
    private Button mRegisteButton;
    private EditText mPassWordEditText;
    private TextView mTitle;
    private ImageButton mBackImageButton;
    private static final String TITLE="注册";
    UserProxy mUserProxy;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void initView() {
        mTitle=(TextView) findViewById(R.id.titlebar_title);
        mBackImageButton=(ImageButton)findViewById(R.id.titlebar_left_imagebtn);
        mBackImageButton.setVisibility(View.VISIBLE);
        mRegisteButton=(Button)findViewById(R.id.regist_btn);
        mUserNameEditText=(EditText) findViewById(R.id.regist_username);
        mPassWordEditText=(EditText)findViewById(R.id.regist_password);
        mTitle.setText(TITLE);
        mBackImageButton.setBackgroundResource(R.drawable.back);
//        mBackImageButton.setImageResource(R.drawable.back);
        mBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRegisteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=mUserNameEditText.getText().toString();
                String passWord=mPassWordEditText.getText().toString();
                if("".equals(userName)||"".equals(passWord)){
                    Toast.makeText(RegisterActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Handler.Callback callback=new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        return false;
                    }
                };
                Log.d("licl","you click login"+"userName:"+userName+" passWord:"+passWord);
                mUserProxy= UserProxy.getInstance();;
                mUserProxy.register(userName, passWord, new CallBack<User>() {
                    @Override
                    public void onSuccess(User result) {
                        Log.d("licl","register success");
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d("licl","register failed"+errorInfo);
                        Toast.makeText(RegisterActivity.this,"注册失败"+errorInfo,Toast.LENGTH_SHORT).show();
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
