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

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;

public class ConversationListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mConverListRecyclerview;
    private ConversationAdapter mConversationAdapter;
    private List<Conversation> mConversations;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private List<BmobIMConversation> mBmobIMConversations;
    private MessageProxy mMessageProxy;


    public ConversationListFragment() {

    }
    @SuppressLint("ValidFragment")
    public ConversationListFragment(List<Conversation> conversations) {
        mConversations = conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        mConversations = conversations;
    }

    public static ConversationListFragment newInstance(String param1, String param2) {
        ConversationListFragment fragment = new ConversationListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        Log.d("licl","mBmobIMConversations size:"+mBmobIMConversations.size());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return view;
    }
    public void getData(){
        mConversations=new ArrayList<>();
        mConversations.add(new Conversation("Tony Stark","11:09","Hi i am Iron Man!"));
        mConversations.add(new Conversation("小红","11:01","我 秦始皇 打钱!"));
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
