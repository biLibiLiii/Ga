package com.bilibiliii.ga.conversation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Conversation;
import com.bilibiliii.ga.chat.ChatActivity;
import com.bilibiliii.ga.utils.bmob.MessageProxy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;

public class ConversationListFragment extends Fragment {

    private RecyclerView mConverListRecyclerview;
    private ConversationAdapter mConversationAdapter;
    private OnFragmentInteractionListener mListener;
    private List<BmobIMConversation> mBmobIMConversations;
    private MessageProxy mMessageProxy;


    public ConversationListFragment() {

    }


    public static ConversationListFragment newInstance(String param1, String param2) {
        ConversationListFragment fragment = new ConversationListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 添加对话使用方法
    */
    public void addConversation(BmobIMConversation conversation){
        mConversationAdapter.addConversation(conversation);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessageProxy=new MessageProxy();
        mBmobIMConversations=mMessageProxy.queryAllConversation();
        if(mBmobIMConversations==null){
            mBmobIMConversations=new ArrayList();
        }
        mBmobIMConversations.add(new BmobIMConversation());
        Log.d("licl","mBmobIMConversations size:"+mBmobIMConversations.size());

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_conversation_list, container, false);
        mConverListRecyclerview=(RecyclerView)view.findViewById(R.id.conversation_list_recyclerView);
        getData();
        mConversationAdapter=new ConversationAdapter(mBmobIMConversations);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mConverListRecyclerview.setLayoutManager(linearLayoutManager);
        mConverListRecyclerview.setAdapter(mConversationAdapter);
        mConversationAdapter.setOnItemClickListener(new ConversationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getContext(), ChatActivity.class);
                BmobIMConversation bmobIMConversation=mBmobIMConversations.get(position);
                intent.putExtra("conversation",bmobIMConversation);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return view;
    }
    public void getData(){

    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
