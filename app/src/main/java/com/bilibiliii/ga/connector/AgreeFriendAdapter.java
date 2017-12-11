package com.bilibiliii.ga.connector;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.NewFriend;

import java.util.List;

/**
 * Created by yhl on 2017/12/11.
 */

public class AgreeFriendAdapter extends RecyclerView.Adapter<AgreeFriendAdapter.AgreeFriendViewHolder> {
    List<NewFriend> mNewFriendList;

    public AgreeFriendAdapter(List<NewFriend> newFriendList) {
        mNewFriendList = newFriendList;
    }

    @Override
    public AgreeFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AgreeFriendViewHolder agreeFriendViewHolder=new AgreeFriendViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_agreefriend_recyclerview,parent,false));
        return agreeFriendViewHolder;
    }

    @Override
    public void onBindViewHolder(AgreeFriendViewHolder holder, int position) {
        holder.mNameTv.setText(mNewFriendList.get(position).getName());
        holder.mTime.setText(mNewFriendList.get(position).getTime().toString());
        holder.mAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.mDisagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return mNewFriendList.size();
    }

    class AgreeFriendViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameTv;
        private TextView mTime;
        private Button mAgreeButton;
        private Button mDisagreeButton;
        public AgreeFriendViewHolder(View itemView) {
            super(itemView);
            mNameTv=(TextView)itemView.findViewById(R.id.user_name_agreefriend);
            mTime=(TextView)itemView.findViewById(R.id.time_agreefriend);
            mAgreeButton=(Button)itemView.findViewById(R.id.agree_btn);
            mDisagreeButton=(Button)itemView.findViewById(R.id.disagree_btn);
        }
    }
}
