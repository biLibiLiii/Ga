package com.bilibiliii.ga.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Msg;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MsgAdapter mMsgAdapter;
    private List<Msg> mMsgs;
    LinearLayoutManager mLinearLayoutManager;
    private TextView mMsgTextView;
    private Button mSendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mRecyclerView=(RecyclerView)findViewById(R.id.msg_recyclerview) ;
        getData();
        mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMsgAdapter=new MsgAdapter(mMsgs);
        mRecyclerView.setAdapter(mMsgAdapter);
        Log.d("licl","setAdapter excute");
        initDataView();
        initListeners();
    }
    private void initDataView(){
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
                mMsg=new Msg(msg,Msg.TYPE_SEND);
                mMsgs.add(mMsg);
                mMsgAdapter.setMsgs(mMsgs);
                mMsgAdapter.notifyItemInserted(mMsgs.size()-1);
                mMsgTextView.setText("");
            }
        });
    }
    public void getData(){
        mMsgs=new ArrayList<>();
        mMsgs.add(new Msg("Hi!",0));
        mMsgs.add(new Msg("Hello!",1));
        mMsgs.add(new Msg("It is very nice to meet you!",0));
    }
}
