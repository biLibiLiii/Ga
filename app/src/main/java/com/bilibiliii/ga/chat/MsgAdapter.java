package com.bilibiliii.ga.chat;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgViewHolder> {
    private List<Msg> mMsgs;

    public MsgAdapter(List<Msg> msgs) {
        mMsgs = msgs;
    }
    public void addMsg(Msg msg){
        mMsgs.add(msg);
        notifyItemInserted(mMsgs.size()-1);
    }
    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MsgViewHolder msgViewHolder=new MsgViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_chat_recyclerview,parent,false));
        return msgViewHolder;
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {

        if(mMsgs.get(position).getType()==Msg.TYPE_SEND){
            holder.mLeftMsgLayout.setVisibility(View.GONE);
            holder.mRightMsgLayout.setVisibility(View.VISIBLE);

            holder.mRightTextview.setText(mMsgs.get(position).getContent());
            holder.mRightImageButton.setBackgroundResource(R.drawable.icon_test);
        }else {
            holder.mRightMsgLayout.setVisibility(View.GONE);
            holder.mLeftMsgLayout.setVisibility(View.VISIBLE);

            holder.mLeftTextview.setText(mMsgs.get(position).getContent());
            holder.mLeftImageButton.setBackgroundResource(R.drawable.icon_test);
        }
    }

    @Override
    public int getItemCount() {

        return mMsgs.size();

    }

    class MsgViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mLeftMsgLayout;
        LinearLayout mRightMsgLayout;
        TextView mLeftTextview;
        TextView mRightTextview;
        ImageButton mLeftImageButton;
        ImageButton mRightImageButton;
        public MsgViewHolder(View itemView) {
            super(itemView);
            mLeftMsgLayout=(LinearLayout)itemView.findViewById(R.id.left_linearLayout);
            mRightMsgLayout=(LinearLayout)itemView.findViewById(R.id.right_linearLayout);
            mLeftTextview=(TextView)itemView.findViewById(R.id.left_msg);
            mRightTextview=(TextView)itemView.findViewById(R.id.right_msg);
            mLeftImageButton=(ImageButton)itemView.findViewById(R.id.left_icon);
            mRightImageButton=(ImageButton)itemView.findViewById(R.id.right_icon);
        }
    }

    public List<Msg> getMsgs() {
        return mMsgs;
    }

    public void setMsgs(List<Msg> msgs) {
        mMsgs = msgs;
    }
}
