package com.bilibiliii.ga.connector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibiliii.ga.Config;
import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.NewFriend;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.FriendProxy;
import com.bilibiliii.ga.utils.db.NewFriendManager;

import java.util.List;

import cn.bmob.newim.bean.BmobIMMessage;

/**
 * Created by yhl on 2017/12/11.
 */

public class AgreeFriendAdapter extends RecyclerView.Adapter<AgreeFriendAdapter.AgreeFriendViewHolder> {
    private List<NewFriend> mNewFriendList;
    private Context mContext;
    private NewFriendManager mNewFriendManager;
    private FriendProxy mFriendProxy;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;


    private interface OnItemLongClickListener{
        void onItemLongClick();
    }
    private interface OnItemClickListener{
        void onItemClick();
    }
    public void setOnClickListner(OnItemClickListener onItemClickListener,OnItemLongClickListener onItemLongClickListener){
        mOnItemClickListener=onItemClickListener;
        mOnItemLongClickListener=onItemLongClickListener;
    }
    public AgreeFriendAdapter(List<NewFriend> newFriendList,Context context) {
        mNewFriendList = newFriendList;
        mContext=context;
        mNewFriendManager=NewFriendManager.getInstance(context);
        mFriendProxy=new FriendProxy();
    }

    @Override
    public AgreeFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AgreeFriendViewHolder agreeFriendViewHolder=new AgreeFriendViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_agreefriend_recyclerview,parent,false));
        return agreeFriendViewHolder;
    }

    @Override
    public void onBindViewHolder(AgreeFriendViewHolder holder, final int position) {
        holder.mNameTv.setText(mNewFriendList.get(position).getName());
        holder.mTime.setText(DateUtils.getRelativeTimeSpanString(mNewFriendList.get(position).getTime()));
        holder.mAgreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFriendProxy.sendAgreeAddFriendMessage(mNewFriendList.get(position), new CallBack<BmobIMMessage>() {
                    @Override
                    public void onSuccess(BmobIMMessage result) {
                        addFriend(mNewFriendList.get(position).getUid());
                        Toast.makeText(mContext,"成功添加好友",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Toast.makeText(mContext,"添加好友失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.mDisagreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void addFriend(String uid) {
        User user = new User();
        user.setObjectId(uid);

        new FriendProxy().agreeAddFriend(user, new CallBack<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFail(String errorInfo) {

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
