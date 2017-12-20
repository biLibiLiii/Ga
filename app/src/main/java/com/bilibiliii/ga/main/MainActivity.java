package com.bilibiliii.ga.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseActivity;
import com.bilibiliii.ga.connector.ConnectorFragment;
import com.bilibiliii.ga.conversation.ConversationListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TITLE="会话";

    @Bind(R.id.titlebar_title)
    TextView mTitle;
    @Bind(R.id.titlebar_left_imagebtn)
    ImageButton mBackImageButton;
    @Bind((R.id.conversation_imagebtn))
    ImageButton mConverImageButton;
    @Bind(R.id.connector_imagebtn)
    ImageButton mConnectorImageButton;
    @Bind(R.id.browser_imagebtn)
    ImageButton mBrowserImageButton;
    @Bind(R.id.titlebar_right_imagebtn)
    ImageButton mRightImageButton;
    FragmentManager mFragmentManager;
    ConversationListFragment mConversationListFragment;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigationview)
    NavigationView mNavigationView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_conversation);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mRightImageButton.setVisibility(View.VISIBLE);
        mRightImageButton.setBackgroundResource(R.drawable.comments);
        mBackImageButton.setVisibility(View.VISIBLE);
        mNavigationView.setItemIconTintList(null);
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
        mBackImageButton.setOnClickListener(this);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId=item.getItemId();
                switch (itemId){
                    case R.id.weather:
                        Toast.makeText(MainActivity.this,"click weather",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        Toast.makeText(MainActivity.this,"click location",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
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
                mRightImageButton.setBackgroundResource(R.drawable.comments);
                mRightImageButton.setVisibility(View.VISIBLE);
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
            case R.id.titlebar_left_imagebtn:
                mDrawerLayout.openDrawer(mNavigationView);
        }
    }

    private void resetImageIcon(){
        mRightImageButton.setVisibility(View.GONE);
        mConverImageButton.setBackgroundResource(R.drawable.interactive);
        mConnectorImageButton.setBackgroundResource(R.drawable.group);
        mBrowserImageButton.setBackgroundResource(R.drawable.browse);
    }
}
