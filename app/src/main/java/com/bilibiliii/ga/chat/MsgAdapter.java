package com.bilibiliii.ga.chat;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        Log.d("licl","MsgAdapter excute");
        mMsgs = msgs;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("licl","onCreateViewHolder excute");
        MsgViewHolder msgViewHolder=new MsgViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_chat_recyclerview,parent,false));
        return msgViewHolder;
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        Log.d("licl","onBindViewHolder excute");
        if(mMsgs.get(position).getType()==Msg.TYPE_SEND){
            holder.mLeftMsgLayout.setVisibility(View.GONE);
            holder.mRightMsgLayout.setVisibility(View.VISIBLE);

            holder.mRightTextview.setText(mMsgs.get(position).getContent());
        }else {
            holder.mRightMsgLayout.setVisibility(View.GONE);
            holder.mLeftMsgLayout.setVisibility(View.VISIBLE);
            holder.mLeftTextview.setText(mMsgs.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        Log.d("licl","getItemCount excute");
        return mMsgs.size();

    }

    class MsgViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mLeftMsgLayout;
        LinearLayout mRightMsgLayout;
        TextView mLeftTextview;
        TextView mRightTextview;
        public MsgViewHolder(View itemView) {
            super(itemView);
            mLeftMsgLayout=(LinearLayout)itemView.findViewById(R.id.left_linearLayout);
            mRightMsgLayout=(LinearLayout)itemView.findViewById(R.id.right_linearLayout);
            mLeftTextview=(TextView)itemView.findViewById(R.id.left_msg);
            mRightTextview=(TextView)itemView.findViewById(R.id.right_msg);
        }
    }

    public List<Msg> getMsgs() {
        return mMsgs;
    }

    public void setMsgs(List<Msg> msgs) {
        mMsgs = msgs;
    }
}
