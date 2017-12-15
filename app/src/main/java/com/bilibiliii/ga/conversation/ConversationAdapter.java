package com.bilibiliii.ga.conversation;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Conversation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.listener.MessagesQueryListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by Administrator on 2017/11/14.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {


    private List<BmobIMConversation> mBmobIMConversations;


    public ConversationAdapter(List<BmobIMConversation> bmobIMConversations) {
        mBmobIMConversations = bmobIMConversations;
    }

    public interface OnItemClickListener{
        void onItemClick(View view ,int position);
        void onItemLongClick(View view ,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }


    public void setConversations(List<BmobIMConversation> conversations) {
        mBmobIMConversations = conversations;
    }
    public void addConversation(BmobIMConversation conversation){
        for(BmobIMConversation tmpConcersation:mBmobIMConversations){
            if(tmpConcersation.getConversationTitle().equals(conversation.getConversationTitle())){
                return;
            }
        }
        mBmobIMConversations.add(conversation);
        notifyItemInserted(mBmobIMConversations.size()-1);
    }
    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConversationViewHolder conversationViewHolder=new ConversationAdapter.ConversationViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_conversation_recyclerview,parent,false));

        return conversationViewHolder;
    }

    @Override
    public void onBindViewHolder(final ConversationViewHolder holder, int position) {
        final String[] finalMsg = new String[1];
        try {
            mBmobIMConversations.get(position).queryMessages(null, 20, new MessagesQueryListener() {
                @Override
                public void done(List<BmobIMMessage> list, BmobException e) {
                    finalMsg[0] =list.get(list.size()-1).getContent();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.mUserName.setText(mBmobIMConversations.get(position).getConversationTitle());
        holder.mConversationTime.setText(DateUtils.getRelativeTimeSpanString(mBmobIMConversations.get(position).getUpdateTime()));
        holder.mLastConversation.setText(finalMsg[0]);
        holder.mUserIcon.setImageResource(R.drawable.icon_test);

        if (mOnItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return mBmobIMConversations.size();
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
