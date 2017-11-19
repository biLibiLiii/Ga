package com.bilibiliii.ga.chat;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Conversation;

import java.util.ArrayList;
import java.util.List;

public class ConversationListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mConverListRecyclerview;
    private ConversationAdapter mConversationAdapter;


    private List<Conversation> mConversations;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ConversationListFragment() {

    }
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
    public void addConversation(Conversation conversation){
        mConversationAdapter.addConversation(conversation);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mConversations=new ArrayList<>();
        mConversations.add(new Conversation("Tony Stark","11:09","Hi i am Iron Man!"));
        mConversations.add(new Conversation("小红","11:01","我 秦始皇 打钱!"));
        mConversationAdapter=new ConversationAdapter(mConversations);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        mConverListRecyclerview.setLayoutManager(linearLayoutManager);
        mConverListRecyclerview.setAdapter(mConversationAdapter);

        return view;
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
