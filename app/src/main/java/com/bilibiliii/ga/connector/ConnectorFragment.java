package com.bilibiliii.ga.connector;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.base.BaseFragment;
import com.bilibiliii.ga.bean.Friend;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.main.MainActivity;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.FriendProxy;
import com.bilibiliii.ga.utils.bmob.UserProxy;

import java.util.List;

public class ConnectorFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ListView connectorListview;
    private List<Friend> mFriends;
    private TextView mUsername;
    private EditText mSearchEditText;
    private ImageButton mSearchImageButton;
    private UserProxy mUserProxy;
    private ConnectorAdapter mConnectorAdapter;
    private MainActivity mContext;
    private ImageButton mRightImageButton;
    private FriendProxy mFriendProxy;
    public ConnectorFragment() {

    }

    public static ConnectorFragment newInstance(String param1, String param2) {
        ConnectorFragment fragment = new ConnectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=(MainActivity)getActivity();
        mUserProxy=UserProxy.getInstance();
        mFriendProxy=new FriendProxy();
        mRightImageButton=(ImageButton)mContext.findViewById(R.id.titlebar_right_imagebtn);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getData();
    }
    public void getData(){

        mFriendProxy.queryFriends(new CallBack<List<Friend>>() {
            @Override
            public void onSuccess(List<Friend> result) {
                mFriends =result;
                mConnectorAdapter=new ConnectorAdapter();
                mConnectorAdapter.mUsers= mFriends;
                connectorListview.setAdapter(mConnectorAdapter);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_connector, container, false);
        connectorListview=(ListView) view.findViewById(R.id.connector_listview);
        mSearchEditText=(EditText)view.findViewById(R.id.search_name_edittext) ;
        mSearchImageButton=(ImageButton)view.findViewById(R.id.search_connector_imagebutton);

        initListener();
        return view;
    }

    private void initListener(){
        mRightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recyclerview_framelayout,new AddFriendFragment())
                        .commit();
            }
        });
        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"查询中",Toast.LENGTH_SHORT).show();
                mUserProxy.queryUser(mSearchEditText.getText().toString(), new CallBack<List<User>>() {
                    @Override
                    public void onSuccess(List<User> result) {
//                        Log.d("licl","查询用户成功"+result.size());
//                        mConnectorAdapter.mUsers.clear();
//                        mConnectorAdapter.mUsers.addAll(result);
//                        mConnectorAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d("licl","查询用户失败"+errorInfo);
                        Toast.makeText(getContext(),"未查询到指定用户或网络连接不可用",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private class ConnectorAdapter extends BaseAdapter{
        public List<Friend> mUsers;
        @Override
        public int getCount() {
            return mUsers.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view=LayoutInflater.from(getContext()).inflate(R.layout.item_connector_listview,viewGroup,false);
            }
            mUsername=(TextView)view.findViewById(R.id.connector_name);
            mUsername.setText(mUsers.get(i).getFriendUser().getUsername());
            return view;
        }
    }




}
