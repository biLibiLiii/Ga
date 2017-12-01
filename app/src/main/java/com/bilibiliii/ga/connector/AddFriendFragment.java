package com.bilibiliii.ga.connector;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.User;
import com.bilibiliii.ga.main.MainActivity;
import com.bilibiliii.ga.utils.bmob.CallBack;
import com.bilibiliii.ga.utils.bmob.UserProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriendFragment extends Fragment {
    private ListView connectorListview;
    private List<User> mUsers=new ArrayList<>();
    private TextView mUsername;
    private EditText mSearchEditText;
    private ImageButton mSearchImageButton;
    private UserProxy mUserProxy;
    private ConnectorAdapter mConnectorAdapter;
    private Context mContext;
    public AddFriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext=(MainActivity)getActivity();
        View view=inflater.inflate(R.layout.fragment_add_friend, container, false);
        mUserProxy= UserProxy.getInstance();
        getData();
        connectorListview=(ListView) view.findViewById(R.id.connector_listview);
        mSearchEditText=(EditText)view.findViewById(R.id.search_name_edittext) ;
        mSearchImageButton=(ImageButton)view.findViewById(R.id.search_connector_imagebutton);
        mConnectorAdapter=new ConnectorAdapter();
        mConnectorAdapter.mUsers=mUsers;
        connectorListview.setAdapter(mConnectorAdapter);
        initListener();
        return view;
    }
    private void initListener(){
        mSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"查询中",Toast.LENGTH_SHORT).show();
                mUserProxy.queryUser(mSearchEditText.getText().toString(), new CallBack<List<User>>() {
                    @Override
                    public void onSuccess(List<User> result) {
                        Log.d("licl","查询用户成功"+result.size());
                        mConnectorAdapter.mUsers.clear();
                        mConnectorAdapter.mUsers.addAll(result);
                        mConnectorAdapter.notifyDataSetChanged();
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
    public void getData(){
        User.UserBuilder userBuilder=new User.UserBuilder();


    }
    private class ConnectorAdapter extends BaseAdapter {
        public List<User> mUsers;
        public Button addFriendBtn;
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
                view=LayoutInflater.from(getContext()).inflate(R.layout.item_addconnector_listview,viewGroup,false);
            }
            addFriendBtn=(Button)view.findViewById(R.id.add_conn_btn);
            mUsername=(TextView)view.findViewById(R.id.connector_name);
            mUsername.setText(mUsers.get(i).getUsername());
            addFriendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return view;
        }
    }
}
