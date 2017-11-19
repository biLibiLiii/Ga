package com.bilibiliii.ga.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Conversation;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private List<Conversation> mConversations;

    public ConversationAdapter(List<Conversation> conversations) {
        mConversations = conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        mConversations = conversations;
    }
    public void addConversation(Conversation conversation){
        mConversations.add(conversation);
        notifyItemInserted(mConversations.size()-1);
    }
    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConversationViewHolder conversationViewHolder=new ConversationAdapter.ConversationViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_conversation_recyclerview,parent,false));

        return conversationViewHolder;
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {
        holder.mUserName.setText(mConversations.get(position).getUserName());
        holder.mConversationTime.setText(mConversations.get(position).getTime());
        holder.mLastConversation.setText(mConversations.get(position).getLastConversation());
        holder.mUserIcon.setImageResource(R.drawable.icon_test);
    }



    @Override
    public int getItemCount() {
        return mConversations.size();
    }

    class ConversationViewHolder extends RecyclerView.ViewHolder{
        ImageView mUserIcon;
        TextView mUserName;
        TextView mConversationTime;
        TextView mLastConversation;
        public ConversationViewHolder(View itemView) {
            super(itemView);
            mUserIcon=(ImageView)itemView.findViewById(R.id.user_icon);
            mUserName=(TextView)itemView.findViewById(R.id.user_name_conversationlist);
            mConversationTime=(TextView)itemView.findViewById(R.id.time_conversationlist);
            mLastConversation=(TextView)itemView.findViewById(R.id.last_conversation_conversationlist);
        }
    }
}
