package com.bilibiliii.ga.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Msg;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.MessageProxy;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;

public class ChatActivity extends AppCompatActivity {

    private MessageProxy mMessageProxy;
    private BmobIMUserInfo mBmobIMUserInfo;
    private RecyclerView mRecyclerView;
    private MsgAdapter mMsgAdapter;
    private List<Msg> mMsgs;
    private BmobIMConversation mBmobIMConversation;
    LinearLayoutManager mLinearLayoutManager;
    private TextView mMsgTextView;
    private Button mSendButton;
    private TextView mTitle;
    private ImageButton mLeftTitleImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initDataView();
        initListeners();
    }
    private void initDataView(){
        mBmobIMConversation=(BmobIMConversation)getIntent().getSerializableExtra("conversation");
        mTitle=(TextView)findViewById(R.id.titlebar_title);
        mLeftTitleImageButton =(ImageButton)findViewById(R.id.titlebar_left_imagebtn);
        mLeftTitleImageButton.setBackgroundResource(R.drawable.back);
        mLeftTitleImageButton.setVisibility(View.VISIBLE);
        /**
         * 初始化聊天对象信息
         * */

        mMessageProxy=new MessageProxy();
        mBmobIMUserInfo=new BmobIMUserInfo();
        mBmobIMConversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), mBmobIMConversation);
        mMessageProxy.setMessageManager(mBmobIMConversation);

//        mBmobIMUserInfo.setUserId("30bb775920");
//        mBmobIMUserInfo.setName("li6");
//        mMessageProxy.createNewConversation(mBmobIMUserInfo);
        Log.d("licl",mBmobIMConversation.getConversationTitle());
        mTitle.setText(mBmobIMConversation.getConversationTitle());
        mRecyclerView=(RecyclerView)findViewById(R.id.msg_recyclerview) ;
        getData();
        mLinearLayoutManager=new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMsgAdapter=new MsgAdapter(mMsgs);
        mRecyclerView.setAdapter(mMsgAdapter);
        mMsgTextView=(TextView) findViewById(R.id.msg_textview);
        mSendButton=(Button)findViewById(R.id.send_btn);
    }
    private void initListeners(){
        mSendButton.setOnClickListener(new View.OnClickListener() {
            String msg;
            Msg mMsg;
            @Override
            public void onClick(View view) {

                msg=mMsgTextView.getText().toString();
                mMessageProxy.sendMessage(msg, new CallBack<BmobIMMessage>() {
                    @Override
                    public void onSuccess(BmobIMMessage result) {
                        Log.d("licl","send message success");
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d("licl","send message failed"+errorInfo);
                    }
                });
                mMsg=new Msg(msg,Msg.TYPE_SEND);
                mMsgs.add(mMsg);
                mMsgAdapter.setMsgs(mMsgs);
                mMsgAdapter.notifyItemInserted(mMsgs.size()-1);
                mMsgTextView.setText("");
            }
        });
        mLeftTitleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getData(){
        mMsgs=new ArrayList<>();
        mMessageProxy.queryMessages(new CallBack<List<BmobIMMessage>>() {
            @Override
            public void onSuccess(List<BmobIMMessage> result) {
                for(BmobIMMessage bmobIMMessage :result){
                    int type=bmobIMMessage.getToId()==BmobIM.getInstance().getCurrentUid()?Msg.TYPE_RECEIVED:Msg.TYPE_SEND;
                    mMsgs.add(new Msg(bmobIMMessage.getContent(),type));
                }
            }
            @Override
            public void onFail(String errorInfo) {

            }
        });

    }
}
