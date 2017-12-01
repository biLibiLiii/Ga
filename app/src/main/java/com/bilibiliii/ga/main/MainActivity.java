package com.bilibiliii.ga.main;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.connector.ConnectorFragment;
import com.bilibiliii.ga.conversation.ConversationListFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TITLE="会话";
    private TextView mTitle;
    private ImageButton mBackImageButton;
    private ImageButton mConverImageButton;
    private ImageButton mConnectorImageButton;
    private ImageButton mBrowserImageButton;
    private ImageButton mRightImageButton;
    FragmentManager mFragmentManager;
    ConversationListFragment mConversationListFragment;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_conversation);
    }

    @Override
    protected void initView() {
        mConverImageButton=(ImageButton) findViewById(R.id.conversation_imagebtn);
        mConnectorImageButton=(ImageButton) findViewById(R.id.connector_imagebtn);
        mBrowserImageButton=(ImageButton) findViewById(R.id.browser_imagebtn);
        mTitle=(TextView) findViewById(R.id.titlebar_title);
        mBackImageButton=(ImageButton)findViewById(R.id.titlebar_left_imagebtn);
        mRightImageButton=(ImageButton)findViewById(R.id.titlebar_right_imagebtn);
        mBackImageButton.setVisibility(View.VISIBLE);
        mTitle.setText(TITLE);
        mBackImageButton.setBackgroundResource(R.drawable.category);
        mConversationListFragment=new ConversationListFragment();
        mFragmentManager =getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.recyclerview_framelayout,mConversationListFragment)
                .commit();
        mConverImageButton.setOnClickListener(this);
        mConnectorImageButton.setOnClickListener(this);
        mBrowserImageButton.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.conversation_imagebtn:
                mFragmentManager.beginTransaction()
                        .replace(R.id.recyclerview_framelayout,mConversationListFragment)
                        .commit();
                resetImageIcon();
                mConverImageButton.setBackgroundResource(R.drawable.interactive_fill);
                break;
            case R.id.connector_imagebtn:
                mFragmentManager.beginTransaction()
                        .replace(R.id.recyclerview_framelayout,new ConnectorFragment())
                        .commit();
                resetImageIcon();
                mConnectorImageButton.setBackgroundResource(R.drawable.group_fill);
                mRightImageButton.setBackgroundResource(R.drawable.add);
                mRightImageButton.setVisibility(View.VISIBLE);
                break;
            case R.id.browser_imagebtn:

                resetImageIcon();
                mBrowserImageButton.setBackgroundResource(R.drawable.browse_fill);
                break;
        }
    }

    private void resetImageIcon(){
        mRightImageButton.setVisibility(View.GONE);
        mConverImageButton.setBackgroundResource(R.drawable.interactive);
        mConnectorImageButton.setBackgroundResource(R.drawable.group);
        mBrowserImageButton.setBackgroundResource(R.drawable.browse);
    }
}
